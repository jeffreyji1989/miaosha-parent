package com.nuwa.miaosha.ms.resp;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 秒杀表
 * </p>
 *
 * @author jeffrey
 * @since 2022-09-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MsResp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 秒杀id
     */
    private Long id;

    /**
     * 商品ID
     */
    private Long goodId;

    /**
     * 商品名称
     */
    private String goodName;

    /**
     * 秒杀价格
     */
    private BigDecimal price;

    /**
     * 秒杀库存
     */
    private Long stock;
    /**
     * 状态 0：正常 1：下架
     */
    private Integer status;

    /**
     * 主题ID
     */
    private Long subjectId;

    /**
     * 开始时间
     */
    private LocalDateTime beginTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

}
