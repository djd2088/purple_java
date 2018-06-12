/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.service.CrudService;
import com.rui.xb.modules.xb.entity.RuiOrderCommon;
import com.rui.xb.modules.xb.dao.RuiOrderCommonDao;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2018-06-12
 */
@Service
@Transactional(readOnly = true)
public class RuiOrderCommonService extends CrudService<RuiOrderCommonDao, RuiOrderCommon> {

	public RuiOrderCommon get(String id) {
		return super.get(id);
	}
	
	public List<RuiOrderCommon> findList(RuiOrderCommon ruiOrderCommon) {
		return super.findList(ruiOrderCommon);
	}
	
	public Page<RuiOrderCommon> findPage(Page<RuiOrderCommon> page, RuiOrderCommon ruiOrderCommon) {
		return super.findPage(page, ruiOrderCommon);
	}
	
	@Transactional(readOnly = false)
	public void save(RuiOrderCommon ruiOrderCommon) {
		super.save(ruiOrderCommon);
	}
	
	@Transactional(readOnly = false)
	public void delete(RuiOrderCommon ruiOrderCommon) {
		super.delete(ruiOrderCommon);
	}
	
}