package com.smartmall.order.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemVO {

    private Long id;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品数量
     */
    private Integer quantity;

}