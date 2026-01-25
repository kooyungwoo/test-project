package org.example.project.sample.converter;

import org.example.project.sample.entity.SampleEntity;
import org.example.project.sample.entity.SampleMemberEntity;
import org.example.project.sample.record.SampleMemberDto;
import org.example.project.sample.record.SampleRecord;
import org.example.project.sample.utils.MemberGrade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface SampleConverter {

  SampleRecord toRecord(SampleEntity entity);

  SampleEntity toEntity(SampleRecord dataRecord);

  @Mapping(target = "memberGrade", source = "memberGrade", qualifiedByName = "enumToString")
  @Mapping(target = "joinDate", source = "joinDate", dateFormat = "yyyy-MM-dd")
  @Mapping(target = "lastLoginAt", source = "lastLoginAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
  SampleMemberDto toDtoBySampleMemberEntity(SampleMemberEntity entity);

  @Named("enumToString")
  default String enumToString(MemberGrade grade) {
    return grade != null ? grade.name() : null;
  }
}
