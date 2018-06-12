/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.service.CrudService;
import com.rui.xb.modules.xb.entity.RuiRecommendProduct;
import com.rui.xb.modules.xb.dao.RuiRecommendProductDao;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2018-06-12
 */
@Service
@Transactional(readOnly = true)
public class RuiRecommendProductService extends CrudService<RuiRecommendProductDao, RuiRecommendProduct> {

	public RuiRecommendProduct get(String id) {
		return super.get(id);
	}
	
	public List<RuiRecommendProduct> findList(RuiRecommendProduct ruiRecommendProduct) {
		return super.findList(ruiRecommendProduct);
	}
	
	public Page<RuiRecommendProduct> findPage(Page<RuiRecommendProduct> page, RuiRecommendProduct ruiRecommendProduct) {
		return super.findPage(page, ruiRecommendProduct);
	}
	
	@Transactional(readOnly = false)
	public void save(RuiRecommendProduct ruiRecommendProduct) {
		super.save(ruiRecommendProduct);
	}
	
	@Transactional(readOnly = false)
	public void delete(RuiRecommendProduct ruiRecommendProduct) {
		super.delete(ruiRecommendProduct);
	}
	
}