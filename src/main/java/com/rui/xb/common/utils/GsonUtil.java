package com.rui.xb.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.rui.xb.modules.xb.entity.result.RuiResult;
import com.rui.xb.modules.xb.entity.result.RuiResultConstant;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:  DuJianDong
 * @Date: 2018/5/7 下午4:30
 * @Description: *
 */
public class GsonUtil {


    public static void response(RuiResultConstant constant,Object data, HttpServletResponse response) throws
            IOException {

        response.reset();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(getGsonInstance().toJson(new RuiResult(constant.getCode(),constant.getMessage(),
                data)));
        return;
    }

    public static String getJsonString(Object data) {
        return getGsonInstance().toJson(data);
    }

    public static String getNormalString(Object data){
        return new Gson().toJson(data);
    }


    private static class GsonHolder {
        private static final Gson FACTORY_BEAN = GsonUtil.configGson();
    }

    public static Gson getGsonInstance() {
        return GsonHolder.FACTORY_BEAN;
    }

    private static Gson configGson() {
        return new GsonBuilder()
                .serializeNulls()
                .disableHtmlEscaping()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .excludeFieldsWithoutExposeAnnotation().create();

    }

    private static class GsonParserHolder {
        private static final JsonParser FACTORY_BEAN = new JsonParser();
    }

    public static JsonParser getGsonParser() {
        return GsonParserHolder.FACTORY_BEAN;
    }






}
