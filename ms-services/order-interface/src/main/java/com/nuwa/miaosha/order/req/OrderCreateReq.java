package com.nuwa.miaosha.order.req;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderCreateReq implements Serializable {
    @NotNull(message = "主题不能为空")
    private Long subjectId ;
    @NotNull(message = "商品不能为空")
    private Long goodId;
    private String goodName;
    @Range(min = 1,max = 100,message = "商品数量不在范围内")
    private Integer goodCount;
    @DecimalMin(value = "0.01",message = "商品价格有误")
    private BigDecimal goodPrice;
    private Long userId;

}
