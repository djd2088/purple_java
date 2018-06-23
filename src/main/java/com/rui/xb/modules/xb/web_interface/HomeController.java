package com.rui.xb.modules.xb.web_interface;

import com.google.gson.JsonObject;
import com.rui.xb.common.utils.GsonUtil;
import com.rui.xb.common.web.BaseController;
import com.rui.xb.modules.xb.entity.RuiHomeIndex;
import com.rui.xb.modules.xb.entity.result.RuiResult;
import com.rui.xb.modules.xb.entity.result.RuiResultConstant;
import com.rui.xb.modules.xb.service.RuiHomeIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: DuJianDong
 * @Date: 2018/6/22 下午4:29
 * @Description: *首页
 */
@Controller
@RequestMapping(value = "${api}/home")
public class HomeController extends BaseController{


    @Autowired
    RuiHomeIndexService homeIndexService;

    @ResponseBody
    @RequestMapping(value = "index")
    public void bannerHome(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            //RuiHomeIndex index = homeIndexService.getByType(RuiHomeIndex.TYPE.BANNER.name());
            List<RuiHomeIndex> index = homeIndexService.findList(null);
            GsonUtil.response(RuiResultConstant.SUCCESS,index,response);
        } catch (IOException e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR,null,response);
        }
    }
}
