package org.example.project.common.record;

public record ResultResponse(boolean success, String message) {
  public ResultResponse() {
    this(true, "성공");
  }
}
