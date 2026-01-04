package org.example.project.common.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.example.project.common.entity.CommonCodeEntity;

@Mapper
public interface CommonMapper {
  List<CommonCodeEntity> findCommonCodeAll();
}
