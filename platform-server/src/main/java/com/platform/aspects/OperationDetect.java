package com.platform.aspects;

import com.alibaba.fastjson.JSONObject;
import com.platform.context.BaseContext;
import com.platform.utils.IPAddressUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class OperationDetect {

    @Around("@annotation(com.platform.annotaionExtend.OperationLog)")
    public Object operationDetect(ProceedingJoinPoint joinPoint) {
        Long userId = BaseContext.getCurrentId().getId();
        String exceptionDetected = null;
        Object proceed = null;
        LocalDateTime updateTime = LocalDateTime.now();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String[] split = url.split("/");
        int portCheck = split[3].equals("admin") ? 1 : 2;
        HttpServletRequest request1 = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String ip = IPAddressUtils.getIpAddress(request);
        if (ip == null) {
            return "error/limit";
        }

        String method = joinPoint.getSignature().getName();
        String args = JSONObject.toJSONString(joinPoint.getArgs());
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable ex) {
            exceptionDetected = ex.getMessage();
            ex.printStackTrace();
        } finally {

        }
        return proceed;
    }
}
