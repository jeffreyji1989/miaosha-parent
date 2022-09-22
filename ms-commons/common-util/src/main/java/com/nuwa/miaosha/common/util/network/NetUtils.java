package com.nuwa.miaosha.common.util.network;//package com.nuwa.common.util.network;
//
//import lombok.extern.slf4j.Slf4j;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @author jijunhui
// * @version 1.0.0
// * @date 2021/3/8 10:52
// * @description 网络相关工具类
// */
//@Slf4j
//public class NetUtils {
//    /**
//     * 获取ip地址
//     *
//     * @return
//     */
//    public static final String getIpAddress() {
//        String ip = "";
//        try {
//            HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
//            String ipAddresses = request.getHeader("x-forwarded-for");
//            if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
//                ipAddresses = request.getHeader("Proxy-Client-IP");
//            }
//            if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
//                ipAddresses = request.getHeader("WL-Proxy-Client-IP");
//            }
//            if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
//                ipAddresses = request.getHeader("HTTP_CLIENT_IP");
//            }
//            if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
//                ipAddresses = request.getHeader("X-Real-IP");
//            }
//            if (ipAddresses != null && ipAddresses.length() != 0) {
//                ip = ipAddresses.split(",")[0];
//            }
//            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
//                ip = request.getRemoteAddr();
//                log.debug("地址:{}", request.getRemoteHost());
//            }
//            return ip;
//        } catch (Exception e) {
//            log.error("获取IP地址失败,堆栈异常:{}", e.getMessage(), e);
//        }
//        return null;
//    }
//}
