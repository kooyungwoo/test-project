package org.example.project.common.utils;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

public final class ExcelUtils {

  // 소나큐브 대응: 인스턴스화 방지를 위한 private 생성자
  private ExcelUtils() {
    throw new IllegalStateException("Utility class");
  }

  public static <T> void download(
      HttpServletResponse response,
      Class<T> clazz,
      List<T> dataList,
      String fileName,
      String sheetName)
      throws IOException {

    // 1. 응답 헤더 설정
    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    response.setCharacterEncoding("utf-8");
    String encodedFileName =
        URLEncoder.encode(fileName, StandardCharsets.UTF_8).replace("+", "%20");
    response.setHeader(
        "Content-disposition", "attachment;filename*=utf-8''" + encodedFileName + ".xlsx");

    // 2. 공통 스타일 설정
    WriteCellStyle headWriteCellStyle = new WriteCellStyle();
    headWriteCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());

    WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
    contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);
    contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);
    contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
    contentWriteCellStyle.setBorderTop(BorderStyle.THIN);

    HorizontalCellStyleStrategy styleStrategy =
        new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

    // 3. 엑셀 쓰기 실행
    EasyExcelFactory.write(response.getOutputStream(), clazz)
        .registerWriteHandler(styleStrategy)
        .sheet(sheetName)
        .doWrite(dataList);
  }
}
