/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.service.CrudService;
import com.rui.xb.modules.xb.entity.RuiUserLog;
import com.rui.xb.modules.xb.dao.RuiUserLogDao;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2018-06-12
 */
@Service
@Transactional(readOnly = true)
public class RuiUserLogService extends CrudService<RuiUserLogDao, RuiUserLog> {

	public RuiUserLog get(String id) {
		return super.get(id);
	}
	
	public List<RuiUserLog> findList(RuiUserLog ruiUserLog) {
		return super.findList(ruiUserLog);
	}
	
	public Page<RuiUserLog> findPage(Page<RuiUserLog> page, RuiUserLog ruiUserLog) {
		return super.findPage(page, ruiUserLog);
	}
	
	@Transactional(readOnly = false)
	public void save(RuiUserLog ruiUserLog) {
		super.save(ruiUserLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(RuiUserLog ruiUserLog) {
		super.delete(ruiUserLog);
	}
	
}