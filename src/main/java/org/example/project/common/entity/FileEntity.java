package org.example.project.common.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 파일 정보를 표현하는 엔티티 클래스.
 *
 * <p>파일의 식별자, 원본 이름, 저장된 이름, 경로, 확장자, 크기, 컨텐츠 타입 등 업로드된 파일과 관련된 메타데이터를 보관합니다.
 *
 * <p>주로 서비스 레이어와 데이터 레이어 사이에서 파일 정보를 전달하는 용도로 사용됩니다.
 */
@Getter
@Setter
@Builder
@ToString
public class FileEntity {
  /** 파일 ID (고유 식별자). 예: DB에 저장된 파일 PK 또는 UUID. */
  private String fileId;

  /** 참조 타입: 파일이 속한 도메인(예: "USER", "POST", "ATTACHMENT" 등). */
  private String refType;

  /** 참조 ID: refType에 해당하는 엔티티의 식별자. */
  private String refId;

  /** 업로드된 원본 파일명 (사용자에게 보여주는 이름). */
  private String originName;

  /** 저장된 파일명 (서버에 저장된 실제 파일명). */
  private String savedName;

  /** 파일이 저장된 경로 (서버 내부 경로 또는 외부 스토리지의 경로). */
  private String filePath;

  /** 파일 확장자 (예: "jpg", "png", "pdf"). */
  private String extension;

  /** 파일 크기 (바이트 단위 또는 사람이 읽기 쉬운 문자열). */
  private String fileSize;

  /** MIME 타입 (예: "image/png", "application/pdf"). */
  private String contentType;

  /** 파일 다운로드 횟수. */
  private String downloadCount;

  /** 삭제 여부 (예: "Y" 또는 "N"). */
  private String delYn;
}
