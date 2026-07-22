package com.smartmall.product.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smartmall.product.DTO.ProductAddDTO;
import com.smartmall.product.DTO.ProductQueryDTO;
import com.smartmall.product.DTO.ProductUpdateDTO;
import com.smartmall.product.Entity.Product;

public interface ProductService extends IService<Product> {

    Product addProduct(ProductAddDTO dto);

    Product updateProduct(Long id, ProductUpdateDTO dto);

    void updateStatus(Long id, Integer status);

    void deleteProduct(Long id);

    Object pageQuery(ProductQueryDTO dto);
}