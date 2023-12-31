package com.platform.utils.excelHandlers;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.util.List;
import java.util.Map;

/***
 * 级联下拉处理器
 */
public class CascadeWriteHandler implements SheetWriteHandler {
    private List<String> largeList;     //大类的字符串集合
    Map<String,List<String>> siteMap;   //大类和小类的对应关系的map集合

    private int parentIndex;
    private int sonIndex;

    private char[] alphabet = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public CascadeWriteHandler(List<String> largeList,
                               Map<String, List<String>> siteMap,
                               int parentIndex, int sonIndex) {
        this.largeList = largeList;
        this.siteMap = siteMap;
        this.parentIndex = parentIndex;
        this.sonIndex = sonIndex;
    }

    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {}


    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        //获取工作簿
        Sheet sheet = writeSheetHolder.getSheet();
        Workbook book = writeWorkbookHolder.getWorkbook();
        //创建一个专门存放地区信息的隐藏sheet页
        Sheet hideSheet = book.createSheet("site");
        book.setSheetHidden(book.getSheetIndex(hideSheet),true);

        //将具体数据写入每一行中，将开头为父级区域，后面为子区域
        int rowId=0;
        Row proviRow = hideSheet.createRow(rowId++);
        proviRow.createCell(0).setCellValue("大类列表");
        for(int i=0;i<largeList.size();i++){
            Cell proviCell = proviRow.createCell(i + 1);
            proviCell.setCellValue(largeList.get(i));
        }
        for (String key : siteMap.keySet()) {
            List<String> son = siteMap.get(key);
            Row row = hideSheet.createRow(rowId++);
            for (int i = 0; i < son.size(); i++) {
                Cell cell = row.createCell(i + 1);
                cell.setCellValue(son.get(i));
            }
            //添加名称管理器
            String range = getRange(1, rowId, son.size());
            Name name = book.createName();
            name.setNameName(key);
            String formula = "site!" + range;
            name.setRefersToFormula(formula);
        }

        //开始设置大小类下拉框
        DataValidationHelper dvHelper = sheet.getDataValidationHelper();
        //大类规则
        DataValidationConstraint expConstraint = dvHelper.createExplicitListConstraint(largeList.toArray(new String[0]));
        CellRangeAddressList expRangeAddressList = new CellRangeAddressList(1, 999, parentIndex, parentIndex);
        setValidation(sheet, dvHelper, expConstraint, expRangeAddressList, "提示", "你输入的值未在备选列表中，请下拉选择合适的值");


        // 小类规则(各单元格按个设置)
        // "INDIRECT($A$" + 2 + ")" 表示规则数据会从名称管理器中获取key与单元格 A2 值相同的数据，如果A2是浙江省，那么此处就是浙江省下面的市
        // 为了让每个单元格的公式能动态适应，使用循环挨个给公式。
        // 循环几次，就有几个单元格生效，次数要和上面的大类影响行数一一对应，要不然最后几个没对上的单元格实现不了级联
        for (int i = 2; i < 1000; i++) {
            CellRangeAddressList rangeAddressList = new CellRangeAddressList(i-1 , i-1, sonIndex, sonIndex);
            DataValidationConstraint formula = dvHelper.createFormulaListConstraint("INDIRECT($"+alphabet[parentIndex]+"$" + i + ")");
            setValidation(sheet, dvHelper, formula, rangeAddressList, "提示", "你输入的值未在备选列表中，请下拉选择合适的值");
        }
    }


    /**
     * 设置验证规则
     * @param sheet          sheet对象
     * @param helper      验证助手
     * @param constraint   createExplicitListConstraint
     * @param addressList  验证位置对象
     * @param msgHead     错误提示头
     * @param msgContext   错误提示内容
     */
    private void setValidation(Sheet sheet, DataValidationHelper helper, DataValidationConstraint constraint, CellRangeAddressList addressList, String msgHead, String msgContext) {
        DataValidation dataValidation = helper.createValidation(constraint, addressList);
        dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
        dataValidation.setShowErrorBox(true);
        dataValidation.setSuppressDropDownArrow(true);
        dataValidation.createErrorBox(msgHead, msgContext);
        sheet.addValidationData(dataValidation);
    }

    /***
     *
     * @param offset   偏移量，如果给0，表示从A列开始，1，就是从B列
     * @param rowId    第几行
     * @param colCount 一共多少列
     * @return 如果给入参 1,1,10. 表示从B1-K1。最终返回 $B$1:$K$1
     */
    public  String getRange(int offset,int rowId,int colCount){

        char start = (char) ('A' + offset);
        if (colCount <= 25) {
            char end = (char) (start + colCount - 1);
            return "$" + start + "$" + rowId + ":$" + end + "$" + rowId;
        } else {
            char endPrefix = 'A';
            char endSuffix = 'A';
            if ((colCount - 25) / 26 == 0 || colCount == 51) {// 26-51之间，包括边界（仅两次字母表计算）
                if ((colCount - 25) % 26 == 0) {// 边界值
                    endSuffix = (char) ('A' + 25);
                } else {
                    endSuffix = (char) ('A' + (colCount - 25) % 26 - 1);
                }
            } else {// 51以上
                if ((colCount - 25) % 26 == 0) {
                    endSuffix = (char) ('A' + 25);
                    endPrefix = (char) (endPrefix + (colCount - 25) / 26 - 1);
                } else {
                    endSuffix = (char) ('A' + (colCount - 25) % 26 - 1);
                    endPrefix = (char) (endPrefix + (colCount - 25) / 26);
                }
            }
            return "$" + start + "$" + rowId + ":$" + endPrefix + endSuffix + "$" + rowId;
        }
    }
}
