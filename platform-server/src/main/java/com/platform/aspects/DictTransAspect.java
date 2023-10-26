package com.platform.aspects;

import com.platform.annotaionExtend.DictHelper;
import com.platform.annotaionExtend.DictParam;
import com.platform.utils.DictUtil;
import com.platform.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class DictTransAspect {

    @Autowired
    private RedisUtil redisUtil;


    @Around("@annotation(dictHelper)")
    public Object doAround(ProceedingJoinPoint joinPoint,DictHelper dictHelper)throws Throwable{

        log.info("--------开始字典转换------------");
        try{
            Object result=joinPoint.proceed();

            DictParam[] values= dictHelper.value();
            if(values == null){
                return result;
            }
            if(result instanceof List){
                for (var r:(List<?>)result) {
                    trans(r,values);
                }
            }else
                trans(result,values);
            return result;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    private void trans(Object r,DictParam[] values)throws Throwable{
        for(DictParam value:values){
            Class<?> clazz = r.getClass();
            Field sourceField = clazz.getDeclaredField(value.field());
            sourceField.setAccessible(true);
            Object fieldValue = sourceField.get(r);

            Map<String, String> factoryMap = DictUtil.dictMap.get(value.dictType());
            String targetValue = factoryMap.getOrDefault(fieldValue.toString(), "");

            Field targetField = clazz.getDeclaredField(value.targetField());
            targetField.setAccessible(true);
            targetField.set(r,targetValue);
        }
    }

    private String firstToUppercase(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }


}
