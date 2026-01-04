package org.example.project.samplegrid.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sample-grid")
@Tag(name = "samples grid API", description = "샘플 그리드 관련 기능 제공")
public class SampleGridController {

  @Operation(summary = "샘플 조회", description = "샘플 정보를 조회합니다")
  @ApiResponse(responseCode = "200", description = "성공적으로 조회됨")
  @GetMapping
  public List<Map<String, Object>> getAll() throws JsonProcessingException {
    String json =
        "["
            + "{ \"name\": \"App1\", \"version\": \"1.0\", \"status\": \"Active\" },"
            + "{ \"name\": \"App2\", \"version\": \"2.1\", \"status\": \"Inactive\" },"
            + "{ \"name\": \"App3\", \"version\": \"3.0\", \"status\": \"Active\" }"
            + "]";

    // 문자열 → List<Map<String,Object>> 변환
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(json, new TypeReference<>() {});
  }
}
