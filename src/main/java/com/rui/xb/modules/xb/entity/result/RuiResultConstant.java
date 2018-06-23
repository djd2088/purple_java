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


    SERVER_ERROR(500,"服务器异常");

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
