package org.example.project.sampleform.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.example.project.sampleform.entity.SampleFormEntity;

@Mapper
public interface SampleFormMapper {
  List<SampleFormEntity> findAll();

  SampleFormEntity findById(int id);

  void insert(SampleFormEntity entity);

  void update(SampleFormEntity entity);

  void delete(int id);
}
