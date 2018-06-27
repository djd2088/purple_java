package com.rui.xb.modules.xb.utils;

import com.google.common.collect.Maps;
import com.rui.xb.common.security.Digests;
import com.rui.xb.common.utils.Encodes;
import com.rui.xb.common.utils.HttpUtil;
import io.rong.RongCloud;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.Map;
import java.util.Random;

/**
 * @Author: DuJianDong
 * @Date: 2018/6/26 下午6:01
 * @Description: *
 */
public class RongCloudService {

    private static final String APP_KEY = "e0x9wycfe486q";
    private static final String APP_SECRET = "ytsLP1qufzLGz";
    private static final String URL = "http://api.sms.ronghub.com/";
    private static final String T_ID_SEND_MSG = "1";
    private static final String REGION = "86";
    private static final String SEND_MSG = "sendCode.json";
    private static final String VERIFY_CODE = "verifyCode.json";

    public static boolean sendMsg(String phone) throws Exception{
        Map<String,String> param = Maps.newHashMap();
        param.put("mobile",phone);
        param.put("templateId",T_ID_SEND_MSG);
        param.put("region",REGION);
        String result = HttpUtil.postForm(URL + SEND_MSG,param,getHeader());
        System.out.println(result);
        return true;
    }

    public static boolean verifyCode(String code, String sessionId) throws Exception{
        Map<String,String> param = Maps.newHashMap();
        param.put("sessionId",sessionId);
        param.put("code",code);
        String result = HttpUtil.postForm(URL + VERIFY_CODE,param,getHeader());
        System.out.println(result);
        return true;
    }

    private static Map<String,String> getHeader() throws Exception{
        String nonce = getRandomStr(6);
        String timeStamp = String.valueOf(new Date().getTime());
        Map<String,String> header = Maps.newHashMap();
        header.put("App-Key",APP_KEY);
        header.put("Nonce",nonce);
        header.put("Timestamp",timeStamp);
        header.put("Signature",getSign(nonce,timeStamp));
        return header;
    }


    public static String getSign(String nonce,String timeStamp) throws Exception{
        String sign = APP_SECRET + nonce + timeStamp;
        byte[] bytes = Digests.sha1(sign.getBytes("UTF-8"));
        return Encodes.encodeHex(bytes);
    }


    public static String getRandomStr(int length){
        String str = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<length;i++){
            int number = random.nextInt(10);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
