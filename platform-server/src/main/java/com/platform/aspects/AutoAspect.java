package com.platform.aspects;

import cn.hutool.extra.spring.SpringUtil;
import com.platform.annotaionExtend.AutoFill;
import com.platform.annotaionExtend.FieldNameFill;
import com.platform.constant.AutoFillConstant;
import com.platform.context.BaseContext;
import com.platform.enums.FieldType;
import com.platform.enums.OperationType;
import com.platform.mapper.EmployeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.List;

@Aspect
@Component
@Slf4j
public class AutoAspect {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Pointcut("@annotation(com.platform.annotaionExtend.AutoFill)")
    public void autoFillPointCut() {
    }

    @Pointcut("@annotation(com.platform.annotaionExtend.FieldNameFill)")
    public void FieldNameFill(){}


    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) {
        log.info("填充公共字段");
        String name = joinPoint.getSignature().getName();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);
        OperationType operationType = autoFill.value();
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) return;
        Object entity = args[0];

        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId().getEmployeeId();
        if(entity instanceof List<?>){
            for (var e:(List<?>)entity) {
                fillField(e,operationType,now);
            }
        }else {
            fillField(entity,operationType,now);
        }
    }

    @Around("FieldNameFill()")
    public Object fieldNameFill(ProceedingJoinPoint joinPoint){
        log.info("填充字段信息");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        FieldNameFill autoFill = signature.getMethod().getAnnotation(FieldNameFill.class);
        FieldType fieldType = autoFill.value();
        try {
            Object result = joinPoint.proceed();
            if(result instanceof List<?>){
                for (var e:(List<?>)result) {
                    fieldNameFill(e,fieldType);
                }
            }else {
                fieldNameFill(result,fieldType);
            }
            return result;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

    }

    private void fillField(Object entity, OperationType operationType, LocalDateTime now) {
        if (operationType == OperationType.INSERT) {
            try {
                Method setCreateTime = entity.getClass().getMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setCreateUser = entity.getClass().getMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateTime = entity.getClass().getMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                setCreateTime.invoke(entity, now);
                setCreateUser.invoke(entity, BaseContext.getCurrentId().getEmployeeId());
                setUpdateTime.invoke(entity, now);
                setUpdateUser.invoke(entity, BaseContext.getCurrentId().getEmployeeId());
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        } else if (operationType == OperationType.UPDATE) {
            try {

                Method setUpdateUser = entity.getClass().getMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                Method setUpdateTime = entity.getClass().getMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);

                setUpdateTime.invoke(entity, now);
                setUpdateUser.invoke(entity, BaseContext.getCurrentId().getEmployeeId());
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private void fieldNameFill(Object entity,FieldType fieldType){
        try{
            Class<?> entityClass = entity.getClass();
            Field createUserName = entityClass.getDeclaredField("createUserName");
            Field updateUserName = entityClass.getDeclaredField("updateUserName");
            createUserName.setAccessible(true);
            updateUserName.setAccessible(true);


            //获取对象中的修改人和创建人工号
            Field createUserField = entityClass.getDeclaredField("createUser");
            Field updateUserField= entityClass.getDeclaredField("updateUser");
            createUserField.setAccessible(true);
            updateUserField.setAccessible(true);
            Long createUserId=(Long) createUserField.get(entity);
            Long updateUserId=(Long) updateUserField.get(entity);
            String createUserNameStr=null;
            String updateUserNameStr=null;
            if(createUserId!=null){
                createUserNameStr=employeeMapper.getById(createUserId).getName();
            }
            if (updateUserId!=null) {
                updateUserNameStr = employeeMapper.getById(updateUserId).getName();
            }

            //填充字段
            createUserName.set(entity, ObjectUtils.isEmpty(createUserNameStr)?"":createUserNameStr);
            updateUserName.set(entity,ObjectUtils.isEmpty(updateUserNameStr)?"":updateUserNameStr);

        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

}
