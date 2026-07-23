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

//1000 用户(User)
//2000 商品(Product)
//3000 分类(Category)
//4000 库存(Inventory)
//5000 购物车(Cart)
//6000 订单(Order)
//7000 支付(Payment)


// =====================
// 用户模块 1000-1099
// =====================


    USER_NOT_EXIST(1001, "用户不存在"),

    USER_PASSWORD_ERROR(
            1002,
            "密码错误"
    ),
    USER_EXIST(
            1003,
            "用户名已存在"
    ),
    USER_DISABLED(
            1004,
            "账号已被禁用"
    ),
    PHONE_EXIST(
            1005,
            "手机号已存在"
    ),
    EMAIL_EXIST(
            1006,
            "邮箱已存在"
    ),
    USER_OLD_PASSWORD_ERROR(
            1007,
            "原密码错误"
    ),
// =====================
// 商品模块 2000-2099
// =====================

    PRODUCT_NOT_EXIST(2001, "商品不存在"),

    CATEGORY_NOT_EXIST(2002,"分类不存在"),

    PRODUCT_STOCK_NOT_ENOUGH(2003,"商品库存不足"),

    INVENTORY_NOT_EXIST(
        3001,
                "库存不存在"
    ),

    INVENTORY_NOT_ENOUGH(
        3002,
                "库存不足"
    ),
    // Order
    ORDER_NOT_EXIST(6001, "订单不存在"),
    ORDER_STATUS_ERROR(6002, "订单状态异常"),
    ORDER_CREATE_FAILED(6003, "订单创建失败"),
    ORDER_CANCEL_FAILED(6004, "订单取消失败"),
    ORDER_DETAIL_NOT_EXIST(6005, "订单详情不存在");


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