/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.service.CrudService;
import com.rui.xb.modules.xb.entity.RuiHomeIndex;
import com.rui.xb.modules.xb.dao.RuiHomeIndexDao;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2018-06-12
 */
@Service
@Transactional(readOnly = true)
public class RuiHomeIndexService extends CrudService<RuiHomeIndexDao, RuiHomeIndex> {

	public RuiHomeIndex get(String id) {
		return super.get(id);
	}
	
	public List<RuiHomeIndex> findList(RuiHomeIndex ruiHomeIndex) {
		return super.findList(ruiHomeIndex);
	}
	
	public Page<RuiHomeIndex> findPage(Page<RuiHomeIndex> page, RuiHomeIndex ruiHomeIndex) {
		return super.findPage(page, ruiHomeIndex);
	}
	
	@Transactional(readOnly = false)
	public void save(RuiHomeIndex ruiHomeIndex) {
		super.save(ruiHomeIndex);
	}
	
	@Transactional(readOnly = false)
	public void delete(RuiHomeIndex ruiHomeIndex) {
		super.delete(ruiHomeIndex);
	}
	
}