package com.platform.handler;

import com.platform.exception.BaseException;
import com.platform.result.Result;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex) {
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    @ExceptionHandler
    public Result exceptionHandler(HttpMessageNotReadableException ex) {
        log.error("异常信息：{}", ex.getMessage());
        String msg = ex.getMessage();
        return Result.error(ex.getMessage());
    }

    @ExceptionHandler
    public Result validateExceptionHandler(ConstraintViolationException ex){
        log.error("异常信息：{}",ex.getMessage());
        return Result.error(ex.getMessage());
    }

    @ExceptionHandler
    public Result validateMessageExceptionHandler(MethodArgumentNotValidException ex){
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        List<String> errorList=new ArrayList<>();
        for (ObjectError err :allErrors) {
            errorList.add(err.getDefaultMessage());
        }
        return Result.errorList(errorList);
    }
}
