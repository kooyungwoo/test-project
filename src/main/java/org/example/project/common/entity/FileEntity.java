package org.example.project.common.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class FileEntity {
  private String fileId;
  private String refType;
  private String refId;
  private String originName;
  private String savedName;
  private String filePath;
  private String extension;
  private String fileSize;
  private String contentType;
  private String downloadCount;
  private String delYn;
}
