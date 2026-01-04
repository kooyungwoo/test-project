package org.example.project.common.converter;

import org.example.project.common.entity.CommonCodeEntity;
import org.example.project.common.record.CommonCodeRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommonConverter {

  CommonCodeRecord toCommonCodeRecord(CommonCodeEntity entity);

  CommonCodeEntity toCommonCodeEntity(CommonCodeRecord dataRecord);
}
