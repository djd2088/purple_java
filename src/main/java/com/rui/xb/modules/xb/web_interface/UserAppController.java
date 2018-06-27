package com.rui.xb.modules.xb.web_interface;

import com.alibaba.fastjson.JSONObject;
import com.rui.xb.common.utils.GsonUtil;
import com.rui.xb.common.web.BaseController;
import com.rui.xb.modules.xb.entity.RuiUser;
import com.rui.xb.modules.xb.entity.result.RuiResultConstant;
import com.rui.xb.modules.xb.service.RuiUserService;
import com.rui.xb.modules.xb.utils.RongCloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: DuJianDong
 * @Date: 2018/6/26 下午4:43
 * @Description: *
 */
@Controller
@RequestMapping(value = "${api}/user")
public class UserAppController extends BaseController{

    @Autowired
    RuiUserService userService;

    /**注册*/
    @ResponseBody
    @RequestMapping(value = "sign")
    public void sign(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            JSONObject params = parseRequest(request);
            String phone = params.getString("phone");
            String pswd = params.getString("password");
            String sex = params.getString("sex");
            if (checkIsEmpty(phone,pswd,sex)){
                GsonUtil.response(RuiResultConstant.PARAMS_ERROR,null,response);
                return;
            }
            if (userService.getByPhoneAndPswd(phone,null) != null){
                GsonUtil.response(RuiResultConstant.FAILED_USER_EXIST,null,response);
                return;
            }
            RuiUser user = new RuiUser();
            user.setPhone(phone);
            user.setPassword(pswd);
            user.setSex(sex);
            user.setNickname(RongCloudService.getRandomStr(8));
            userService.save(user);
            GsonUtil.response(RuiResultConstant.SUCCESS,null,response);
        } catch (IOException e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR,null,response);
        }
    }

    /**校验短信code*/
    @ResponseBody
    @RequestMapping(value = "verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            JSONObject params = parseRequest(request);
            String code = params.getString("code");
            String sessionId = params.getString("sessionId");
            if (checkIsEmpty(code,sessionId)){
                GsonUtil.response(RuiResultConstant.PARAMS_ERROR,null,response);
                return;
            }
            boolean result = RongCloudService.verifyCode(code,sessionId);
            if (!result){
                GsonUtil.response(RuiResultConstant.FAILED,null,response);
                return;
            }
            GsonUtil.response(RuiResultConstant.SUCCESS,null,response);
        } catch (IOException e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR,null,response);
        }
    }

    /**发送短信code*/
    @ResponseBody
    @RequestMapping(value = "sendMsg")
    public void sendMsg(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            JSONObject params = parseRequest(request);
            String phone = params.getString("phone");
            if (checkIsEmpty(phone)){
                GsonUtil.response(RuiResultConstant.PARAMS_ERROR,null,response);
                return;
            }
            boolean result = RongCloudService.sendMsg(phone);
            if (!result){
                GsonUtil.response(RuiResultConstant.FAILED,null,response);
                return;
            }
            GsonUtil.response(RuiResultConstant.SUCCESS,null,response);
        } catch (IOException e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR,null,response);
        }
    }

    /**登录*/
    @ResponseBody
    @RequestMapping(value = "login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            JSONObject params = parseRequest(request);
            String phone = params.getString("phone");
            String pswd = params.getString("password");
            if (checkIsEmpty(phone,pswd)){
                GsonUtil.response(RuiResultConstant.PARAMS_ERROR,null,response);
                return;
            }
            RuiUser result = userService.getByPhoneAndPswd(phone,pswd);
            if (result == null){
                GsonUtil.response(RuiResultConstant.FAILED_USER_LOGIN_ERROR,null,response);
                return;
            }
            if (result.getIsLock().equals("1")){
                GsonUtil.response(RuiResultConstant.FAILED_USER_LOCK,null,response);
                return;
            }
            GsonUtil.response(RuiResultConstant.SUCCESS,result,response);
            result.setOnline(true);
            userService.save(result);
        } catch (IOException e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR,null,response);
        }
    }

    /**退出登录*/
    @ResponseBody
    @RequestMapping(value = "logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            JSONObject params = parseRequest(request);
            String userId = params.getString("userId");
            if (checkIsEmpty(userId)){
                GsonUtil.response(RuiResultConstant.PARAMS_ERROR,null,response);
                return;
            }
            userService.logout(userId);
            GsonUtil.response(RuiResultConstant.SUCCESS,null,response);
        } catch (IOException e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR,null,response);
        }
    }

    /**个人信息*/
    @ResponseBody
    @RequestMapping(value = "userInfo")
    public void userInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            JSONObject params = parseRequest(request);
            String userId = params.getString("userId");
            if (checkIsEmpty(userId)){
                GsonUtil.response(RuiResultConstant.PARAMS_ERROR,null,response);
                return;
            }
            RuiUser user = userService.get(userId);
            GsonUtil.response(RuiResultConstant.SUCCESS,user,response);
        } catch (IOException e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR,null,response);
        }
    }

    /**编辑个人信息*/
    @ResponseBody
    @RequestMapping(value = "editInfo",method = RequestMethod.POST)
    public void editInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            JSONObject params = parseRequest(request);
            String userId = params.getString("userId");
            if (checkIsEmpty(userId)){
                GsonUtil.response(RuiResultConstant.PARAMS_ERROR,null,response);
                return;
            }
            RuiUser user = userService.get(userId);
            if (user == null){
                GsonUtil.response(RuiResultConstant.FAILED_USER_NO_EXIST,null,response);
                return;
            }
            userService.editInfo(user,params);
            GsonUtil.response(RuiResultConstant.SUCCESS,null,response);
        } catch (IOException e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR,null,response);
        }
    }

}
