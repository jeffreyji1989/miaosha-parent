package com.nuwa.miaosha.common.db.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author jijunhui
 * @version 1.0.0
 * @date 2020/10/29 20:08
 * @description mybatis-plus 自动填充
 */
@Component
@Slf4j
public class EntityMetaObjectHandler implements MetaObjectHandler {
    private final static String DELETED = "deleted";
    private final static String CREATE_TIME = "createTime";
    private final static String UPDATE_TIME = "updateTime";
    private final static String CREATE_BY = "createBy";
    private final static String UPDATE_BY = "updateBy";

    @Override
    public void insertFill(MetaObject metaObject) {
        final Date now = new Date();
        // 起始版本 3.3.3(推荐)
        this.strictInsertFill(metaObject,CREATE_TIME,Date.class, now);
        this.strictInsertFill(metaObject,UPDATE_TIME,Date.class, now);
        this.strictInsertFill(metaObject, DELETED, Integer.class,1);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        final Date now = new Date();
        // 起始版本 3.3.3(推荐)
        this.strictInsertFill(metaObject,UPDATE_TIME, Date.class ,now);
        this.strictInsertFill(metaObject,UPDATE_BY, Date.class,now);
    }
}
