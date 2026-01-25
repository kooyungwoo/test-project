package org.example.project.common.converter;

import org.example.project.common.entity.FileEntity;
import org.example.project.common.record.FileRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileConverter {

  FileRecord toCommonCodeRecord(FileEntity entity);

  FileEntity toCommonCodeEntity(FileRecord dataRecord);
}
