package com.nuwa.miaosha.ms.entity;

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
 * 秒杀记录表
 * </p>
 *
 * @author jeffrey
 * @since 2022-09-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_ms_record")
public class MsRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 秒杀id
     */
    @TableId(value = "ID",type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 商品ID
     */
    @TableField("GOOD_ID")
    private Long goodId;

    /**
     * 用户ID
     */
    @TableField("USER_ID")
    private Long userId;

    /**
     * 商品ID
     */
    @TableField("GOOD_COUNT")
    private Integer goodCount;

    /**
     * 秒杀价格
     */
    @TableField("PRICE")
    private BigDecimal price;

    /**
     * 主题ID
     */
    @TableField("SUBJECT_ID")
    private Long subjectId;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;


}
