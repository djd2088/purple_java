/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.service;

import java.util.List;

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
	public void delete(RuiOrder ruiOrder) {
		super.delete(ruiOrder);
	}
	
}