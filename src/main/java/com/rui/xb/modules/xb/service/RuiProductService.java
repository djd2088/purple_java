/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.service.CrudService;
import com.rui.xb.modules.xb.entity.RuiProduct;
import com.rui.xb.modules.xb.dao.RuiProductDao;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2018-06-12
 */
@Service
@Transactional(readOnly = true)
public class RuiProductService extends CrudService<RuiProductDao, RuiProduct> {

	public RuiProduct get(String id) {
		return super.get(id);
	}
	
	public List<RuiProduct> findList(RuiProduct ruiProduct) {
		return super.findList(ruiProduct);
	}
	
	public Page<RuiProduct> findPage(Page<RuiProduct> page, RuiProduct ruiProduct) {
		return super.findPage(page, ruiProduct);
	}
	
	@Transactional(readOnly = false)
	public void save(RuiProduct ruiProduct) {
		super.save(ruiProduct);
	}
	
	@Transactional(readOnly = false)
	public void delete(RuiProduct ruiProduct) {
		super.delete(ruiProduct);
	}
	
}