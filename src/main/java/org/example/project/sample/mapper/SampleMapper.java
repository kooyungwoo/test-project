package org.example.project.sample.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.example.project.sample.entity.SampleEntity;

@Mapper
public interface SampleMapper {
  List<SampleEntity> findAll();

  SampleEntity findById(int id);

  void insert(SampleEntity entity);

  void update(SampleEntity entity);

  void delete(int id);
}
