package com.rui.xb.modules.xb.entity.result;

/**
 * @Author: DuJianDong
 * @Date: 2018/6/22 下午6:48
 * @Description: *
 */
public enum  RuiResultConstant {
    /**
     * 失败
     */
    FAILED(0, "failed"),

    /**
     * 成功
     */
    SUCCESS(1, "success"),


    /**
     * 参数有误
     */
    PARAMS_ERROR(404,"缺少参数或格式有误"),


    SERVER_ERROR(500,"服务器异常"),

    FAILED_COLLECT_USER_SAME(0,"不能收藏自己的商品"),

    FAILED_COLLECT_COLLECTED(0,"不能重复收藏"),

    FAILED_MSG_ERROR(0,"验证码错误"),

    FAILED_USER_LOGIN_ERROR(0,"账户或密码错误"),

    FAILED_USER_LOCK(0,"账户已被冻结"),

    FAILED_USER_EXIST(0,"手机已被注册"),

    FAILED_USER_NO_EXIST(0,"手机已被注册"),

    FAILED_PRODUCT_PIC(0,"确认图片上传无误"),

    FAILED_ORDER_NO_STOCK(0,"库存不足");

    public int code;

    public String message;

    RuiResultConstant(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
