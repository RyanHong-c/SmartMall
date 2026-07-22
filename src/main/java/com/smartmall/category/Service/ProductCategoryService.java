package com.smartmall.category.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smartmall.category.Entity.ProductCategory;
import com.smartmall.category.DTO.CategoryAddDTO;
import com.smartmall.category.DTO.CategoryUpdateDTO;
import com.smartmall.category.VO.CategoryTreeVO;

import java.util.List;

public interface ProductCategoryService
        extends IService<ProductCategory> {

    ProductCategory addCategory(CategoryAddDTO dto);

    ProductCategory updateCategory(
            Long id,
            CategoryUpdateDTO dto
    );

    List<ProductCategory> listCategory();

    List<CategoryTreeVO> tree();

    void deleteCategory(Long id);
}