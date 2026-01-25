package org.example.project.common.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.example.project.common.entity.FileEntity;

@Mapper
public interface FileMapper {
  List<FileEntity> findFileAll();

  int insert(FileEntity fileEntity);

  int update(FileEntity fileEntity);

  int updateDelYn(FileEntity fileEntity);

  int updateDownloadCount(FileEntity fileEntity);
}
