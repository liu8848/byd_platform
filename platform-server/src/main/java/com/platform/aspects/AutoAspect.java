package com.platform.aspects;

import com.platform.annotaionExtend.AutoFill;
import com.platform.constant.AutoFillConstant;
import com.platform.context.BaseContext;
import com.platform.entity.Employee;
import com.platform.enums.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class AutoAspect {
    @Pointcut("@annotation(com.platform.annotaionExtend.AutoFill)")
    public void autoFillPointCut(){}

    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint){
        log.info("填充公共字段");
        String name=joinPoint.getSignature().getName();

        MethodSignature signature= (MethodSignature) joinPoint.getSignature();
        AutoFill autoFill=signature.getMethod().getAnnotation(AutoFill.class);
        OperationType operationType=autoFill.value();
        Object[] args= joinPoint.getArgs();
        if(args==null||args.length==0) return;
        Object entity=args[0];

        LocalDateTime now=LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId().getEmployeeId();

        if(operationType==OperationType.INSERT){
            try{
                Method setCreateTime=entity.getClass().getMethod(AutoFillConstant.SET_CREATE_TIME,LocalDateTime.class);
                Method setCreateUser=entity.getClass().getMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateTime=entity.getClass().getMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser=entity.getClass().getMethod(AutoFillConstant.SET_UPDATE_USER,Long.class);

                setCreateTime.invoke(entity,now);
                setCreateUser.invoke(entity,currentId);
                setUpdateTime.invoke(entity,now);
                setUpdateUser.invoke(entity,currentId);
            }catch (NoSuchMethodException | IllegalAccessException| InvocationTargetException e){
                e.printStackTrace();
            }
        } else if (operationType==OperationType.UPDATE) {
            try {
                Method setUpdateUser = getClass().getMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                Method setUpdateTime= getClass().getMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);

                setUpdateTime.invoke(entity,now);
                setUpdateUser.invoke(entity,currentId);
            }catch (NoSuchMethodException|IllegalAccessException|InvocationTargetException e){
                e.printStackTrace();
            }
        }
    }
}
