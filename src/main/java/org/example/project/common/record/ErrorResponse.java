package org.example.project.common.record;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
    LocalDateTime timestamp,
    int status,
    String code,
    String message,
    List<ValidationError> errors) {
  // 일반 에러용 생성자
  public static ErrorResponse of(int status, String code, String message) {
    return new ErrorResponse(LocalDateTime.now(), status, code, message, List.of());
  }

  // Validation 에러용 생성자
  public static ErrorResponse of(
      int status, String code, String message, List<ValidationError> errors) {
    return new ErrorResponse(LocalDateTime.now(), status, code, message, errors);
  }

  // 필드별 에러를 담을 내부 record
  public record ValidationError(String field, String value, String message) {}
}
