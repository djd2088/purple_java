/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.service.CrudService;
import com.rui.xb.modules.xb.entity.RuiUserDetail;
import com.rui.xb.modules.xb.dao.RuiUserDetailDao;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2018-06-12
 */
@Service
@Transactional(readOnly = true)
public class RuiUserDetailService extends CrudService<RuiUserDetailDao, RuiUserDetail> {

	public RuiUserDetail get(String id) {
		return super.get(id);
	}
	
	public List<RuiUserDetail> findList(RuiUserDetail ruiUserDetail) {
		return super.findList(ruiUserDetail);
	}
	
	public Page<RuiUserDetail> findPage(Page<RuiUserDetail> page, RuiUserDetail ruiUserDetail) {
		return super.findPage(page, ruiUserDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(RuiUserDetail ruiUserDetail) {
		super.save(ruiUserDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(RuiUserDetail ruiUserDetail) {
		super.delete(ruiUserDetail);
	}
	
}