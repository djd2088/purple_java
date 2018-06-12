/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.service.CrudService;
import com.rui.xb.modules.xb.entity.RuiHomeDetail;
import com.rui.xb.modules.xb.dao.RuiHomeDetailDao;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2018-06-12
 */
@Service
@Transactional(readOnly = true)
public class RuiHomeDetailService extends CrudService<RuiHomeDetailDao, RuiHomeDetail> {

	public RuiHomeDetail get(String id) {
		return super.get(id);
	}
	
	public List<RuiHomeDetail> findList(RuiHomeDetail ruiHomeDetail) {
		return super.findList(ruiHomeDetail);
	}
	
	public Page<RuiHomeDetail> findPage(Page<RuiHomeDetail> page, RuiHomeDetail ruiHomeDetail) {
		return super.findPage(page, ruiHomeDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(RuiHomeDetail ruiHomeDetail) {
		super.save(ruiHomeDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(RuiHomeDetail ruiHomeDetail) {
		super.delete(ruiHomeDetail);
	}
	
}