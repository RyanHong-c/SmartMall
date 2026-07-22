package com.smartmall.order.converter;

import com.smartmall.order.entity.OrderItem;
import com.smartmall.order.vo.OrderItemVO;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class OrderItemConverter {


    public OrderItemVO toVO(OrderItem item){

        OrderItemVO vo = new OrderItemVO();

        BeanUtils.copyProperties(
                item,
                vo
        );

        return vo;
    }

}