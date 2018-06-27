/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.service.CrudService;
import com.rui.xb.modules.xb.entity.RuiProductPicture;
import com.rui.xb.modules.xb.dao.RuiProductPictureDao;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2018-06-12
 */
@Service
@Transactional(readOnly = true)
public class RuiProductPictureService extends CrudService<RuiProductPictureDao, RuiProductPicture> {

	public RuiProductPicture get(String id) {
		return super.get(id);
	}
	
	public List<RuiProductPicture> findList(RuiProductPicture ruiProductPicture) {
		return super.findList(ruiProductPicture);
	}
	
	public Page<RuiProductPicture> findPage(Page<RuiProductPicture> page, RuiProductPicture ruiProductPicture) {
		return super.findPage(page, ruiProductPicture);
	}
	
	@Transactional(readOnly = false)
	public void save(RuiProductPicture ruiProductPicture) {
		super.save(ruiProductPicture);
	}
	
	@Transactional(readOnly = false)
	public void delete(RuiProductPicture ruiProductPicture) {
		super.delete(ruiProductPicture);
	}

	public List<RuiProductPicture> findListByProductId(String productId){
		return dao.findListByProductId(productId);
	}
	
}