package com.rui.xb.modules.xb.web_interface;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.utils.GsonUtil;
import com.rui.xb.common.web.BaseController;
import com.rui.xb.modules.xb.entity.RuiOrder;
import com.rui.xb.modules.xb.entity.RuiProduct;
import com.rui.xb.modules.xb.entity.result.RuiResultConstant;
import com.rui.xb.modules.xb.service.RuiOrderService;
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
 * @Date: 2018/6/28 下午7:49
 * @Description: *
 */
@Controller
@RequestMapping(value = "${api}/trade")
public class OrderTradeController extends BaseController {


    @Autowired
    RuiProductService productService;

    @Autowired
    RuiOrderService orderService;


    /**
     * 确认订单
     */
    @ResponseBody
    @RequestMapping(value = "confirmOrder")
    public void confirmOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            JSONObject params = parseRequest(request);
            String productId = params.getString("productId");//锁问题
            int number = params.getIntValue("number");
            RuiProduct product = productService.get(productId);
            if (number > product.getStock()) {
                GsonUtil.response(RuiResultConstant.FAILED_ORDER_NO_STOCK, null, response);
                return;
            }
            double price = Double.valueOf(product.getPrice());
            double total = price * number + Double.valueOf(product.getFreight());
            Map<String, Object> result = Maps.newLinkedHashMap();
            result.put("number", number);
            result.put("totalFee", total);
            result.put("freight", product.getFreight());
            result.put("productInfo", product);
            GsonUtil.response(RuiResultConstant.SUCCESS, result, response);
        } catch (Exception e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR, null, response);
        }
    }


    /**
     * 试算预订单:接支付时使用
     */
    @ResponseBody
    @RequestMapping(value = "calculatePreOrder")
    public void calculatePreOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            JSONObject params = parseRequest(request);
            String productId = params.getString("productId");//锁问题
            int number = params.getIntValue("number");
            RuiProduct product = productService.get(productId);
            if (number > product.getStock()) {
                GsonUtil.response(RuiResultConstant.FAILED_ORDER_NO_STOCK, null, response);
                return;
            }
            double price = Double.valueOf(product.getPrice());
            double total = price * number + Double.valueOf(product.getFreight());
            GsonUtil.response(RuiResultConstant.SUCCESS, total, response);
        } catch (Exception e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR, null, response);
        }
    }


    /**
     * 提交订单
     */
    @ResponseBody
    @RequestMapping(value = "submitOrder")
    public void submitOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            JSONObject params = parseRequest(request);
            String userId = params.getString("userId");
            String productId = params.getString("productId");//锁问题
            String number = params.getString("number");
            String receiveId = params.getString("receiveId");//收货Id
            String receiveAddress = params.getString("receiveAddress");
            String type = params.getString("type");
            if (checkIsEmpty(productId, number, receiveId, receiveAddress)) {
                GsonUtil.response(RuiResultConstant.PARAMS_ERROR, null, response);
                return;
            }
            RuiProduct product = productService.get(productId);
            int numbers = Integer.parseInt(number);
            if (numbers > product.getStock()) {
                GsonUtil.response(RuiResultConstant.FAILED_ORDER_NO_STOCK, null, response);
                return;
            }
            orderService.submitOrder(product, numbers, receiveId, receiveAddress, userId, type);
            if (numbers == product.getStock()){

            }
            GsonUtil.response(RuiResultConstant.SUCCESS, null, response);
        } catch (Exception e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR, null, response);
        }
    }

    /**
     * 取消订单
     */
    @ResponseBody
    @RequestMapping(value = "cancelOrder")
    public void cancelOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            JSONObject params = parseRequest(request);
            String orderId = params.getString("orderId");
            if (checkIsEmpty(orderId)){
                GsonUtil.response(RuiResultConstant.PARAMS_ERROR,null,response);
                return;
            }
            orderService.cancelOrder(orderId);
        } catch (Exception e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR,null,response);
            return;
        }
    }


    /**我买到/卖出的*/
    @ResponseBody
    @RequestMapping(value = "myBuyAndSell")
    public void myBuyAndSell(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            JSONObject params = parseRequest(request);
            int pageNo = params.getIntValue("pageNo");
            int pageSize = params.getIntValue("pageSize");
            String userId = params.getString("userId");
            String type = params.getString("type");//1买入 2//卖出
            if (checkIsEmpty(userId,type) || pageNo == 0 || pageSize == 0){
                GsonUtil.response(RuiResultConstant.PARAMS_ERROR,null,response);
                return;
            }
            RuiOrder order = new RuiOrder();
            if (type.equals("1")){
                order.setBuyerId(userId);
            }else {
                order.setSellerId(userId);
            }
            Page<RuiOrder> page = orderService.findPageByType(new Page<>(pageNo,pageSize),order);
            Map<String,Object> result = Maps.newLinkedHashMap();
            result.put("orderList",page.getList());
            result.put("isOver",page.isLastPage());
            GsonUtil.response(RuiResultConstant.SUCCESS,result,response);
        } catch (Exception e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR,null,response);
        }
    }


    /**商品详情*/
    @ResponseBody
    @RequestMapping(value = "orderDetail")
    public void orderDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            JSONObject params = parseRequest(request);
            String orderId = params.getString("orderId");
            if (checkIsEmpty(orderId)){
                GsonUtil.response(RuiResultConstant.PARAMS_ERROR,null,response);
                return;
            }

            RuiOrder order = orderService.getOrderDetailById(orderId);
            GsonUtil.response(RuiResultConstant.SUCCESS,order,response);
        } catch (Exception e) {
            e.printStackTrace();
            GsonUtil.response(RuiResultConstant.SERVER_ERROR,null,response);
        }
    }




}
