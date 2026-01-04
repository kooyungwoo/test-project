package org.example.project.sampleform.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.project.common.record.ResultRecord;
import org.example.project.sampleform.record.SampleFormRecord;
import org.example.project.sampleform.service.SampleFormService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sample-form")
@Tag(name = "samples form API", description = "샘플 폼 관련 기능 제공")
public class SampleFormController {

  private final SampleFormService service;

  public SampleFormController(SampleFormService service) {
    this.service = service;
  }

  @Operation(summary = "샘플 조회", description = "샘플 정보를 조회합니다")
  @ApiResponse(responseCode = "200", description = "성공적으로 조회됨")
  @GetMapping
  public PageInfo<SampleFormRecord> getAll(
      @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
    return service.getAll(page, size);
  }

  @Operation(summary = "샘플 상세 조회", description = "샘플 상세 정보를 조회합니다")
  @ApiResponse(responseCode = "200", description = "성공적으로 조회됨")
  @GetMapping("/{dataId}")
  public SampleFormRecord getById(@PathVariable int dataId) {
    return service.getById(dataId);
  }

  @Operation(summary = "샘플 저장", description = "샘플 정보를 저장(등록,수정)합니다")
  @ApiResponse(responseCode = "200", description = "성공적으로 저장됨")
  @PostMapping
  public ResultRecord add(@RequestBody SampleFormRecord dataRecord) {
    service.save(dataRecord);
    return new ResultRecord(true, "저장이 완료 되었습니다.");
  }

  @Operation(summary = "샘플 수정", description = "샘플 정보를 수정합니다")
  @ApiResponse(responseCode = "200", description = "성공적으로 수정됨")
  @PutMapping("/{dataId}")
  public ResultRecord update(@PathVariable int dataId, @RequestBody SampleFormRecord dataRecord) {
    // Record는 빌더 형식을 권장하지 않음 생성자 이용 필요 record는 자동으로 모든 필드를 받는 canonical constructor를 생성
    service.update(
        new SampleFormRecord(
            dataId,
            dataRecord.title(),
            dataRecord.content(),
            dataRecord.status(),
            dataRecord.printable(),
            null,
            null));
    return new ResultRecord(true, "수정이 완료 되었습니다.");
  }

  @Operation(summary = "샘플 삭제", description = "샘플 정보를 삭제합니다")
  @ApiResponse(responseCode = "200", description = "성공적으로 삭제됨")
  @DeleteMapping("/{dataId}")
  public ResultRecord delete(@PathVariable int dataId) {
    service.delete(dataId);
    return new ResultRecord(true, "삭제가 완료 되었습니다.");
  }
}
