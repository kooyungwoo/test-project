package org.example.project.sample.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.example.project.common.utils.ExcelUtils;
import org.example.project.sample.record.SampleMemberDto;
import org.example.project.sample.record.SampleRecord;
import org.example.project.sample.service.SampleService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/sample")
@Tag(name = "samples API", description = "샘플 관련 기능 제공")
public class SampleController {

  private final SampleService service;

  public SampleController(SampleService service) {
    this.service = service;
  }

  @Operation(summary = "샘플 조회", description = "샘플 정보를 조회합니다")
  @ApiResponse(responseCode = "200", description = "성공적으로 조회됨")
  @GetMapping
  public PageInfo<SampleRecord> getAll(
      @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
    return service.getAll(page, size);
  }

  @Operation(summary = "샘플 상세 조회", description = "샘플 상세 정보를 조회합니다")
  @ApiResponse(responseCode = "200", description = "성공적으로 조회됨")
  @GetMapping("/{id}")
  public SampleRecord getById(@PathVariable int id) {
    return service.getById(id);
  }

  @Operation(summary = "샘플 등록", description = "샘플 정보를 등록합니다")
  @ApiResponse(responseCode = "200", description = "성공적으로 등록됨")
  @PostMapping
  public void add(@RequestBody SampleRecord dataRecord) {
    service.add(dataRecord);
  }

  @Operation(summary = "샘플 수정", description = "샘플 정보를 수정합니다")
  @ApiResponse(responseCode = "200", description = "성공적으로 수정됨")
  @PutMapping("/{id}")
  public void update(@PathVariable int id, @RequestBody SampleRecord dataRecord) {
    service.update(new SampleRecord(id, dataRecord.name(), dataRecord.email()));
  }

  @Operation(summary = "샘플 삭제", description = "샘플 정보를 삭제합니다")
  @ApiResponse(responseCode = "200", description = "성공적으로 삭제됨")
  @DeleteMapping("/{id}")
  public void delete(@PathVariable int id) {
    service.delete(id);
  }

  @GetMapping("/excel/download-sapmple")
  public void downloadSampleUserExcel(HttpServletResponse response) throws IOException {
    // 데이터 조회
    List<SampleMemberDto> dataList = service.findAllForSampleMember();

    log.debug("dataList: {}", dataList);

    // 유틸리티 호출
    ExcelUtils.download(response, SampleMemberDto.class, dataList, "샘플목록", "샘플 사용자 리스트");
  }
}
