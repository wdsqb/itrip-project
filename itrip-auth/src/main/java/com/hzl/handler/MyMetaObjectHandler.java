package com.hzl.handler;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description : MyBatisPlus自动填充处理器
 * @date : 2020-11-12 14:05
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    private static final Logger log = LoggerFactory.getLogger(MyMetaObjectHandler.class);

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.strictInsertFill(metaObject, "creationDate", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, "modifyDate", LocalDateTime::now, LocalDateTime.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.strictUpdateFill(metaObject, "modifyDate", LocalDateTime::now, LocalDateTime.class);
    }
}
