package org.example.project.common.record;

public record FileResponse(
    String fileId,
    String originalName,
    String savedName,
    String uploadPath,
    String extension,
    long size,
    String contentType) {}
