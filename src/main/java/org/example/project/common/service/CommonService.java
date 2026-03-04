package org.example.project.common.service;

import java.util.List;
import org.example.project.common.converter.CommonConverter;
import org.example.project.common.mapper.CommonMapper;
import org.example.project.common.record.CommonCodeRecord;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

  private final CommonMapper mapper;
  private final CommonConverter converter;

  public CommonService(CommonMapper mapper, CommonConverter converter) {
    this.mapper = mapper;
    this.converter = converter;
  }

  /**
   * 공통코드 전체 조회.
   *
   * @return 공통코드 레코드 목록
   */
  public List<CommonCodeRecord> getCommonCodeAll() {
    return mapper.findCommonCodeAll().stream().map(converter::toCommonCodeRecord).toList();
  }
}
