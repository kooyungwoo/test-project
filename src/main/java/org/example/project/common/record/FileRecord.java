package org.example.project.common.record;

import io.swagger.v3.oas.annotations.media.Schema;

public record FileRecord(
    @Schema(description = "파일 ID", example = "1") String fileId,
    @Schema(description = "참조 시스템 KEY", example = "SAMPLE") String refType,
    @Schema(description = "참조 ID ", example = "1") String refId,
    @Schema(description = "업로드 원본 파일명", example = "TEST.jpeg") String originName,
    @Schema(description = "저장 파일명", example = "TEST.JPEG") String savedName,
    @Schema(description = "파일경로", example = "/temp/SAMPLE") String filePath,
    @Schema(description = "확장자", example = ".jpeg") String extension,
    @Schema(description = "파일사이즈", example = "10") String fileSize,
    @Schema(description = "MIME타입", example = "image/jpeg") String contentType,
    @Schema(description = "다운로드 횟수", example = "0") String downloadCount,
    @Schema(description = "삭제여부", example = "N") String delYn) {}
