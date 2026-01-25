package org.example.project.common.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.apache.tika.utils.StringUtils;
import org.example.project.common.entity.FileEntity;
import org.example.project.common.exception.BusinessException;
import org.example.project.common.mapper.FileMapper;
import org.example.project.common.record.FileRecord;
import org.example.project.common.record.FileResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class FileService {

  // 로컬 저장 경로 (application.yml 설정 사용)
  @Value("${custom-file.base-path}")
  private String basePath;

  private final Tika tika = new Tika();

  private final FileMapper mapper;

  public FileService(FileMapper mapper) {
    this.mapper = mapper;
  }

  public FileResponse uploadFile(MultipartFile file, String refType, String refId) {
    Map<String, Object> resultMap = storeFile(file, refType);
    return uploadFile(resultMap, refType, refId) > 0
        ? new ObjectMapper().convertValue(resultMap, FileResponse.class)
        : null;
  }

  private int uploadFile(Map<String, Object> resultMap, String refType, String refId) {
    if (resultMap != null) {
      // DB 저장
      FileEntity entity =
          FileEntity.builder()
              .refType(refType)
              .refId(StringUtils.isEmpty(refId) ? "0" : refId)
              .originName((String) resultMap.get("originalName"))
              .savedName((String) resultMap.get("savedName"))
              .filePath((String) resultMap.get("uploadPath"))
              .extension((String) resultMap.get("extension"))
              .fileSize(Objects.toString(resultMap.get("size"), "0"))
              .contentType((String) resultMap.get("contentType"))
              .downloadCount("0")
              .delYn("N")
              .build();
      int dmlCnt = mapper.insert(entity);
      if (dmlCnt > 0) {
        resultMap.put("fileId", entity.getFileId());
      }
      return dmlCnt;
    }
    return 0;
  }

  public int attachFilesFinalization(List<FileRecord> fileList, String refType, String refId) {
    int result = 0;
    for (FileRecord file : fileList) {
      FileEntity entity =
          FileEntity.builder().fileId(file.fileId()).refType(refType).refId(refId).build();
      result += mapper.update(entity);
    }
    return result;
  }

  private Map<String, Object> storeFile(MultipartFile file, String refType) {
    if (file.isEmpty()) {
      throw new BusinessException("파일이 비어있습니다.", "FILE_EMPTY", 400);
    }

    try {
      // 1. MIME 타입 검증 (Tika 사용)
      String mimeType = tika.detect(file.getInputStream());
      log.info("Detected MIME type: {}", mimeType);

      // 허용하고 싶은 타입만 필터링 (예: 이미지, PDF 등)
      if (!isValidType(mimeType)) {
        throw new BusinessException("지원하지 않는 파일 형식입니다.", "INVALID_FILE_TYPE", 400);
      }

      // 2. 파일명 중복 방지를 위한 UUID 생성
      String originalName = file.getOriginalFilename();
      String extension =
          StringUtils.isEmpty(originalName)
              ? ""
              : originalName.substring(originalName.lastIndexOf("."));
      String savedName = UUID.randomUUID().toString() + extension;

      // 3. 디렉토리 생성 및 파일 저장
      String filePath = basePath + refType;
      File targetDir = new File(filePath);
      boolean mkdirsResult = true;
      if (!targetDir.exists()) {
        mkdirsResult = targetDir.mkdirs();
      }
      if (!mkdirsResult) {
        throw new BusinessException("파일 저장 디렉토리 생성에 실패했습니다.", "DIRECTORY_CREATION_FAILED", 500);
      }

      File saveFile = new File(filePath, savedName);
      file.transferTo(saveFile);

      // 4. 결과 반환
      Map<String, Object> resultMap = new HashMap<>();
      resultMap.put("originalName", originalName);
      resultMap.put("savedName", savedName);
      resultMap.put("uploadPath", filePath);
      resultMap.put("extension", extension);
      resultMap.put("size", file.getSize());
      resultMap.put("contentType", mimeType);

      return resultMap;

    } catch (IOException e) {
      log.error("File upload error", e);
      throw new BusinessException("파일 저장 중 오류가 발생했습니다.", "FILE_SAVE_ERROR", 500);
    }
  }

  private boolean isValidType(String mimeType) {
    // 실제 운영 시에는 더 엄격하게 체크
    return mimeType.startsWith("image/")
        || mimeType.equals("application/pdf")
        || mimeType.equals(
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); // xlsx
  }
}
