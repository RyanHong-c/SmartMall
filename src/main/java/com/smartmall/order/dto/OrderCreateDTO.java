package com.smartmall.order.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderCreateDTO {

    @NotNull(message = "用户ID不能为空")
    private Long userId;

}