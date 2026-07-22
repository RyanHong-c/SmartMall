package com.smartmall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.smartmall.order.dto.OrderCreateDTO;
import com.smartmall.order.dto.OrderQueryDTO;
import com.smartmall.order.dto.OrderStatusDTO;
import com.smartmall.order.entity.Order;
import com.smartmall.order.vo.OrderDetailVO;
import com.smartmall.order.vo.OrderVO;

import java.util.List;

public interface OrderService extends IService<Order> {


    /**
     * 创建订单
     */
    Order createOrder(
            OrderCreateDTO dto
    );


    /**
     * 查询订单列表
     */
    List<OrderVO> listOrder(
            OrderQueryDTO dto
    );


    /**
     * 查询订单详情
     */
    OrderDetailVO detail(
            Long id
    );


    /**
     * 修改状态
     */
    void updateStatus(
            Long id,
            OrderStatusDTO dto
    );


    /**
     * 取消订单
     */
    void cancel(
            Long id
    );

}