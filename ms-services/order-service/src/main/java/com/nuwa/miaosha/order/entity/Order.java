package com.nuwa.miaosha.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单
 * </p>
 *
 * @author jeffrey
 * @since 2022-09-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "ID",type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField("USER_ID")
    private Long userId;

    /**
     * 商品id
     */
    @TableField("GOOD_ID")
    private Long goodId;

    /**
     * 商品名称
     */
    @TableField("GOOD_NAME")
    private String goodName;

    /**
     * 商品数量
     */
    @TableField("GOOD_COUNT")
    private Integer goodCount;

    /**
     * 商品价格
     */
    @TableField("GOOD_PRICE")
    private BigDecimal goodPrice;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 状态
     */
    @TableField("STATUS")
    private Integer status;

    /**
     * 支付时间
     */
    @TableField("PAY_TIME")
    private LocalDateTime payTime;

    /**
     * 活动id
     */
    @TableField("SUBJECT_ID")
    private Long subjectId;


}
