package org.example.project.common.exception;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.example.project.common.record.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  // 1. 커스텀 비즈니스 예외 처리 (직접 던진 에러)
  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {

    ErrorResponse response = ErrorResponse.of(e.getStatus(), e.getErrorCode(), e.getMessage());

    // ResponseEntity 통한 응답 반환, 상태 코드도 함께 설정
    return ResponseEntity.status(e.getStatus()).body(response);
  }

  // 2. 유효성 검사 실패 시 (Validation 에러)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationException(
      MethodArgumentNotValidException e) {
    // 1. 모든 필드 에러 정보를 ValidationError record로 변환
    List<ErrorResponse.ValidationError> errors =
        e.getBindingResult().getFieldErrors().stream()
            .map(
                error ->
                    new ErrorResponse.ValidationError(
                        error.getField(),
                        String.valueOf(error.getRejectedValue()),
                        error.getDefaultMessage() // record에 정의한 message를 가져옴
                        ))
            .toList();

    // 2. 공통 응답 객체 생성
    ErrorResponse response =
        ErrorResponse.of(
            HttpStatus.BAD_REQUEST.value(), "INVALID_INPUT", "입력값이 유효하지 않습니다.", errors);

    return ResponseEntity.badRequest().body(response);
  }

  // 3. 그 외 예상치 못한 모든 에러 처리 (500 에러)
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleAllException(Exception e) {

    log.error("서버 내부 오류가 발생했습니다.", e);

    ErrorResponse response =
        ErrorResponse.of(
            HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL_SERVER_ERROR", "서버 내부 오류가 발생했습니다.");

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }
}
