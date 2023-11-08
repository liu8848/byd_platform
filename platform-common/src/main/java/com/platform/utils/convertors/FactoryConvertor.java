package com.platform.utils.convertors;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.platform.constant.DictKeyConstant;
import com.platform.utils.DictUtil;

public class FactoryConvertor implements Converter<Long> {
    @Override
    public Class<?> supportJavaTypeKey() {
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Long convertToJavaData(ReadConverterContext<?> context) throws Exception {
        return DictUtil.getDictValue(DictKeyConstant.FACTORY,context.getReadCellData().getStringValue());
    }

    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<Long> context) throws Exception {
        return new WriteCellData<>(DictUtil.getDictName(DictKeyConstant.FACTORY,context.getValue()));
    }
}
