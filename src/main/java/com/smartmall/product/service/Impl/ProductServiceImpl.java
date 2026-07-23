package com.smartmall.product.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smartmall.product.dto.ProductAddDTO;
import com.smartmall.product.dto.ProductQueryDTO;
import com.smartmall.product.dto.ProductUpdateDTO;
import com.smartmall.product.entity.Product;
import com.smartmall.enums.ResultCode;
import com.smartmall.exception.BusinessException;
import com.smartmall.product.mapper.ProductMapper;
import com.smartmall.product.service.ProductService;
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
        //准备接收数据
        Product product = new Product();
        //核心搬运动作：把 dto 里的字段值，按"字段名一致"的规则，逐个拷贝到 product 里。
        //内部原理是 Java 反射——遍历 dto 的所有 getter 方法（如 getName()），去 product 里找对应的 setter 方法（如 setName()）调用赋值
        BeanUtils.copyProperties(dto, product);
        //给新建的对象初始化一些数据
        product.setSales(0);
        product.setStatus(1);

        //与 mapper 和数据库关联
//        ServiceImpl<ProductMapper, Product>已经和 mapper 关联了
        save(product);
        //返回给 controller
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