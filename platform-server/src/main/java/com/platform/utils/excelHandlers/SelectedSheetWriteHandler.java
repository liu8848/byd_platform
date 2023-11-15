package com.platform.utils.excelHandlers;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import com.platform.commonModel.Dictionary;
import lombok.Getter;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.util.List;
import java.util.Map;

/***
 * 处理选定工作表
 */
public class SelectedSheetWriteHandler implements SheetWriteHandler {

    @Getter
    private final Map<Integer, List<Dictionary>> selectedMap;

    public SelectedSheetWriteHandler(Map<Integer, List<Dictionary>> selectedMap) {
        this.selectedMap = selectedMap;
    }

    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {}


    private char[] alphabet = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};


    /***
     * 将数字列转化为字母列
     * @param num 数字
     * @return 字母
     */
    private String getExcelColumn(int num){
        String column="";
        int len=alphabet.length-1;
        int first=num/len;
        int second=num%len;
        if(num<=len){
            column=alphabet[num]+"";
        }else {
            column=alphabet[first-1]+"";
            if(second==0){
                column=column+alphabet[len]+"";
            }else {
                column=column+alphabet[second-1]+"";
            }
        }
        return column;
    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        if(selectedMap==null|| selectedMap.isEmpty()){
            return;
        }
        //需要设置下拉框的sheet页
        Sheet curSheet = writeSheetHolder.getSheet();
        DataValidationHelper helper = curSheet.getDataValidationHelper();
        String dictSheetName="sheet2";
        Workbook workbook = writeWorkbookHolder.getWorkbook();

        //数据字典sheet页
        Sheet dictSheet = workbook.createSheet(dictSheetName);
        //隐藏数据字典的sheet页
        workbook.setSheetHidden(workbook.getSheetIndex(dictSheet),true);
        for (Map.Entry<Integer,List<Dictionary>>entry: selectedMap.entrySet()){
            //设置下拉单元格的首行、末行、首列、末列
            CellRangeAddressList rangeAddressList = new CellRangeAddressList(1,65533,entry.getKey(),entry.getKey());
            int rowLen = entry.getValue().size();
            //设置字典sheet页的值 每一列一个字典项
            for (int i=0;i<rowLen;i++){
                Row row = dictSheet.getRow(i);
                if(row==null){
                    row = dictSheet.createRow(i);
                }
                //在key列写入字典名
                row.createCell(entry.getKey()).setCellValue(entry.getValue().get(i).getDictName());
                //在key+1列写入字典值
                row.createCell(entry.getKey()+1).setCellValue(entry.getValue().get(i).getDictValue());

                //创建名称管理器
                Name dictName= workbook.createName();
                dictName.setNameName("_"+entry.getValue().get(i).getDictName());
                dictName.setRefersToFormula(dictSheetName+"!$"+alphabet[entry.getKey()+1]+"$"+(i+1));
            }

            String excelColumn = getExcelColumn(entry.getKey());
            //下拉框数据来源 eg:字典sheet!$B1:$B2
            String refers=dictSheetName+"!$"+excelColumn+"$1:$"+excelColumn+"$"+rowLen;
            //创建可被其他单元格引用的名称
            Name name = workbook.createName();
            //设置名称的名字
            name.setNameName("dict"+entry.getKey());
            //设置公式
            name.setRefersToFormula(refers);
            //设置引用约束
            DataValidationConstraint constraint = helper.createFormulaListConstraint("dict" + entry.getKey());
            //设置约束
            DataValidation validation = helper.createValidation(constraint, rangeAddressList);
            if(validation instanceof HSSFDataValidation){
                validation.setSuppressDropDownArrow(false);
            }else {
                validation.setSuppressDropDownArrow(true);
                validation.setShowErrorBox(true);
            }
            //阻止输入非下拉框值
            validation.setErrorStyle(DataValidation.ErrorStyle.STOP);
            validation.createErrorBox("提示","此值与单元格定义格式不一致");
            //添加下拉框约束
            writeSheetHolder.getSheet().addValidationData(validation);


            //添加字典值与字典名称联动
            DataValidationHelper dataValidationHelper = writeSheetHolder.getSheet().getDataValidationHelper();
            CellRangeAddressList idRange = new CellRangeAddressList(1, 65535, entry.getKey()+1, entry.getKey()+1);
            DataValidationConstraint idConstraint = dataValidationHelper.createFormulaListConstraint("=INDIRECT(\"_\"&$"+alphabet[entry.getKey()]+"2)"); // 函数加入下划线
            DataValidation idValidation = dataValidationHelper.createValidation(idConstraint, idRange);
            idValidation.setShowErrorBox(true);
            idValidation.createErrorBox("错误", "请选择正确的id");
            writeSheetHolder.getSheet().addValidationData(idValidation);
        }
    }
}
