package com.smartmall.user.Mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smartmall.user.Entity.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * 用户Mapper
 */
@Mapper
public interface UserMapper
        extends BaseMapper<User> {


}