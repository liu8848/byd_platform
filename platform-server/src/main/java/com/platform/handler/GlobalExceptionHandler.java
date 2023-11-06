package com.platform.handler;

import com.platform.exception.BaseException;
import com.platform.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
