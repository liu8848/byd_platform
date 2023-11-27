package com.platform.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.platform.context.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class AutoFillHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("----------填充字段-------------------");
        this.strictInsertFill(metaObject,"createTime", LocalDateTime.class,LocalDateTime.now());
        this.strictInsertFill(metaObject,"createUser", Long.class, BaseContext.getCurrentId().getEmployeeId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("----------填充字段-------------------");
        this.strictUpdateFill(metaObject,"updateTime", LocalDateTime.class,LocalDateTime.now());
        this.strictUpdateFill(metaObject,"updateUser", Long.class, BaseContext.getCurrentId().getEmployeeId());
    }
}
