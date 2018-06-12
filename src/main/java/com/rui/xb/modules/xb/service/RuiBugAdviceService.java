/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.service.CrudService;
import com.rui.xb.modules.xb.entity.RuiBugAdvice;
import com.rui.xb.modules.xb.dao.RuiBugAdviceDao;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2018-06-12
 */
@Service
@Transactional(readOnly = true)
public class RuiBugAdviceService extends CrudService<RuiBugAdviceDao, RuiBugAdvice> {

	public RuiBugAdvice get(String id) {
		return super.get(id);
	}
	
	public List<RuiBugAdvice> findList(RuiBugAdvice ruiBugAdvice) {
		return super.findList(ruiBugAdvice);
	}
	
	public Page<RuiBugAdvice> findPage(Page<RuiBugAdvice> page, RuiBugAdvice ruiBugAdvice) {
		return super.findPage(page, ruiBugAdvice);
	}
	
	@Transactional(readOnly = false)
	public void save(RuiBugAdvice ruiBugAdvice) {
		super.save(ruiBugAdvice);
	}
	
	@Transactional(readOnly = false)
	public void delete(RuiBugAdvice ruiBugAdvice) {
		super.delete(ruiBugAdvice);
	}
	
}