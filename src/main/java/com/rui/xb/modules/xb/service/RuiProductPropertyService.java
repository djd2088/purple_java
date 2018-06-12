/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.service.CrudService;
import com.rui.xb.modules.xb.entity.RuiProductProperty;
import com.rui.xb.modules.xb.dao.RuiProductPropertyDao;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2018-06-12
 */
@Service
@Transactional(readOnly = true)
public class RuiProductPropertyService extends CrudService<RuiProductPropertyDao, RuiProductProperty> {

	public RuiProductProperty get(String id) {
		return super.get(id);
	}
	
	public List<RuiProductProperty> findList(RuiProductProperty ruiProductProperty) {
		return super.findList(ruiProductProperty);
	}
	
	public Page<RuiProductProperty> findPage(Page<RuiProductProperty> page, RuiProductProperty ruiProductProperty) {
		return super.findPage(page, ruiProductProperty);
	}
	
	@Transactional(readOnly = false)
	public void save(RuiProductProperty ruiProductProperty) {
		super.save(ruiProductProperty);
	}
	
	@Transactional(readOnly = false)
	public void delete(RuiProductProperty ruiProductProperty) {
		super.delete(ruiProductProperty);
	}
	
}