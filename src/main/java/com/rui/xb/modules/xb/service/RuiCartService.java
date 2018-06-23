/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.service.CrudService;
import com.rui.xb.modules.xb.entity.RuiCart;
import com.rui.xb.modules.xb.dao.RuiCartDao;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2018-06-22
 */
@Service
@Transactional(readOnly = true)
public class RuiCartService extends CrudService<RuiCartDao, RuiCart> {

	public RuiCart get(String id) {
		return super.get(id);
	}
	
	public List<RuiCart> findList(RuiCart ruiCart) {
		return super.findList(ruiCart);
	}
	
	public Page<RuiCart> findPage(Page<RuiCart> page, RuiCart ruiCart) {
		return super.findPage(page, ruiCart);
	}
	
	@Transactional(readOnly = false)
	public void save(RuiCart ruiCart) {
		super.save(ruiCart);
	}
	
	@Transactional(readOnly = false)
	public void delete(RuiCart ruiCart) {
		super.delete(ruiCart);
	}
	
}