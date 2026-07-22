package com.smartmall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({
        "com.smartmall.user.Mapper",
        "com.smartmall.product.Mapper",
        "com.smartmall.category.Mapper",
        "com.smartmall.inventory.Mapper",
        "com.smartmall.cart.mapper"
})
public class MybatisPlusConfig {

}