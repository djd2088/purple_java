package com.rui.xb.modules.xb.entity.result;

import com.google.gson.annotations.Expose;

/**
 * @Author: DuJianDong
 * @Date: 2018/6/22 下午6:47
 * @Description: *
 */
public class RuiResult {


    /**
     * 状态码：1成功，其他为失败
     */
    @Expose
    public int code;

    /**
     * 成功为success，其他为失败原因
     */
    @Expose
    public String message;

    /**
     * 数据结果集
     */
    @Expose
    public Object data;

    public RuiResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
