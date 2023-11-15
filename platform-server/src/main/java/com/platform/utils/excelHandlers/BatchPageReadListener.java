package com.platform.utils.excelHandlers;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.util.ListUtils;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Slf4j
public class BatchPageReadListener<T> extends PageReadListener<T> {
    private List<T> cachedDataList= ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    private final Consumer<List<T>> consumer;

    public BatchPageReadListener(Consumer<List<T>> consumer, Consumer<List<T>> consumer1) {
        super(consumer);
        this.consumer = consumer1;
    }

    @Override
    public void invoke(T data, AnalysisContext context) {
        //当该行数据为空，则不装载
        if(isLineNullValue(data)){
            return;
        }
        cachedDataList.add(data);
        if(cachedDataList.size()>=BATCH_COUNT){
            consumer.accept(cachedDataList);
            cachedDataList=ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        if(ObjectUtils.isEmpty(cachedDataList)){
            return;
        }
        consumer.accept(cachedDataList);
    }

    private boolean isLineNullValue(T data){
        if(data instanceof String){
            return StringUtil.isNullOrEmpty((String) data);
        }
        try{
            List<Field> fields= Arrays.stream(data.getClass().getDeclaredFields())
                    .filter(f->f.isAnnotationPresent(ExcelProperty.class))
                    .toList();
            List<Boolean> lineNullList=new ArrayList<>(fields.size());
            for(Field field:fields){
                field.setAccessible(true);
                Object value = field.get(data);
                if(ObjectUtils.isEmpty(value)){
                    lineNullList.add(Boolean.TRUE);
                }else {
                    lineNullList.add(Boolean.FALSE);
                }
            }
            return lineNullList.stream().allMatch(Boolean.TRUE::equals);
        }catch (Exception e){
            log.error("读取数据行[{}]解析失败：{}",data,e.getMessage());
        }
        return true;
    }
}
