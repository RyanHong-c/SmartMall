package com.smartmall.category.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.smartmall.category.Entity.ProductCategory;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductCategoryMapper
        extends BaseMapper<ProductCategory> {

}