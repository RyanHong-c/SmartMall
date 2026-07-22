package com.smartmall.inventory.Controller;

import com.smartmall.inventory.Converter.InventoryConverter;
import com.smartmall.inventory.DTO.InventoryUpdateDTO;
import com.smartmall.inventory.Entity.Inventory;
import com.smartmall.inventory.Service.InventoryService;
import com.smartmall.inventory.VO.InventoryVO;
import com.smartmall.common.Result;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {


    private final InventoryService inventoryService;

    private final InventoryConverter inventoryConverter;



    /**
     * 查询库存
     */
    @GetMapping("/{productId}")
    public Result<InventoryVO> get(
            @PathVariable Long productId
    ){

        Inventory inventory =
                inventoryService.getInventory(productId);


        return Result.success(
                inventoryConverter.toVO(inventory)
        );
    }





    /**
     * 初始化库存
     */
    @PostMapping("/init/{productId}")
    public Result<InventoryVO> init(
            @PathVariable Long productId
    ){

        Inventory inventory =
                inventoryService.initInventory(
                        productId
                );


        return Result.success(
                inventoryConverter.toVO(inventory)
        );
    }





    /**
     * 修改库存
     */
    @PutMapping("/{productId}")
    public Result<InventoryVO> update(
            @PathVariable Long productId,
            @Valid @RequestBody InventoryUpdateDTO dto
    ){

        Inventory inventory =
                inventoryService.updateInventory(
                        productId,
                        dto
                );


        return Result.success(
                inventoryConverter.toVO(inventory)
        );
    }





    /**
     * 增加库存
     */
    @PostMapping("/increase")
    public Result<Void> increase(
            @RequestParam Long productId,
            @RequestParam Integer count
    ){

        inventoryService.increase(
                productId,
                count
        );


        return Result.success();
    }





    /**
     * 扣减库存
     */
    @PostMapping("/decrease")
    public Result<Void> decrease(
            @RequestParam Long productId,
            @RequestParam Integer count
    ){

        inventoryService.decrease(
                productId,
                count
        );


        return Result.success();
    }

}