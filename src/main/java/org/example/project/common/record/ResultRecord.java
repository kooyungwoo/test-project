package org.example.project.common.record;

public record ResultRecord(boolean success, String message) {
  public ResultRecord() {
    this(true, "성공");
  }
}
