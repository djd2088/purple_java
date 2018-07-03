package com.rui.xb.modules.xb.web_interface;

import com.alibaba.fastjson.JSONObject;
import com.rui.xb.common.utils.GsonUtil;
import com.rui.xb.common.web.BaseController;
import com.rui.xb.modules.xb.entity.RuiHomeIndex;
import com.rui.xb.modules.xb.entity.RuiProductCategory;
import com.rui.xb.modules.xb.entity.result.RuiResultConstant;
import com.rui.xb.modules.xb.service.RuiProductCategoryService;
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
 * @Date: 2018/6/27 下午2:57
 * @Description: *
 */
@Controller
@RequestMapping(value = "${api}/category")
public class CategoryAppController extends BaseController{


    @Autowired
    RuiProductCategoryService categoryService;


    /**获取子类目*/
    @ResponseBody
    @RequestMapping(value = "subClass")
    public void subClass(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            JSONObject params = parseRequest(request);
            String parentId = params.getString("parentId");
            RuiProductCategory category = new RuiProductCategory();
            if (parentId.equals("8")){ //热门推荐
                category.setIsCommend("1");
            }else {
                category.setParentId(parentId);
            }
            List<RuiProductCategory> categories = categoryService.findList(category);
            GsonUtil.response(RuiResultConstant.SUCCESS,categories,response);
        } catch (Exception e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR,null,response);
        }
    }

}
