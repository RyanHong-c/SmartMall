package com.smartmall.order.controller;

import com.smartmall.common.Result;

import com.smartmall.order.dto.OrderCreateDTO;
import com.smartmall.order.dto.OrderQueryDTO;
import com.smartmall.order.dto.OrderStatusDTO;
import com.smartmall.order.service.OrderService;
import com.smartmall.order.vo.OrderVO;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {


    private final OrderService orderService;



    @PostMapping
    public Result<?> create(
            @RequestBody
            @Valid
            OrderCreateDTO dto
    ){

        return Result.success(
                orderService.createOrder(dto)
        );

    }



    @GetMapping("/list")
    public Result<List<OrderVO>> list(
            OrderQueryDTO dto
    ){

        return Result.success(
                orderService.listOrder(dto)
        );

    }



    @PutMapping("/{id}/status")
    public Result<?> status(
            @PathVariable Long id,
            @RequestBody
            @Valid
            OrderStatusDTO dto
    ){

        orderService.updateStatus(
                id,
                dto
        );


        return Result.success();

    }



    @PutMapping("/{id}/cancel")
    public Result<?> cancel(
            @PathVariable Long id
    ){

        orderService.cancel(id);

        return Result.success();

    }

}