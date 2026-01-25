package org.example.project.sampleform.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ObjectUtils;
import org.example.project.common.exception.BusinessException;
import org.example.project.common.service.FileService;
import org.example.project.sampleform.converter.SampleFormConverter;
import org.example.project.sampleform.entity.SampleFormEntity;
import org.example.project.sampleform.mapper.SampleFormMapper;
import org.example.project.sampleform.record.SampleFileFormRecord;
import org.example.project.sampleform.record.SampleFormRecord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Log4j2
@Service
public class SampleFormService {

  private final SampleFormMapper mapper;
  private final SampleFormConverter converter;
  private final FileService fileService;

  public SampleFormService(
      SampleFormMapper mapper, SampleFormConverter converter, FileService fileService) {
    this.mapper = mapper;
    this.converter = converter;
    this.fileService = fileService;
  }

  public PageInfo<SampleFormRecord> getAll(int page, int size) {
    /* PageHelper 반환값을 사용하지 않는 패턴이 표준이라 소나큐브를 무시하는 주석으로 예외처리 */
    PageHelper.startPage(page, size); // NOSONAR
    List<SampleFormEntity> resultList = mapper.findAll();
    return new PageInfo<>(resultList.stream().map(converter::toRecord).toList());
  }

  public SampleFormRecord getById(int dataId) {
    return converter.toRecord(mapper.findById(dataId));
  }

  public void save(SampleFormRecord dataRecord) {
    if (dataRecord.dataId() == null || dataRecord.dataId() == 0) {
      mapper.insert(converter.toEntity(dataRecord));
    } else {
      mapper.update(converter.toEntity(dataRecord));
    }
  }

  @Transactional
  public void saveWithFiles(SampleFileFormRecord dataRecord) {
    SampleFormEntity entity = new SampleFormEntity();
    entity.setTitle(dataRecord.title());
    mapper.insert(entity);
    if (ObjectUtils.isNotEmpty(entity.getDataId())
        && !CollectionUtils.isEmpty(dataRecord.uploadedFiles())) {
      int fileUploadCnt =
          fileService.attachFilesFinalization(
              dataRecord.uploadedFiles(), dataRecord.refType(), String.valueOf(entity.getDataId()));
      if (fileUploadCnt == 0) {
        log.error("첨부파일 최종 확정 에러: {}", entity.getDataId());
        throw new BusinessException("첨부파일 업로드 에러가 발생 했습니다.", "FAIL_UPLOAD_FILE", 500);
      }
    }
  }

  public void update(SampleFormRecord dataRecord) {
    mapper.update(converter.toEntity(dataRecord));
  }

  public void delete(int dataId) {
    mapper.delete(dataId);
  }
}
