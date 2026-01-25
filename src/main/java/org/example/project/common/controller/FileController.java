package org.example.project.common.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.project.common.record.FileResponse;
import org.example.project.common.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/common/files")
@RequiredArgsConstructor
public class FileController {

  private final FileService fileService;

  @PostMapping("/upload")
  public ResponseEntity<FileResponse> upload(
      @RequestParam("file") MultipartFile file,
      @RequestParam("refType") String refType,
      @RequestParam("regId") String regId) {
    FileResponse response = fileService.uploadFile(file, refType, regId);
    // 업로드된 파일 정보 반환(ResponseEntity 통한 200코드 응답)
    return ResponseEntity.ok(response);
  }

  // 여러 개 동시 업로드 처리 시
  @PostMapping("/upload-multiple")
  public List<FileResponse> uploadMultiple(
      @RequestParam("files") List<MultipartFile> files,
      @RequestParam("refType") String refType,
      @RequestParam("refId") String refId) {

    return files.stream()
        .map(file -> fileService.uploadFile(file, refType, refId)) // 파라미터 전달
        .toList();
  }
}
