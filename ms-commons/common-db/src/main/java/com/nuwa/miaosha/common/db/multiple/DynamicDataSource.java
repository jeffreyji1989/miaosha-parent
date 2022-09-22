package com.nuwa.miaosha.common.db.multiple;//package com.nuwa.common.db.multiple;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//import org.springframework.stereotype.Component;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author jijunhui
// * @version 1.0.0
// * @date 2021/1/30 14:37
// * @description 动态数据源
// */
//@Component
////@Primary
//@Slf4j
//public class DynamicDataSource extends AbstractRoutingDataSource {
//
//    @Autowired
//    @Qualifier("readDataSource")
//    private DataSource readDataSource;
//    @Autowired
//    @Qualifier("writeDataSource")
//    private DataSource writeDataSource;
//
//    @Override
//    protected Object determineCurrentLookupKey() {
//        log.info("DataSourceContextHolder：{}", DataSourceContextHolder.getDbType());
//        return DataSourceContextHolder.getDbType();
//    }
//
//    @Override
//    public void afterPropertiesSet() {
//        Map<Object, Object> map = new HashMap<>();
//        map.put("readDataSource", readDataSource);
//        map.put("writeDataSource", writeDataSource);
//        // 注册数据源
//        setTargetDataSources(map);
//        setDefaultTargetDataSource(writeDataSource);
////        super.afterPropertiesSet();
//    }
//}
