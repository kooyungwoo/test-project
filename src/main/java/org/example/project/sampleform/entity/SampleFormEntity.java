package org.example.project.sampleform.entity;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class SampleFormEntity {
  private Integer dataId;
  private String title;
  private String content;
  private String status;
  private String printable;
  private Date regDate;
  private Date modDate;
}
