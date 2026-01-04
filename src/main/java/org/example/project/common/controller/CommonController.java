package org.example.project.common.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.example.project.common.record.CommonCodeRecord;
import org.example.project.common.service.CommonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/common")
@Tag(name = "common API", description = "공통 관련 기능 제공")
public class CommonController {

  private final CommonService service;

  public CommonController(CommonService service) {
    this.service = service;
  }

  @Operation(summary = "공통코드 조회", description = "공통코드 정보를 조회합니다")
  @ApiResponse(responseCode = "200", description = "성공적으로 조회됨")
  @GetMapping("/common-codes")
  public List<CommonCodeRecord> getCommonCodeAll() {
    return service.getCommonCodeAll();
  }
}
