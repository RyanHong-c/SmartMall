package com.smartmall.product.Controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.smartmall.common.Result;

import com.smartmall.product.Converter.ProductConverter;

import com.smartmall.product.DTO.ProductAddDTO;
import com.smartmall.product.DTO.ProductQueryDTO;
import com.smartmall.product.DTO.ProductUpdateDTO;

import com.smartmall.product.Entity.Product;

import com.smartmall.product.Service.ProductService;

import com.smartmall.product.VO.ProductVO;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {


    private final ProductService productService;

    private final ProductConverter productConverter;



    /**
     * 新增商品
     */
    @PostMapping
    public Result<ProductVO> add(
            @Valid @RequestBody ProductAddDTO dto
    ){

        Product product =
                productService.addProduct(dto);


        return Result.success(
                productConverter.toVO(product)
        );
    }



    /**
     * 查询商品详情
     */
    @GetMapping("/{id}")
    public Result<ProductVO> getById(
            @PathVariable Long id
    ){

        Product product =
                productService.getById(id);


        return Result.success(
                productConverter.toVO(product)
        );
    }



    /**
     * 商品列表
     */
    @GetMapping("/list")
    public Result<List<ProductVO>> list(){

        List<Product> products =
                productService.list();


        List<ProductVO> result =
                products.stream()
                        .map(productConverter::toVO)
                        .collect(Collectors.toList());


        return Result.success(result);
    }



    /**
     * 商品分页查询
     */
    @GetMapping("/page")
    public Result<Page<ProductVO>> page(
            ProductQueryDTO dto
    ){

        Page<Product> page =
                new Page<>(
                        dto.getPage(),
                        dto.getSize()
                );


        Page<Product> result =
                productService.page(page);



        Page<ProductVO> voPage =
                new Page<>();


        voPage.setCurrent(
                result.getCurrent()
        );

        voPage.setSize(
                result.getSize()
        );

        voPage.setTotal(
                result.getTotal()
        );


        voPage.setRecords(
                result.getRecords()
                        .stream()
                        .map(productConverter::toVO)
                        .collect(Collectors.toList())
        );


        return Result.success(voPage);
    }




    /**
     * 修改商品
     */
    @PutMapping("/{id}")
    public Result<ProductVO> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductUpdateDTO dto
    ){

        Product product =
                productService.updateProduct(
                        id,
                        dto
                );


        return Result.success(
                productConverter.toVO(product)
        );
    }





    /**
     * 商品上下架
     */
    @PutMapping("/{id}/status")
    public Result<Void> updateStatus(
            @PathVariable Long id,
            @RequestParam Integer status
    ){

        productService.updateStatus(
                id,
                status
        );


        return Result.success();
    }





    /**
     * 删除商品
     */
    @DeleteMapping("/{id}")
    public Result<Void> delete(
            @PathVariable Long id
    ){

        productService.deleteProduct(id);


        return Result.success();
    }

}