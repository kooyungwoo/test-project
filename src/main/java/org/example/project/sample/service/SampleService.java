package org.example.project.sample.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;

import org.example.project.common.exception.BusinessException;
import org.example.project.sample.converter.SampleConverter;
import org.example.project.sample.entity.SampleEntity;
import org.example.project.sample.mapper.SampleMapper;
import org.example.project.sample.record.SampleRecord;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

  private final SampleMapper mapper;
  private final SampleConverter converter;

  public SampleService(SampleMapper mapper, SampleConverter converter) {
    this.mapper = mapper;
    this.converter = converter;
  }

  public PageInfo<SampleRecord> getAll(int page, int size) {
    /* PageHelper 반환값을 사용하지 않는 패턴이 표준이라 소나큐브를 무시하는 주석으로 예외처리 */
    PageHelper.startPage(page, size); // NOSONAR
    List<SampleEntity> resultList = mapper.findAll();
    return new PageInfo<>(resultList.stream().map(converter::toRecord).toList());
  }

  public SampleRecord getById(int id) {
    return mapper
        .findById(id)
        .map(converter::toRecord) // 데이터가 있을 때만 Record로 변환
        .orElseThrow(() -> new BusinessException("해당 데이터를 찾을 수 없습니다.", "NOT_FOUND", 500));
  }

  public void add(SampleRecord dataRecord) {
    mapper.insert(converter.toEntity(dataRecord));
  }

  public void update(SampleRecord dataRecord) {
    mapper.update(converter.toEntity(dataRecord));
  }

  public void delete(int id) {
    mapper.delete(id);
  }
}
