/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.service.CrudService;
import com.rui.xb.modules.xb.entity.RuiBrowingHistory;
import com.rui.xb.modules.xb.dao.RuiBrowingHistoryDao;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2018-06-22
 */
@Service
@Transactional(readOnly = true)
public class RuiBrowingHistoryService extends CrudService<RuiBrowingHistoryDao, RuiBrowingHistory> {

	public RuiBrowingHistory get(String id) {
		return super.get(id);
	}
	
	public List<RuiBrowingHistory> findList(RuiBrowingHistory ruiBrowingHistory) {
		return super.findList(ruiBrowingHistory);
	}
	
	public Page<RuiBrowingHistory> findPage(Page<RuiBrowingHistory> page, RuiBrowingHistory ruiBrowingHistory) {
		return super.findPage(page, ruiBrowingHistory);
	}
	
	@Transactional(readOnly = false)
	public void save(RuiBrowingHistory ruiBrowingHistory) {
		super.save(ruiBrowingHistory);
	}
	
	@Transactional(readOnly = false)
	public void delete(RuiBrowingHistory ruiBrowingHistory) {
		super.delete(ruiBrowingHistory);
	}

	public Integer findConutByProductId(String productId){
		return dao.findCountByProductId(productId);
	}
	
}