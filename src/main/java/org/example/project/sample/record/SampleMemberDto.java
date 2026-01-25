package org.example.project.sample.record;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ColumnWidth(20)
public class SampleMemberDto {

  @ColumnWidth(10)
  @ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER)
  @ExcelProperty({"기본정보", "회원ID"})
  private Integer id;

  @ColumnWidth(15)
  @ExcelProperty({"기본정보", "성명"})
  private String userName;

  @ExcelProperty({"기본정보", "이메일"})
  private String email;

  @ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.RIGHT)
  @ExcelProperty({"기본정보", "휴대전화"})
  private String phone;

  @ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER)
  @ExcelProperty({"기본정보", "가입일자"})
  private String joinDate;

  @ExcelProperty({"추가정보", "회원등급"})
  private String memberGrade;

  @NumberFormat("#,##0.00")
  @ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.RIGHT)
  @ExcelProperty({"추가정보", "누적 구매액"})
  private BigDecimal totalSpend;

  @ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.RIGHT)
  @ExcelProperty({"추가정보", "포인트"})
  private Integer points;

  @ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER)
  @ExcelProperty({"추가정보", "마지막 접속일"})
  private String lastLoginAt;

  @ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER)
  @ExcelProperty({"추가정보", "마케팅 동의"})
  private String marketingAgree;
}
