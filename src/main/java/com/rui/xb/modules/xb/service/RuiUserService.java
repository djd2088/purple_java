/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.service.CrudService;
import com.rui.xb.modules.xb.entity.RuiUser;
import com.rui.xb.modules.xb.dao.RuiUserDao;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2018-03-02
 */
@Service
@Transactional(readOnly = true)
public class RuiUserService extends CrudService<RuiUserDao, RuiUser> {

	public RuiUser get(String id) {
		return super.get(id);
	}
	
	public List<RuiUser> findList(RuiUser ruiUser) {
		return super.findList(ruiUser);
	}
	
	public Page<RuiUser> findPage(Page<RuiUser> page, RuiUser ruiUser) {
		return super.findPage(page, ruiUser);
	}
	
	@Transactional(readOnly = false)
	public void save(RuiUser ruiUser) {
		super.save(ruiUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(RuiUser ruiUser) {
		super.delete(ruiUser);
	}
	
}