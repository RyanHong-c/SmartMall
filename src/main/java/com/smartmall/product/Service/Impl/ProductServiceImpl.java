package com.smartmall.product.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smartmall.product.DTO.ProductAddDTO;
import com.smartmall.product.DTO.ProductQueryDTO;
import com.smartmall.product.DTO.ProductUpdateDTO;
import com.smartmall.product.Entity.Product;
import com.smartmall.enums.ResultCode;
import com.smartmall.exception.BusinessException;
import com.smartmall.product.Mapper.ProductMapper;
import com.smartmall.product.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Override
    @Transactional
    public Product addProduct(ProductAddDTO dto) {
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);
        product.setSales(0);
        product.setStatus(1);
        save(product);
        return product;
    }

    @Override
    @Transactional
    public Product updateProduct(Long id, ProductUpdateDTO dto) {
        Product product = getById(id);
        if (product == null) {
            throw new BusinessException(
                    ResultCode.PRODUCT_NOT_EXIST
            );
        }
        BeanUtils.copyProperties(dto, product);
        updateById(product);
        return product;
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        Product product = getById(id);
        if (product == null) {
            throw new BusinessException(
                    ResultCode.PRODUCT_NOT_EXIST
            );
        }
        product.setStatus(status);
        updateById(product);
    }

    @Override
    public void deleteProduct(Long id) {
        removeById(id);
    }

    @Override
    public Object pageQuery(ProductQueryDTO dto) {
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();

        if (dto.getCategoryId() != null) {
            wrapper.eq(Product::getCategoryId, dto.getCategoryId());
        }

        if (dto.getKeyword() != null && !dto.getKeyword().isEmpty()) {
            wrapper.like(Product::getName, dto.getKeyword());
        }

        return list(wrapper);
    }

}