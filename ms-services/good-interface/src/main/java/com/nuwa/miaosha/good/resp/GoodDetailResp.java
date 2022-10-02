package com.nuwa.miaosha.good.resp;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class GoodDetailResp implements Serializable {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 商品编码
     */
    private String code;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 商品详情
     */
    private String detail;
    /**
     * 库存
     */
    private Long stock;
}
