package com.smartmall.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.smartmall.order.converter.OrderConverter;
import com.smartmall.order.dto.OrderCreateDTO;
import com.smartmall.order.dto.OrderQueryDTO;
import com.smartmall.order.dto.OrderStatusDTO;
import com.smartmall.order.entity.Order;
import com.smartmall.order.entity.OrderItem;
import com.smartmall.order.mapper.OrderMapper;
import com.smartmall.order.mapper.OrderItemMapper;
import com.smartmall.order.service.OrderService;
import com.smartmall.order.vo.OrderDetailVO;
import com.smartmall.order.vo.OrderVO;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl
        extends ServiceImpl<OrderMapper, Order>
        implements OrderService {


    private final OrderItemMapper orderItemMapper;

    private final OrderConverter orderConverter;


    @Override
    @Transactional
    public Order createOrder(
            OrderCreateDTO dto
    ){

        Order order = new Order();

        order.setOrderNo(
                "SM" + UUID.randomUUID()
                        .toString()
                        .replace("-", "")
        );


        order.setUserId(
                dto.getUserId()
        );


        order.setStatus(0);


        order.setTotalAmount(
                java.math.BigDecimal.ZERO
        );


        save(order);


        return order;
    }



    @Override
    public List<OrderVO> listOrder(
            OrderQueryDTO dto
    ){

        List<Order> list =
                lambdaQuery()
                        .eq(
                                dto.getUserId()!=null,
                                Order::getUserId,
                                dto.getUserId()
                        )
                        .eq(
                                dto.getStatus()!=null,
                                Order::getStatus,
                                dto.getStatus()
                        )
                        .list();


        return list.stream()
                .map(
                        orderConverter::toVO
                )
                .collect(
                        Collectors.toList()
                );
    }



    @Override
    public OrderDetailVO detail(
            Long id
    ){

        return null;

    }



    @Override
    @Transactional
    public void updateStatus(
            Long id,
            OrderStatusDTO dto
    ){

        Order order =
                getById(id);


        order.setStatus(
                dto.getStatus()
        );


        updateById(order);

    }



    @Override
    @Transactional
    public void cancel(
            Long id
    ){

        Order order =
                getById(id);


        order.setStatus(4);


        updateById(order);

    }

}