/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.service.CrudService;
import com.rui.xb.modules.xb.entity.RuiProductCategory;
import com.rui.xb.modules.xb.dao.RuiProductCategoryDao;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2018-06-12
 */
@Service
@Transactional(readOnly = true)
public class RuiProductCategoryService extends CrudService<RuiProductCategoryDao, RuiProductCategory> {

	public RuiProductCategory get(String id) {
		return super.get(id);
	}
	
	public List<RuiProductCategory> findList(RuiProductCategory ruiProductCategory) {
		return super.findList(ruiProductCategory);
	}
	
	public Page<RuiProductCategory> findPage(Page<RuiProductCategory> page, RuiProductCategory ruiProductCategory) {
		return super.findPage(page, ruiProductCategory);
	}
	
	@Transactional(readOnly = false)
	public void save(RuiProductCategory ruiProductCategory) {
		super.save(ruiProductCategory);
	}
	
	@Transactional(readOnly = false)
	public void delete(RuiProductCategory ruiProductCategory) {
		super.delete(ruiProductCategory);
	}

	public List<RuiProductCategory> findParentByCategoryLevel(String level){
		return dao.findParentByCategoryLevel(level);
	}



}