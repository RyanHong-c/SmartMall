package com.smartmall.product.Mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smartmall.product.Entity.Product;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper
        extends BaseMapper<Product> {

}