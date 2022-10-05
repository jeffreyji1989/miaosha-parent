package com.nuwa.miaosha.common.util.constant;

/**
 * @author jijunhui
 * @date 2020/11/22
 * @desc
 */
public interface CommonConstants {

    String CACHE_PRE = "NUWA:";
    /**
     * 默认分页当前页码
     */
    Integer PAGE_NUM_DEFAULT = 1;

    /**
     * 默认分页展示的条数
     */
    Integer PAGE_SIZE_DEFAULT = 20;

    /**
     * 时间格式化 YYMMDDHHMMSS
     */
    String YYMMDDHHMMSS = "yyMMddHHmmss";

    /**
     * 时间格式化 YYYYMMDD
     */
    String YYYYMMDD = "yyyyMMdd";

    String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * id 缓存key过期时间
     */
    Integer ID_EXPIRE = 10;

    /**
     * kafka 读取缓存为空 睡眠时间
     */
    Long KAFKA_FAIL_SLEEP_TIME = 10000L;

    /**
     * 日志流水号
     */
    String LOG_SERIAL_NO = "LOG_SERIAL_NO";

    /**
     * 系统标识符
     */
    String SYSTEM = "SYSTEM";

    /**
     * 当前系统登陆账号
     */
    String LOGIN_USER = "LOGIN_USER";

    /**
     * 当前登录用户id
     */
    String USERID = "USERID";

    /**
     * 申请单的id
     */
    String APPLY_ID = "APPLY_ID";

    /**
     * 删除字段
     */
    String DEL_FLAG = "DEL_FLAG";


    /**
     * 发票的id
     */
    String INVOICE_ID = "INVOICE_ID";

    /**
     * 流水号
     */
    String SERIAL_NO = "SERIAL_NO";

    /**
     * 导出最大上限2000条
     */
    Integer EXPORT_PAGE_SIZE = 2000;

    /**
     * 导出起始页
     */
    Integer EXPORT_PAGE_NUM = 1;

    /**
     * 系统编码值
     */
    String UTF8 = "UTF-8";

    /**
     * 文件编码
     */
    String GBK = "GBK";
    /**
     * 日志打印长度
     */
    Integer LOG_LENGTH = 4096;
    /**
     * 创建订单topic
     */
    String MQ_TOPIC_CREATE_ORDER="CREATE_ORDER_TOPIC";

    String MQ_GROUP_ID="miaosha";
}
