package com.platform.utils;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.write.handler.RowWriteHandler;
import com.alibaba.excel.write.handler.context.RowWriteHandlerContext;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.platform.annotations.CollectCustomMerge;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CustomMergeStrategy implements RowWriteHandler {
    private Integer pkIndex;

    private List<Integer> needMergeColumnIndex=new ArrayList<>();

    private Class<?> elementType;

    public CustomMergeStrategy(Class<?> elementType){
        this.elementType=elementType;
    }

    @Override
    public void beforeRowCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Integer rowIndex, Integer relativeRowIndex, Boolean isHead) {

    }

    @Override
    public void afterRowCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Integer relativeRowIndex, Boolean isHead) {

    }

    @Override
    public void afterRowDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Integer relativeRowIndex, Boolean isHead) {
        if(isHead){
            return;
        }

        Sheet sheet= writeSheetHolder.getSheet();

        Row titleRow= sheet.getRow(0);

        if(pkIndex==null){
            this.lazyInit(writeSheetHolder);
        }

        if(row.getRowNum()<=1){
            return;
        }

        Row lastRow = sheet.getRow(row.getRowNum() - 1);

        if(lastRow.getCell(pkIndex).getStringCellValue().equalsIgnoreCase(row.getCell(pkIndex).getStringCellValue())){
            for (Integer needMerIndex:needMergeColumnIndex){
                CellRangeAddress cellRangeAddress=new CellRangeAddress(row.getRowNum()-1, row.getRowNum(),needMerIndex,needMerIndex);
                sheet.addMergedRegionUnsafe(cellRangeAddress);
            }
        }
    }


    private void lazyInit(WriteSheetHolder writeSheetHolder){
        Sheet sheet = writeSheetHolder.getSheet();

        Row titleRow = sheet.getRow(0);

        Class<?> eleType=this.elementType;

        Field[] fields=eleType.getDeclaredFields();

        for(Field theField:fields){
            ExcelProperty easyExcelAnno=theField.getAnnotation(ExcelProperty.class);
            if(easyExcelAnno==null){
                continue;
            }

            CollectCustomMerge collectCustomMerge=theField.getAnnotation(CollectCustomMerge.class);
            if(collectCustomMerge==null){
                continue;
            }

            for (int index=0;index<fields.length;index++){
                Cell theCell=titleRow.getCell(index);
                if(theCell==null){
                    continue;
                }
                if(easyExcelAnno.value()[0].equalsIgnoreCase(theCell.getStringCellValue())){
                    if(collectCustomMerge.isPK()){
                        pkIndex=index;
                    }
                    if(collectCustomMerge.needMerge()){
                        needMergeColumnIndex.add(index);
                    }
                }
            }
        }
        if(this.pkIndex==null){
            throw new IllegalStateException("使用@CustomMerge必须指定主键");
        }
    }
}
