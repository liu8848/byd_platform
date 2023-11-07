package com.platform.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson.JSON;
import com.platform.dto.auditors.AuditorCreateDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ExcelDataListener implements ReadListener<AuditorCreateDTO> {
    private static final int BATCH_COUNT=100;

    private List<AuditorCreateDTO> cachedDataList= ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    @Override
    public void invoke(AuditorCreateDTO auditorCreateDTO, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", JSON.toJSONString(auditorCreateDTO));
        cachedDataList.add(auditorCreateDTO);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("解析完成");
        System.out.println(cachedDataList);
    }
}
