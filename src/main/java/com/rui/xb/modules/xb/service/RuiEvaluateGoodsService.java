/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.service;

import java.util.List;

import com.rui.xb.modules.xb.entity.RuiOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.service.CrudService;
import com.rui.xb.modules.xb.entity.RuiEvaluateGoods;
import com.rui.xb.modules.xb.dao.RuiEvaluateGoodsDao;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2018-06-12
 */
@Service
@Transactional(readOnly = true)
public class RuiEvaluateGoodsService extends CrudService<RuiEvaluateGoodsDao, RuiEvaluateGoods> {


	@Autowired
	RuiOrderService orderService;

	public RuiEvaluateGoods get(String id) {
		return super.get(id);
	}
	
	public List<RuiEvaluateGoods> findList(RuiEvaluateGoods ruiEvaluateGoods) {
		return super.findList(ruiEvaluateGoods);
	}
	
	public Page<RuiEvaluateGoods> findPage(Page<RuiEvaluateGoods> page, RuiEvaluateGoods ruiEvaluateGoods) {
		return super.findPage(page, ruiEvaluateGoods);
	}
	
	@Transactional(readOnly = false)
	public void save(RuiEvaluateGoods ruiEvaluateGoods) {
		super.save(ruiEvaluateGoods);
	}
	
	@Transactional(readOnly = false)
	public void delete(RuiEvaluateGoods ruiEvaluateGoods) {
		super.delete(ruiEvaluateGoods);
	}


	public void saveAndUpdateOrder(String orderId,String userId,String content,String score){
		RuiEvaluateGoods evaluate = new RuiEvaluateGoods();
		evaluate.setOrderId(orderId);
		evaluate.setUserId(userId);
		evaluate.setEvaluateContent(content);
		evaluate.setEvaluateScores(score);
		super.save(evaluate);

		//跟新order
	}
}