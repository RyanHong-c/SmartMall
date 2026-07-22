package com.smartmall.category.DTO;

import lombok.Data;

@Data
public class CategoryUpdateDTO {

    private String name;

    private Long parentId;

    private Integer sort;

    private Integer status;
}