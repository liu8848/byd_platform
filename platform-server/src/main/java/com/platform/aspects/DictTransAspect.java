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
import java.util.Map;

@Aspect
@Component
@Slf4j
public class DictTransAspect {

    @Autowired
    private RedisUtil redisUtil;


    @Around("@annotation(dictHelper)")
    public Object doAround(ProceedingJoinPoint joinPoint,DictHelper dictHelper)throws Throwable{

        try{
            Object result=joinPoint.proceed();

            DictParam[] values= dictHelper.value();
            if(values == null){
                return result;
            }

            for(DictParam value:values){
                Class<?> clazz = result.getClass();
                Field sourceField = clazz.getDeclaredField(value.field());
                sourceField.setAccessible(true);
                Object fieldValue = sourceField.get(result);

                Map<String, String> factoryMap = DictUtil.dictMap.get(value.dictType());
                String targetValue = factoryMap.getOrDefault(fieldValue.toString(), "");

                Field targetField = clazz.getDeclaredField(value.targetField());
                targetField.setAccessible(true);
                targetField.set(result,targetValue);
            }
            return result;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    private String firstToUppercase(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }


}
