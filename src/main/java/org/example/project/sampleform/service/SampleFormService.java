package org.example.project.sampleform.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.example.project.sampleform.converter.SampleFormConverter;
import org.example.project.sampleform.entity.SampleFormEntity;
import org.example.project.sampleform.mapper.SampleFormMapper;
import org.example.project.sampleform.record.SampleFormRecord;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class SampleFormService {

  private final SampleFormMapper mapper;
  private final SampleFormConverter converter;

  public SampleFormService(SampleFormMapper mapper, SampleFormConverter converter) {
    this.mapper = mapper;
    this.converter = converter;
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

  public void add(SampleFormRecord dataRecord) {
    mapper.insert(converter.toEntity(dataRecord));
  }

  public void update(SampleFormRecord dataRecord) {
    mapper.update(converter.toEntity(dataRecord));
  }

  public void delete(int dataId) {
    mapper.delete(dataId);
  }
}
