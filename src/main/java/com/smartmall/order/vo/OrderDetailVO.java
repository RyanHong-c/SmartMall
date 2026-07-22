package com.smartmall.order.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDetailVO {

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
     * 总金额
     */
    private BigDecimal totalAmount;

    /**
     * 状态
     */
    private Integer status;

    private LocalDateTime createTime;

    /**
     * 商品明细
     */
    private List<OrderItemVO> items;

}