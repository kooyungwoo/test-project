package org.example.project.sample.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.example.project.sample.utils.MemberGrade;

@Getter
@Setter
public class SampleMemberEntity {

  private Integer id;
  private String userName;
  private String email;
  private String phone;
  private LocalDate joinDate;
  private MemberGrade memberGrade;
  private BigDecimal totalSpend;
  private Integer points;
  private LocalDateTime lastLoginAt;
  private String marketingAgree;
}
