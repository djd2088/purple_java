/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.service.CrudService;
import com.rui.xb.modules.xb.entity.RuiTradeOrder;
import com.rui.xb.modules.xb.dao.RuiTradeOrderDao;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2018-06-22
 */
@Service
@Transactional(readOnly = true)
public class RuiTradeOrderService extends CrudService<RuiTradeOrderDao, RuiTradeOrder> {

	public RuiTradeOrder get(String id) {
		return super.get(id);
	}
	
	public List<RuiTradeOrder> findList(RuiTradeOrder ruiTradeOrder) {
		return super.findList(ruiTradeOrder);
	}
	
	public Page<RuiTradeOrder> findPage(Page<RuiTradeOrder> page, RuiTradeOrder ruiTradeOrder) {
		return super.findPage(page, ruiTradeOrder);
	}
	
	@Transactional(readOnly = false)
	public void save(RuiTradeOrder ruiTradeOrder) {
		super.save(ruiTradeOrder);
	}
	
	@Transactional(readOnly = false)
	public void delete(RuiTradeOrder ruiTradeOrder) {
		super.delete(ruiTradeOrder);
	}
	
}