package org.example.project.sampleform.record;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import org.example.project.common.record.FileRecord;

public record SampleFileFormRecord(
    @Schema(description = "데이터 ID", example = "1") Integer dataId,
    @Schema(description = "제목", example = "제목") String title,
    @Schema(description = "참조시스템 KEY", example = "SAMPLE") String refType,
    @Schema(description = "첨부파일목록", example = "첨부파일") List<FileRecord> uploadedFiles) {}
