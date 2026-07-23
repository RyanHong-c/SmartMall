package com.smartmall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartmall.user.dto.PasswordUpdateDTO;
import com.smartmall.user.dto.UserRegisterDTO;
import com.smartmall.user.dto.UserUpdateDTO;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    /**
     * 用户注册测试
     */
    @Test
    void registerTest() throws Exception {

        UserRegisterDTO dto = new UserRegisterDTO();
        dto.setUsername("test001");
        dto.setPassword("123456");
        dto.setNickname("测试用户");
        dto.setPhone("13800138001");
        dto.setEmail("test001@test.com");
        dto.setGender(1);

        mockMvc.perform(
                        post("/api/user/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }


    /**
     * 查询用户测试
     */
    @Test
    void getByIdTest() throws Exception {

        mockMvc.perform(
                        get("/api/user/1")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.username").exists());
    }


    /**
     * 查询列表测试
     */
    @Test
    void listTest() throws Exception {

        mockMvc.perform(
                        get("/api/user/list")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }


    /**
     * 分页查询测试
     */
    @Test
    void pageTest() throws Exception {

        mockMvc.perform(
                        get("/api/user/page")
                                .param("page", "1")
                                .param("size", "10")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }


    /**
     * 修改用户信息测试
     */
    @Test
    void updateTest() throws Exception {

        UserUpdateDTO dto = new UserUpdateDTO();
        dto.setNickname("新昵称");
        dto.setGender(1);

        mockMvc.perform(
                        put("/api/user/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }


    /**
     * 修改密码测试
     */
    @Test
    void updatePasswordTest() throws Exception {

        PasswordUpdateDTO dto = new PasswordUpdateDTO();
        dto.setOldPassword("123456");
        dto.setNewPassword("654321");

        mockMvc.perform(
                        put("/api/user/1/password")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }


    /**
     * 禁用用户测试
     */
    @Test
    void disableTest() throws Exception {

        mockMvc.perform(
                        put("/api/user/1/disable")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }


    /**
     * 启用用户测试
     */
    @Test
    void enableTest() throws Exception {

        mockMvc.perform(
                        put("/api/user/1/enable")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }


    /**
     * 删除用户测试
     */
    @Test
    void deleteTest() throws Exception {

        mockMvc.perform(
                        delete("/api/user/1")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }
}