/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.service.CrudService;
import com.rui.xb.modules.xb.entity.RuiSchoolDict;
import com.rui.xb.modules.xb.dao.RuiSchoolDictDao;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2018-06-26
 */
@Service
@Transactional(readOnly = true)
public class RuiSchoolDictService extends CrudService<RuiSchoolDictDao, RuiSchoolDict> {

	public RuiSchoolDict get(String id) {
		return super.get(id);
	}
	
	public List<RuiSchoolDict> findList(RuiSchoolDict ruiSchoolDict) {
		return super.findList(ruiSchoolDict);
	}
	
	public Page<RuiSchoolDict> findPage(Page<RuiSchoolDict> page, RuiSchoolDict ruiSchoolDict) {
		return super.findPage(page, ruiSchoolDict);
	}
	
	@Transactional(readOnly = false)
	public void save(RuiSchoolDict ruiSchoolDict) {
		super.save(ruiSchoolDict);
	}
	
	@Transactional(readOnly = false)
	public void delete(RuiSchoolDict ruiSchoolDict) {
		super.delete(ruiSchoolDict);
	}
	
}