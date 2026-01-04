package org.example.project.sample.converter;

import org.example.project.sample.entity.SampleEntity;
import org.example.project.sample.record.SampleRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SampleConverter {

  SampleRecord toRecord(SampleEntity entity);

  SampleEntity toEntity(SampleRecord dataRecord);
}
