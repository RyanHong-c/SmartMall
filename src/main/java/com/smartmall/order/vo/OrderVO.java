package com.smartmall.order.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderVO {

    /**
     * 订单ID
     */
    private Long id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 订单金额
     */
    private BigDecimal totalAmount;

    /**
     * 订单状态
     */
    private Integer status;

    private LocalDateTime createTime;

}