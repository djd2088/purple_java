/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.service;

import java.util.List;

import com.rui.xb.modules.xb.entity.RuiOrderItem;
import com.rui.xb.modules.xb.entity.RuiProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.service.CrudService;
import com.rui.xb.modules.xb.entity.RuiOrder;
import com.rui.xb.modules.xb.dao.RuiOrderDao;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2018-06-12
 */
@Service
@Transactional(readOnly = true)
public class RuiOrderService extends CrudService<RuiOrderDao, RuiOrder> {


    @Autowired
    RuiSeqTableService seqTableService;

    @Autowired
	RuiOrderItemService orderItemService;

    public RuiOrder get(String id) {
		return super.get(id);
	}
	
	public List<RuiOrder> findList(RuiOrder ruiOrder) {
		return super.findList(ruiOrder);
	}
	
	public Page<RuiOrder> findPage(Page<RuiOrder> page, RuiOrder ruiOrder) {
		return super.findPage(page, ruiOrder);
	}
	
	@Transactional(readOnly = false)
	public void save(RuiOrder ruiOrder) {
		super.save(ruiOrder);
	}

	@Transactional(readOnly = false)
	public int insertGetId(RuiOrder order){
    	return dao.insertGetId(order);
	}
	
	@Transactional(readOnly = false)
	public void delete(RuiOrder ruiOrder) {
		super.delete(ruiOrder);
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class)
	public void submitOrder(RuiProduct product, int numbers, String receiveId, String receiveAddress,String userId,
							String type){

		double price = Double.valueOf(product.getPrice());
		double goodsAmount = price * numbers;
		double orderAmount = goodsAmount + Double.valueOf(product.getFreight());

		String orderId = saveOrder(product, receiveId, receiveAddress, userId, type, goodsAmount, orderAmount);
		saveOrderItem(product, numbers, userId, orderId);
	}

	private String saveOrder(RuiProduct product, String receiveId, String receiveAddress, String userId, String type,
						double goodsAmount, double orderAmount) {
		RuiOrder order = new RuiOrder();
		order.setOrderNo(seqTableService.buildOrderNo());
		order.setOrderType(type);
		order.setBuyerId(userId);
		order.setSellerId(product.getUserId());
		order.setGoodsAmount(String.valueOf(goodsAmount));
		order.setOrderAmount(String.valueOf(orderAmount));
		order.setFreight(product.getFreight());
		order.setReceiveId(receiveId);
		order.setState(RuiOrder.ORDER_STATE_TYPE.STATE_WAIT_PAY.code);
		order.setReceiveAddress(receiveAddress);
		insertGetId(order);
		return order.getId();
	}

	private void saveOrderItem(RuiProduct product, int numbers, String userId, String orderId){
		RuiOrderItem item = new RuiOrderItem();
		item.setBuyerId(userId);
		item.setSellerId(product.getUserId());
		item.setOrderId((Long.parseLong(orderId)));
		item.setNumber(String.valueOf(numbers));
		item.setMainPic(product.getMainPic());
		item.setProductId(product.getId());
		item.setUnitprice(product.getPrice());
		item.setProductName(product.getProductName());
		orderItemService.save(item);
	}

	@Transactional(readOnly = false)
	public void cancelOrder(String orderId){
    	dao.cancelOrder(orderId);
    	//返回库存、售罄标志
	}

	public RuiOrder getOrderDetailById(String orderId){
    	RuiOrder order = super.get(orderId);
    	buildOrder(order);
    	return order;
	}


	public Page<RuiOrder> findPageByType(Page<RuiOrder> page, RuiOrder ruiOrder) {
		Page<RuiOrder> pageResult = super.findPage(page, ruiOrder);
		for (RuiOrder order : pageResult.getList()){
			buildOrder(order);
		}
		return pageResult;
	}

	private void buildOrder(RuiOrder order) {
		RuiOrderItem item = new RuiOrderItem();
		item.setOrderId(Long.parseLong(order.getId()));
		order.setItems(orderItemService.findList(item));
	}


}