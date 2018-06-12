/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.service.CrudService;
import com.rui.xb.modules.xb.entity.RuiComplain;
import com.rui.xb.modules.xb.dao.RuiComplainDao;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2018-06-12
 */
@Service
@Transactional(readOnly = true)
public class RuiComplainService extends CrudService<RuiComplainDao, RuiComplain> {

	public RuiComplain get(String id) {
		return super.get(id);
	}
	
	public List<RuiComplain> findList(RuiComplain ruiComplain) {
		return super.findList(ruiComplain);
	}
	
	public Page<RuiComplain> findPage(Page<RuiComplain> page, RuiComplain ruiComplain) {
		return super.findPage(page, ruiComplain);
	}
	
	@Transactional(readOnly = false)
	public void save(RuiComplain ruiComplain) {
		super.save(ruiComplain);
	}
	
	@Transactional(readOnly = false)
	public void delete(RuiComplain ruiComplain) {
		super.delete(ruiComplain);
	}
	
}