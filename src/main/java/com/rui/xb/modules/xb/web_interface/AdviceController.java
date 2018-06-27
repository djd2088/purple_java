package com.rui.xb.modules.xb.web_interface;

import com.alibaba.fastjson.JSONObject;
import com.rui.xb.common.utils.GsonUtil;
import com.rui.xb.common.web.BaseController;
import com.rui.xb.modules.xb.entity.RuiBugAdvice;
import com.rui.xb.modules.xb.entity.result.RuiResultConstant;
import com.rui.xb.modules.xb.service.RuiBugAdviceService;
import com.rui.xb.modules.xb.utils.RongCloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: DuJianDong
 * @Date: 2018/6/27 下午2:20
 * @Description: *
 */
@Controller
@RequestMapping(value = "${api}/advice")
public class AdviceController extends BaseController{


    @Autowired
    RuiBugAdviceService bugAdviceService;


    /**提交意见/赞助/加入*/
    @ResponseBody
    @RequestMapping(value = "submit")
    public void sendMsg(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            JSONObject params = parseRequest(request);
            String userId = params.getString("userId");
            String type = params.getString("type");
            String content = params.getString("content");
            if (checkIsEmpty(userId,type,content)){
                GsonUtil.response(RuiResultConstant.PARAMS_ERROR,null,response);
                return;
            }
            RuiBugAdvice advice = new RuiBugAdvice();
            advice.setType(type);
            advice.setUserId(userId);
            advice.setContent(content);
            bugAdviceService.save(advice);
            GsonUtil.response(RuiResultConstant.SUCCESS,null,response);
        } catch (IOException e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR,null,response);
        }
    }
}
