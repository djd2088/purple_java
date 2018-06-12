/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.service.CrudService;
import com.rui.xb.modules.xb.entity.RuiProductHtml;
import com.rui.xb.modules.xb.dao.RuiProductHtmlDao;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2018-06-12
 */
@Service
@Transactional(readOnly = true)
public class RuiProductHtmlService extends CrudService<RuiProductHtmlDao, RuiProductHtml> {

	public RuiProductHtml get(String id) {
		return super.get(id);
	}
	
	public List<RuiProductHtml> findList(RuiProductHtml ruiProductHtml) {
		return super.findList(ruiProductHtml);
	}
	
	public Page<RuiProductHtml> findPage(Page<RuiProductHtml> page, RuiProductHtml ruiProductHtml) {
		return super.findPage(page, ruiProductHtml);
	}
	
	@Transactional(readOnly = false)
	public void save(RuiProductHtml ruiProductHtml) {
		super.save(ruiProductHtml);
	}
	
	@Transactional(readOnly = false)
	public void delete(RuiProductHtml ruiProductHtml) {
		super.delete(ruiProductHtml);
	}
	
}