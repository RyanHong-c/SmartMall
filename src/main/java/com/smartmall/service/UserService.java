package com.smartmall.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.smartmall.dto.PasswordUpdateDTO;
import com.smartmall.dto.UserRegisterDTO;
import com.smartmall.dto.UserUpdateDTO;
import com.smartmall.entity.User;



/**
 * 用户业务接口
 */
public interface UserService
        extends IService<User> {



    /**
     * 用户注册
     */
    User register(
            UserRegisterDTO dto
    );



    /**
     * 修改用户资料
     */
    User updateUser(
            Long id,
            UserUpdateDTO dto
    );



    /**
     * 修改密码
     */
    void updatePassword(
            Long id,
            PasswordUpdateDTO dto
    );



    /**
     * 禁用用户
     */
    void disableUser(
            Long id
    );



    /**
     * 启用用户
     */
    void enableUser(
            Long id
    );


}