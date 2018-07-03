package com.rui.xb.modules.xb.web_interface;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.utils.GsonUtil;
import com.rui.xb.common.utils.PinYinUtil;
import com.rui.xb.common.utils.StringUtils;
import com.rui.xb.common.web.BaseController;
import com.rui.xb.modules.xb.entity.RuiEvaluateGoods;
import com.rui.xb.modules.xb.entity.RuiProduct;
import com.rui.xb.modules.xb.entity.RuiProductCollect;
import com.rui.xb.modules.xb.entity.RuiSchoolDict;
import com.rui.xb.modules.xb.entity.result.RuiResultConstant;
import com.rui.xb.modules.xb.service.RuiEvaluateGoodsService;
import com.rui.xb.modules.xb.service.RuiProductCollectService;
import com.rui.xb.modules.xb.service.RuiProductService;
import com.rui.xb.modules.xb.service.RuiSchoolDictService;
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

    @Autowired
    RuiSchoolDictService schoolDictService;

    @Autowired
    RuiEvaluateGoodsService evaluateGoodsService;

    /**商品列表*/
    @ResponseBody
    @RequestMapping(value = "list")
    public void productList(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            JSONObject params = parseRequest(request);
            if (checkIsEmpty(params.getString("type"))){
                GsonUtil.response(RuiResultConstant.PARAMS_ERROR,null,response);
                return;
            }
            Page<RuiProduct> page = productService.findBaseProListByFilter(params);
            Map<String,Object> result = Maps.newLinkedHashMap();
            result.put("productList",page.getList());
            result.put("isOver",page.isLastPage());
            GsonUtil.response(RuiResultConstant.SUCCESS,result,response);
        } catch (Exception e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR,null,response);
        }
    }

    /**商品详情*/
    @ResponseBody
    @RequestMapping(value = "detail")
    public void detail(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            JSONObject params = parseRequest(request);
            String productId = params.getString("productId");
            String userId = params.getString("userId");
            if (checkIsEmpty(productId,userId)){
                GsonUtil.response(RuiResultConstant.PARAMS_ERROR,null,response);
            }
            RuiProduct product = productService.getProductDetailById(productId,userId);
            GsonUtil.response(RuiResultConstant.SUCCESS,product,response);
        } catch (Exception e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR,null,response);
        }
    }

    /**收藏商品*/
    @ResponseBody
    @RequestMapping(value = "collect")
    public void collect(HttpServletRequest request, HttpServletResponse response) throws Exception {

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
        } catch (Exception e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR,null,response);
        }
    }

    /**商品评价*/
    @ResponseBody
    @RequestMapping(value = "evaluate")
    public void evaluate(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            JSONObject params = parseRequest(request);
            String orderId = params.getString("orderId");
            String userId = params.getString("userId");
            String content = params.getString("content");
            String score = params.getString("score");
            if (checkIsEmpty(orderId,userId,content,score)){
                GsonUtil.response(RuiResultConstant.PARAMS_ERROR,null,response);
                return;
            }
            evaluateGoodsService.saveAndUpdateOrder(orderId,userId,content,score);
            GsonUtil.response(RuiResultConstant.SUCCESS,null,response);
        } catch (Exception e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR,null,response);
        }
    }


    /**商品收藏/列表*/
    @ResponseBody
    @RequestMapping(value = "collectList")
    public void collectList(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            JSONObject params = parseRequest(request);
            String pageNo = params.getString("pageNo");
            String pageSize = params.getString("pageSize");
            String userId = params.getString("userId");
            String type = params.getString("type");
            if (checkIsEmpty(type,userId,pageNo,pageSize)){
                GsonUtil.response(RuiResultConstant.PARAMS_ERROR,null,response);
                return;
            }
            Page<RuiProduct> page = productService.collectList(userId,type,pageNo,pageSize);
            Map<String,Object> result = Maps.newLinkedHashMap();
            result.put("collectList",page.getList());
            result.put("isOver",page.isLastPage());
            GsonUtil.response(RuiResultConstant.SUCCESS,result,response);
        } catch (Exception e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR,null,response);
        }
    }




    /**发布闲置*/
    @ResponseBody
    @RequestMapping(value = "dispatchIdle")
    public void dispatchIdle(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            JSONObject params = parseRequest(request);
            String userId = params.getString("userId");
            String title = params.getString("title");
            String desc = params.getString("desc");
            String categoryId = params.getString("categoryId");
            String price = params.getString("price");
            String freight = params.getString("freight");
            JSONArray pics = params.getJSONArray("pics");
            if (checkIsEmpty(userId,title,desc,categoryId,price,freight)){
                GsonUtil.response(RuiResultConstant.PARAMS_ERROR,null,response);
                return;
            }
            if (pics.size() < 1){
                GsonUtil.response(RuiResultConstant.FAILED_PRODUCT_PIC,null,response);
                return;
            }
            RuiProduct product = productService.dispatchIdle(userId,title,desc,categoryId,price,freight,pics);
            GsonUtil.response(RuiResultConstant.SUCCESS,product,response);
        } catch (Exception e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR,null,response);
        }
    }

    /**发布求购*/
    @ResponseBody
    @RequestMapping(value = "dispatchRequest")
    public void dispatchRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            JSONObject params = parseRequest(request);
            String userId = params.getString("userId");
            String title = params.getString("title");
            String desc = params.getString("desc");
            String categoryId = params.getString("categoryId");
            String price = params.getString("price");
            if (checkIsEmpty(userId,title,desc,categoryId,price)){
                GsonUtil.response(RuiResultConstant.PARAMS_ERROR,null,response);
                return;
            }
            RuiProduct result = productService.dispatchRequest(userId,title,desc,categoryId,price);
            GsonUtil.response(RuiResultConstant.SUCCESS,result,response);
        } catch (Exception e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR,null,response);
        }
    }


    /**发布/求购列表*/
    @ResponseBody
    @RequestMapping(value = "IdlesOrRequests")
    public void IdlesOrRequests(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            JSONObject params = parseRequest(request);
            String userId = params.getString("userId");
            String type = params.getString("type");
            if (checkIsEmpty(userId,type)){
                GsonUtil.response(RuiResultConstant.PARAMS_ERROR,null,response);
                return;
            }
            Page<RuiProduct> page = productService.findBaseProListByFilter(params);
            Map<String,Object> result = Maps.newLinkedHashMap();
            result.put("productList",page.getList());
            result.put("isOver",page.isLastPage());
            GsonUtil.response(RuiResultConstant.SUCCESS,result,response);
        } catch (Exception e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR,null,response);
        }
    }

    /**上传图片*/
    @ResponseBody
    @RequestMapping(value = "uploadImage")
    public void uploadImage(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
//            JSONObject params = parseRequest(request);
            GsonUtil.response(RuiResultConstant.SUCCESS,schoolDictService.findList(null),response);


        } catch (Exception e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR,null,response);
        }
    }


    /**上传图片*/
    @ResponseBody
    @RequestMapping(value = "schoolDict")
    public void schoolDict(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {

            GsonUtil.response(RuiResultConstant.SUCCESS,schoolDictService.findList(null),response);
        } catch (Exception e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR,null,response);
        }
    }
    





}
