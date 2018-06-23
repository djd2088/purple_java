/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.service.CrudService;
import com.rui.xb.modules.xb.entity.RuiCartItem;
import com.rui.xb.modules.xb.dao.RuiCartItemDao;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2018-06-22
 */
@Service
@Transactional(readOnly = true)
public class RuiCartItemService extends CrudService<RuiCartItemDao, RuiCartItem> {

	public RuiCartItem get(String id) {
		return super.get(id);
	}
	
	public List<RuiCartItem> findList(RuiCartItem ruiCartItem) {
		return super.findList(ruiCartItem);
	}
	
	public Page<RuiCartItem> findPage(Page<RuiCartItem> page, RuiCartItem ruiCartItem) {
		return super.findPage(page, ruiCartItem);
	}
	
	@Transactional(readOnly = false)
	public void save(RuiCartItem ruiCartItem) {
		super.save(ruiCartItem);
	}
	
	@Transactional(readOnly = false)
	public void delete(RuiCartItem ruiCartItem) {
		super.delete(ruiCartItem);
	}
	
}