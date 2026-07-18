package com.smartmall.controller;

import com.smartmall.common.Result;
import com.smartmall.enums.ResultCode;
import com.smartmall.exception.BusinessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public Result<String> hello(){

        return Result.success("SmartMall Started Successfully");

    }
    @GetMapping("/test")
    public Result<Void> test() {
        throw new BusinessException(ResultCode.USER_NOT_EXIST);
    }

}
