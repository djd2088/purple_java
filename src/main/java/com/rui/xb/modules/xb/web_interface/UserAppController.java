package com.rui.xb.modules.xb.web_interface;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.utils.GsonUtil;
import com.rui.xb.common.web.BaseController;
import com.rui.xb.modules.xb.entity.RuiProduct;
import com.rui.xb.modules.xb.entity.RuiReceiveAddress;
import com.rui.xb.modules.xb.entity.RuiUser;
import com.rui.xb.modules.xb.entity.result.RuiResultConstant;
import com.rui.xb.modules.xb.service.RuiReceiveAddressService;
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
import java.util.List;
import java.util.Map;

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


    @Autowired
    RuiReceiveAddressService addressService;

    /**注册*/
    @ResponseBody
    @RequestMapping(value = "sign",method = RequestMethod.POST)
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
        } catch (Exception e) {
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
        } catch (Exception e) {
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
        } catch (Exception e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR,null,response);
        }
    }

    /**登录*/
    @ResponseBody
    @RequestMapping(value = "login",method = RequestMethod.POST)
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
        } catch (Exception e) {
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
        } catch (Exception e) {
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
        } catch (Exception e) {
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
        } catch (Exception e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR,null,response);
        }
    }

    /**收货地址列表*/
    @ResponseBody
    @RequestMapping(value = "receiveList")
    public void receiveList(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            JSONObject params = parseRequest(request);
            String userId = params.getString("userId");
            if (checkIsEmpty(userId)){
                GsonUtil.response(RuiResultConstant.PARAMS_ERROR,null,response);
                return;
            }
            RuiReceiveAddress address = new RuiReceiveAddress();
            address.setUserId(userId);
            List<RuiReceiveAddress> result = addressService.findList(address);
            GsonUtil.response(RuiResultConstant.SUCCESS,result,response);
        } catch (Exception e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR,null,response);
        }
    }


    /**添加/删除收货地址*/
    @ResponseBody
    @RequestMapping(value = "addDeleteReceive",method = RequestMethod.POST)
    public void addReceive(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            JSONObject params = parseRequest(request);
            String type = params.getString("type");//1添加 0删除
            String userId = params.getString("userId");

            if (checkIsEmpty(userId,type)){
                GsonUtil.response(RuiResultConstant.PARAMS_ERROR,null,response);
                return;
            }
            RuiReceiveAddress address = new RuiReceiveAddress();
            if (type.equals("1")){
                String name = params.getString("name");
                String province = params.getString("province");
                String location = params.getString("location");
                String phone = params.getString("phone");
                boolean isDefault = params.getBoolean("isDefault");
                address.setUserId(userId);
                address.setName(name);
                address.setProvince(province);
                address.setLocation(location);
                address.setPhone(phone);
                address.setDefault(isDefault);
                addressService.save(address);
            }else {
                String receiveId = params.getString("receiveId");
                address.setId(receiveId);
                addressService.delete(address);
            }
            GsonUtil.response(RuiResultConstant.SUCCESS,null,response);
        } catch (Exception e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR,null,response);
        }
    }

    /**编辑收货地址*/
    @ResponseBody
    @RequestMapping(value = "editReceive",method = RequestMethod.POST)
    public void editReceive(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            JSONObject params = parseRequest(request);
            String userId = params.getString("userId");
            String receiveId = params.getString("receiveId");
            if (checkIsEmpty(userId,receiveId)){
                GsonUtil.response(RuiResultConstant.PARAMS_ERROR,null,response);
                return;
            }
            addressService.editReceive(params);
            GsonUtil.response(RuiResultConstant.SUCCESS,null,response);
        } catch (Exception e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR,null,response);
        }
    }



}
