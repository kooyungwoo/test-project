package org.example.project.common.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 공통 코드 정보를 나타내는 엔티티 클래스.
 *
 * <p>공통 코드의 코드값, 그룹 코드, 이름, 값, 노출 여부 등의 메타데이터를 보관합니다.
 *
 * <p>주로 화면 표시용 옵션이나 코드 테이블 매핑 등에 사용됩니다.
 */
@Getter
@Setter
public class CommonCodeEntity {
  /** 공통 코드값. */
  private String commonCd;

  /** 공통 그룹 코드. */
  private String commonGroupCd;

  /** 공통 코드의 표시 이름. */
  private String commonName;

  /** 공통 코드에 대응하는 실제 값. */
  private String commonValue;

  /** 화면 노출 여부 (예: "Y" 또는 "N"). */
  private String viewYn;
}
