package com.platform.utils.excelHandlers;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import com.platform.commonModel.Dictionary;
import com.platform.utils.DictUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.util.List;

public class DownListSheetWriteHandler implements SheetWriteHandler {

    private static final int FIRST_ROW=1;
    private static final int LAST_ROW=10000;
    private String sheetName;
    private String dictKey;
    private int columnIndex;
    private String columnLabel;

    public DownListSheetWriteHandler(){}

    public DownListSheetWriteHandler(String sheetName,String dictKey,int columnIndex,String columnLabel){
        this.sheetName=sheetName;
        this.dictKey=dictKey;
        this.columnIndex=columnIndex;
        this.columnLabel=columnLabel;
    }


    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder,
                                 WriteSheetHolder writeSheetHolder) {

        Workbook workbook = writeWorkbookHolder.getWorkbook();

        Sheet workbookSheet = workbook.createSheet(sheetName);

        workbook.setSheetHidden(workbook.getSheetIndex(sheetName),true);

        List<Dictionary> buList = DictUtil.dictMap.get(dictKey).values().stream().toList();
        for(int i=0;i<buList.size();i++){
            Dictionary dictionary = buList.get(i);
            Row row = workbookSheet.createRow(i);
            row.createCell(0).setCellValue(dictionary.getDictName());
            row.createCell(1).setCellValue(dictionary.getDictValue());

            Name workbookName = workbook.createName();

            workbookName.setNameName("_"+dictionary.getDictName());
            workbookName.setRefersToFormula(sheetName+"!$B$"+(i+1));
        }


        DataValidationHelper dataValidationHelper = writeSheetHolder.getSheet().getDataValidationHelper();
        CellRangeAddressList nameRange = new CellRangeAddressList(FIRST_ROW, LAST_ROW, columnIndex, columnIndex);
        DataValidationConstraint nameConstraint = dataValidationHelper.createFormulaListConstraint(sheetName + "!$A$1:$A$" + (buList.size() + 1)); // 数据源的第一列
        DataValidation nameValidation = dataValidationHelper.createValidation(nameConstraint, nameRange);
        nameValidation.setShowErrorBox(true);
        nameValidation.createErrorBox("错误", "请选择正确的姓名");
        writeSheetHolder.getSheet().addValidationData(nameValidation);

        CellRangeAddressList idRange = new CellRangeAddressList(FIRST_ROW, LAST_ROW, columnIndex+1, columnIndex+1);
        DataValidationConstraint idConstraint = dataValidationHelper.createFormulaListConstraint("=INDIRECT(\"_\"&$"+columnLabel+"2)"); // 函数加入下划线
        DataValidation idValidation = dataValidationHelper.createValidation(idConstraint, idRange);
        idValidation.setShowErrorBox(true);
        idValidation.createErrorBox("错误", "请选择正确的id");
        writeSheetHolder.getSheet().addValidationData(idValidation);

    }
}
