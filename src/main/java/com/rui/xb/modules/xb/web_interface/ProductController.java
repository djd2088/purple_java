package com.rui.xb.modules.xb.web_interface;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.utils.GsonUtil;
import com.rui.xb.common.utils.StringUtils;
import com.rui.xb.common.web.BaseController;
import com.rui.xb.modules.xb.entity.RuiProduct;
import com.rui.xb.modules.xb.entity.RuiProductCollect;
import com.rui.xb.modules.xb.entity.result.RuiResultConstant;
import com.rui.xb.modules.xb.service.RuiProductCollectService;
import com.rui.xb.modules.xb.service.RuiProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @Author: DuJianDong
 * @Date: 2018/6/23 下午12:56
 * @Description: *
 */
@Controller
@RequestMapping(value = "${api}/product")
public class ProductController extends BaseController{


    @Autowired
    RuiProductService productService;

    @Autowired
    RuiProductCollectService collectService;

    /**商品列表*/
    @ResponseBody
    @RequestMapping(value = "list")
    public void productList(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            JSONObject params = parseRequest(request);
            Page<RuiProduct> page = productService.findBaseProListByFilter(params);
            Map<String,Object> result = Maps.newLinkedHashMap();
            result.put("productList",page.getList());
            result.put("isOver",page.isLastPage());
            GsonUtil.response(RuiResultConstant.SUCCESS,result,response);
        } catch (IOException e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR,null,response);
        }
    }

    /**商品详情*/
    @ResponseBody
    @RequestMapping(value = "detail")
    public void detail(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            JSONObject params = parseRequest(request);
            String productId = params.getString("productId");
            String userId = params.getString("userId");
            if (checkIsEmpty(productId,userId)){
                GsonUtil.response(RuiResultConstant.PARAMS_ERROR,null,response);
            }
            RuiProduct product = productService.getProductDetailById(productId,userId);
            GsonUtil.response(RuiResultConstant.SUCCESS,product,response);
        } catch (IOException e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR,null,response);
        }
    }

    /**商品收藏*/
    @ResponseBody
    @RequestMapping(value = "collect")
    public void collect(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            JSONObject params = parseRequest(request);
            String productId = params.getString("productId");
            String userId = params.getString("userId");
            if (checkIsEmpty(productId,userId)){
                GsonUtil.response(RuiResultConstant.PARAMS_ERROR,null,response);
                return;
            }
            RuiProduct product = productService.get(productId);
            if (product.getUserId().equals(userId)){
                GsonUtil.response(RuiResultConstant.FAILED_COLLECT_USER_SAME,"",response);
                return;
            }
            if (collectService.getByProductIdAndUserId(productId,userId) != null){
                GsonUtil.response(RuiResultConstant.FAILED_COLLECT_COLLECTED,null,response);
                return;
            }
            RuiProductCollect collect = new RuiProductCollect();
            collect.setUserId(userId);
            collect.setProductId(productId);
            collectService.save(collect);
            GsonUtil.response(RuiResultConstant.SUCCESS,null,response);
        } catch (IOException e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR,null,response);
        }
    }


}
