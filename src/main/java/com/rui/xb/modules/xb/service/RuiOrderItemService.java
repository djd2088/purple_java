/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.service.CrudService;
import com.rui.xb.modules.xb.entity.RuiOrderItem;
import com.rui.xb.modules.xb.dao.RuiOrderItemDao;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2018-06-12
 */
@Service
@Transactional(readOnly = true)
public class RuiOrderItemService extends CrudService<RuiOrderItemDao, RuiOrderItem> {

	public RuiOrderItem get(String id) {
		return super.get(id);
	}
	
	public List<RuiOrderItem> findList(RuiOrderItem ruiOrderItem) {
		return super.findList(ruiOrderItem);
	}
	
	public Page<RuiOrderItem> findPage(Page<RuiOrderItem> page, RuiOrderItem ruiOrderItem) {
		return super.findPage(page, ruiOrderItem);
	}
	
	@Transactional(readOnly = false)
	public void save(RuiOrderItem ruiOrderItem) {
		super.save(ruiOrderItem);
	}
	
	@Transactional(readOnly = false)
	public void delete(RuiOrderItem ruiOrderItem) {
		super.delete(ruiOrderItem);
	}
	
}