package com.smartmall.enums;

public enum ResultCode {

    //new ResultCode(xxx,xxx)

    //public static final ResultCode SUCCESS = new ResultCode(200, "操作成功");
    //public static final ResultCode FAIL = new ResultCode(500, "系统异常");
    SUCCESS(200, "操作成功"),

    FAIL(500, "系统异常"),

    PARAM_ERROR(400, "参数错误"),

    UNAUTHORIZED(401, "未登录"),

    FORBIDDEN(403, "权限不足"),

    NOT_FOUND(404, "资源不存在"),

    USER_NOT_EXIST(1001, "用户不存在"),

    USER_PASSWORD_ERROR(1002, "用户名或密码错误"),

    PRODUCT_NOT_EXIST(2001, "商品不存在");

    private final Integer code;

    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}