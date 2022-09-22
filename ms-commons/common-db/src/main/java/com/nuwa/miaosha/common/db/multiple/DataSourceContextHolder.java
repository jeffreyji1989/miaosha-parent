package com.nuwa.miaosha.common.db.multiple;//package com.nuwa.common.db.multiple;
//
//import org.springframework.stereotype.Component;
//
///**
// * @author jijunhui
// * @version 1.0.0
// * @date 2021/1/30 14:33
// * @description 数据源上下文holder
// */
//@Component
//public class DataSourceContextHolder {
//    // 数据源类型 按线程存储
//    private static final ThreadLocal<String> threadLocal = new ThreadLocal();
//
//    /**
//     * 设置数据源类型
//     * @param dbType
//     */
//    public static void setDbType(String dbType){
//        threadLocal.set(dbType);
//    }
//
//    /**
//     * 获取数据源类型
//     * @return
//     */
//    public static String getDbType(){
//        return threadLocal.get();
//    }
//
//    /**
//     * 清除数据源类型
//     */
//    public static void clearDbType(){
//        threadLocal.remove();
//    }
//}
