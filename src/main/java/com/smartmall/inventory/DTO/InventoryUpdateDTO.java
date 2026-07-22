package com.smartmall.inventory.DTO;

import jakarta.validation.constraints.NotNull;

import lombok.Data;


@Data
public class InventoryUpdateDTO {


    @NotNull(message = "库存不能为空")
    private Integer stock;


}