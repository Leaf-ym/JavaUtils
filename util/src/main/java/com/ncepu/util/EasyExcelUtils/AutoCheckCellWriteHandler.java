package com.ncepu.util.EasyExcelUtils;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;

import java.util.List;

/**
 * @author wengym
 * @version 1.0
 * @desc 自动检查表格写入处理器
 * @date 2022/10/21 13:43
 */
public class AutoCheckCellWriteHandler implements CellWriteHandler {

    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Head head, Integer integer, Integer integer1, Boolean aBoolean) {

    }

    @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell, Head head, Integer integer, Boolean aBoolean) {

    }

    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<WriteCellData<?>> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        int columnIndex = cell.getColumnIndex();
        int rowIndex = cell.getRowIndex();
        if (writeSheetHolder.getSheetNo() == 0 && rowIndex >= 1) {
            if(columnIndex == 3){
                String stringCellValue = cell.getStringCellValue();
                if (StringUtils.isNotBlank(stringCellValue)) {
                    stringCellValue = stringCellValue.replace("%", "");
                    int finishSchedule = Integer.parseInt(stringCellValue);
                    if (finishSchedule < 100) {
                        Workbook workbook = writeSheetHolder.getSheet().getWorkbook();
                        CellStyle cellStyle = workbook.createCellStyle();
                        //设置单元格边框类型
                        cellStyle.setBorderTop(BorderStyle.THIN);
                        cellStyle.setBorderBottom(BorderStyle.THIN);
                        cellStyle.setBorderLeft(BorderStyle.THIN);
                        cellStyle.setBorderRight(BorderStyle.THIN);
                        //水平居中
                        cellStyle.setAlignment(HorizontalAlignment.CENTER);
                        //垂直居中
                        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                        //字体设置为红色
                        Font font = workbook.createFont();
                        font.setColor(IndexedColors.RED.index);
                        cellStyle.setFont(font);
                        cell.setCellStyle(cellStyle);
                    }
                }
            }
        }
    }
}