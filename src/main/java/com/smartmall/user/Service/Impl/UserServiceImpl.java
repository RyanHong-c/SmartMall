package com.smartmall.user.Service.Impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smartmall.user.DTO.PasswordUpdateDTO;
import com.smartmall.user.DTO.UserRegisterDTO;
import com.smartmall.user.DTO.UserUpdateDTO;
import com.smartmall.user.Entity.User;
import com.smartmall.enums.ResultCode;
import com.smartmall.exception.BusinessException;
import com.smartmall.user.Mapper.UserMapper;
import com.smartmall.user.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户业务实现
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * 用户注册
     */
    @Override
    @Transactional
    public User register(UserRegisterDTO dto) {
        // 1.检查用户名
        LambdaQueryWrapper<User> usernameWrapper = new LambdaQueryWrapper<>();
        usernameWrapper.eq(User::getUsername, dto.getUsername());
        if (this.count(usernameWrapper) > 0) {
            throw new BusinessException(ResultCode.USER_EXIST);
        }

        // 2.检查手机号
        if (StrUtil.isNotBlank(dto.getPhone())) {
            LambdaQueryWrapper<User> phoneWrapper = new LambdaQueryWrapper<>();
            phoneWrapper.eq(User::getPhone, dto.getPhone());
            if (this.count(phoneWrapper) > 0) {
                throw new BusinessException(ResultCode.PHONE_EXIST);
            }
        }

        // 3.检查邮箱
        if (StrUtil.isNotBlank(dto.getEmail())) {
            LambdaQueryWrapper<User> emailWrapper = new LambdaQueryWrapper<>();
            emailWrapper.eq(User::getEmail, dto.getEmail());
            if (this.count(emailWrapper) > 0) {
                throw new BusinessException(ResultCode.EMAIL_EXIST);
            }
        }

        // 4.创建用户
        User user = new User();
        user.setUsername(dto.getUsername());

        // BCrypt加密
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        user.setNickname(
                StrUtil.isNotBlank(dto.getNickname())
                        ? dto.getNickname()
                        : dto.getUsername()
        );

        user.setPhone(dto.getPhone());
        user.setEmail(dto.getEmail());
        user.setGender(dto.getGender() == null ? 0 : dto.getGender());

        // 默认正常
        user.setStatus(1);

        this.save(user);
        return user;
    }

    /**
     * 修改用户资料
     */
    @Override
    @Transactional
    public User updateUser(Long id, UserUpdateDTO dto) {
        User user = this.getById(id);
        if (user == null) {
            throw new BusinessException(ResultCode.USER_NOT_EXIST);
        }

        if (StrUtil.isNotBlank(dto.getNickname())) {
            user.setNickname(dto.getNickname());
        }

        if (StrUtil.isNotBlank(dto.getPhone())) {
            user.setPhone(dto.getPhone());
        }

        if (StrUtil.isNotBlank(dto.getEmail())) {
            user.setEmail(dto.getEmail());
        }

        if (dto.getGender() != null) {
            user.setGender(dto.getGender());
        }

        if (StrUtil.isNotBlank(dto.getAvatar())) {
            user.setAvatar(dto.getAvatar());
        }

        this.updateById(user);
        return user;
    }

    /**
     * 修改密码
     */
    @Override
    @Transactional
    public void updatePassword(Long id, PasswordUpdateDTO dto) {
        User user = this.getById(id);
        if (user == null) {
            throw new BusinessException(ResultCode.USER_NOT_EXIST);
        }

        // 验证旧密码
        boolean match = passwordEncoder.matches(dto.getOldPassword(), user.getPassword());
        if (!match) {
            throw new BusinessException(ResultCode.USER_OLD_PASSWORD_ERROR);
        }

        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        this.updateById(user);
    }

    /**
     * 禁用用户
     */
    @Override
    public void disableUser(Long id) {
        User user = this.getById(id);
        if (user == null) {
            throw new BusinessException(ResultCode.USER_NOT_EXIST);
        }

        user.setStatus(0);
        this.updateById(user);
    }

    /**
     * 启用用户
     */
    @Override
    public void enableUser(Long id) {
        User user = this.getById(id);
        if (user == null) {
            throw new BusinessException(ResultCode.USER_NOT_EXIST);
        }

        user.setStatus(1);
        this.updateById(user);
    }

}