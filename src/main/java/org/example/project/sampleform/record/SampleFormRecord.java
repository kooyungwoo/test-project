package org.example.project.sampleform.record;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;

public record SampleFormRecord(
    @Schema(description = "데이터 ID", example = "1") Integer dataId,
    @Schema(description = "제목", example = "제목") String title,
    @Schema(description = "내용", example = "내용") String content,
    @Schema(description = "상태", example = "등록") String status,
    @Schema(description = "출력 여부", example = "Y") String printable,
    @Schema(description = "등록일", example = "20251227", type = "string", format = "date")
        Date regDate,
    @Schema(description = "수정일", example = "20251227", type = "string", format = "date")
        Date modDate) {}
