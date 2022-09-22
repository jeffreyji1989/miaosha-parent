package com.nuwa.miaosha.common.db.multiple;//package com.nuwa.common.db.multiple;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
///**
// * @author jijunhui
// * @version 1.0.0
// * @date 2021/1/30 14:42
// * @description todo
// */
//@Aspect
//@Component
//@Lazy(value = false)
//// Order设定AOP执行顺序 使之在数据库事务上先执行
//@Order(-10)
//@Slf4j
//public class DataSourceAspect {
//    //横切点
//    @Before("execution(* com..service..*(..))")
//    public void process(JoinPoint joinPoint) {
//        String methodName = joinPoint.getSignature().getName();
//        if (methodName.startsWith("get") || methodName.startsWith("count") || methodName.startsWith("find")
//                || methodName.startsWith("list") || methodName.startsWith("select") || methodName.startsWith("check")) {
//            DataSourceContextHolder.setDbType("readDataSource");
//        } else {
//            DataSourceContextHolder.setDbType("writeDataSource");
//        }
//        log.info("使用的数据源：{}", DataSourceContextHolder.getDbType());
//    }
//}
