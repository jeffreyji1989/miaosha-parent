package com.nuwa.miaosha.good.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author jeffrey
 * @since 2022-09-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_good")
public class Good implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "ID",type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 商品编码
     */
    @TableField("CODE")
    private String code;

    /**
     * 商品名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 商品标题
     */
    @TableField("TITLE")
    private String title;

    /**
     * 价格
     */
    @TableField("PRICE")
    private BigDecimal price;

    /**
     * 商品详情
     */
    @TableField("DETAIL")
    private String detail;
    /**
     * 库存
     */
    @TableField("STOCK")
    private Long stock;


}
