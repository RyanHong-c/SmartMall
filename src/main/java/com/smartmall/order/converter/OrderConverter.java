package com.smartmall.order.converter;

import com.smartmall.order.entity.Order;
import com.smartmall.order.vo.OrderVO;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter {


    public OrderVO toVO(Order order){

        OrderVO vo = new OrderVO();

        BeanUtils.copyProperties(
                order,
                vo
        );

        return vo;
    }

}