/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.service.CrudService;
import com.rui.xb.modules.xb.entity.RuiOrderLog;
import com.rui.xb.modules.xb.dao.RuiOrderLogDao;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2018-06-12
 */
@Service
@Transactional(readOnly = true)
public class RuiOrderLogService extends CrudService<RuiOrderLogDao, RuiOrderLog> {

	public RuiOrderLog get(String id) {
		return super.get(id);
	}
	
	public List<RuiOrderLog> findList(RuiOrderLog ruiOrderLog) {
		return super.findList(ruiOrderLog);
	}
	
	public Page<RuiOrderLog> findPage(Page<RuiOrderLog> page, RuiOrderLog ruiOrderLog) {
		return super.findPage(page, ruiOrderLog);
	}
	
	@Transactional(readOnly = false)
	public void save(RuiOrderLog ruiOrderLog) {
		super.save(ruiOrderLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(RuiOrderLog ruiOrderLog) {
		super.delete(ruiOrderLog);
	}
	
}