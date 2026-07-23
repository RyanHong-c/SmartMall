package com.smartmall.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smartmall.enums.OrderStatus;
import com.smartmall.enums.ResultCode;
import com.smartmall.exception.BusinessException;
import com.smartmall.order.dto.OrderCreateDTO;
import com.smartmall.order.dto.OrderItemCreateDTO;
import com.smartmall.order.dto.OrderQueryDTO;
import com.smartmall.order.dto.OrderStatusDTO;
import com.smartmall.order.entity.Order;
import com.smartmall.order.entity.OrderItem;
import com.smartmall.order.mapper.OrderMapper;
import com.smartmall.order.service.OrderItemService;
import com.smartmall.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    private final OrderItemService orderItemService;

    @Override
    @Transactional
    public Order create(OrderCreateDTO dto) {

        Order order = new Order();
        order.setOrderNo(UUID.randomUUID().toString().replace("-", ""));
        order.setUserId(dto.getUserId());
        order.setStatus(OrderStatus.PENDING.getCode());

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderItemCreateDTO item : dto.getItems()) {
            totalAmount = totalAmount.add(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }

        order.setTotalAmount(totalAmount);

        save(order);

        for (OrderItemCreateDTO item : dto.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order.getId());
            orderItem.setProductId(item.getProductId());
            orderItem.setProductName(item.getProductName());
            orderItem.setPrice(item.getPrice());
            orderItem.setQuantity(item.getQuantity());
            orderItemService.save(orderItem);
        }

        return order;
    }

    @Override
    public List<Order> list(OrderQueryDTO dto) {

        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(dto.getUserId() != null, Order::getUserId, dto.getUserId())
                .eq(dto.getStatus() != null, Order::getStatus, dto.getStatus())
                .orderByDesc(Order::getCreateTime);

        return list(wrapper);
    }

    @Override
    public Order getOrderById(Long id) {

        Order order = getById(id);

        if (order == null) {
            throw new BusinessException(ResultCode.ORDER_NOT_EXIST);
        }

        return order;
    }

    @Override
    @Transactional
    public Order updateStatus(Long id, OrderStatusDTO dto) {

        Order order = getOrderById(id);
        order.setStatus(dto.getStatus());
        updateById(order);

        return order;
    }

    @Override
    @Transactional
    public void cancel(Long id) {

        Order order = getOrderById(id);
        order.setStatus(OrderStatus.CANCELLED.getCode());
        updateById(order);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        getOrderById(id);

        orderItemService.removeByOrderId(id);
        removeById(id);
    }

    @Override
    public Long count(OrderQueryDTO dto) {

        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(dto.getUserId() != null, Order::getUserId, dto.getUserId())
                .eq(dto.getStatus() != null, Order::getStatus, dto.getStatus());

        return count(wrapper);
    }

}