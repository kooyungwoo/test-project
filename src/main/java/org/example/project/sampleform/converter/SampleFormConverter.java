package org.example.project.sampleform.converter;

import org.example.project.sampleform.entity.SampleFormEntity;
import org.example.project.sampleform.record.SampleFormRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SampleFormConverter {

  SampleFormRecord toRecord(SampleFormEntity entity);

  SampleFormEntity toEntity(SampleFormRecord dataRecord);
}
