/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.service;

import java.util.List;

import com.rui.xb.modules.xb.entity.RuiProduct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.service.CrudService;
import com.rui.xb.modules.xb.entity.RuiProductCollect;
import com.rui.xb.modules.xb.dao.RuiProductCollectDao;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2018-06-25
 */
@Service
@Transactional(readOnly = true)
public class RuiProductCollectService extends CrudService<RuiProductCollectDao, RuiProductCollect> {

	public RuiProductCollect get(String id) {
		return super.get(id);
	}
	
	public List<RuiProductCollect> findList(RuiProductCollect ruiProductCollect) {
		return super.findList(ruiProductCollect);
	}
	
	public Page<RuiProductCollect> findPage(Page<RuiProductCollect> page, RuiProductCollect ruiProductCollect) {
		return super.findPage(page, ruiProductCollect);
	}
	
	@Transactional(readOnly = false)
	public void save(RuiProductCollect ruiProductCollect) {
		super.save(ruiProductCollect);
	}
	
	@Transactional(readOnly = false)
	public void delete(RuiProductCollect ruiProductCollect) {
		super.delete(ruiProductCollect);
	}


	public RuiProductCollect getByProductIdAndUserId(String productId,String userId){
		RuiProductCollect collect = new RuiProductCollect();
		collect.setProductId(productId);
		collect.setUserId(userId);
		return dao.getByProductIdAndUserId(collect);
	}
}