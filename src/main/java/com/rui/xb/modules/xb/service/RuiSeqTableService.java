/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.service;

import java.util.Date;
import java.util.List;

import com.rui.xb.common.utils.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.service.CrudService;
import com.rui.xb.modules.xb.entity.RuiSeqTable;
import com.rui.xb.modules.xb.dao.RuiSeqTableDao;

import static com.rui.xb.common.utils.CommonConstants.ORDER_NO_PREFIX;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2018-07-02
 */
@Service
@Transactional(readOnly = true)
public class RuiSeqTableService extends CrudService<RuiSeqTableDao, RuiSeqTable> {

	public RuiSeqTable get(String id) {
		return super.get(id);
	}
	
	public List<RuiSeqTable> findList(RuiSeqTable ruiSeqTable) {
		return super.findList(ruiSeqTable);
	}
	
	public Page<RuiSeqTable> findPage(Page<RuiSeqTable> page, RuiSeqTable ruiSeqTable) {
		return super.findPage(page, ruiSeqTable);
	}
	
	@Transactional(readOnly = false)
	public void save(RuiSeqTable ruiSeqTable) {
		super.save(ruiSeqTable);
	}
	
	@Transactional(readOnly = false)
	public void delete(RuiSeqTable ruiSeqTable) {
		super.delete(ruiSeqTable);
	}

	@Transactional(readOnly = false)
	public String buildOrderNo(){
		String noSeq = getSeqNextValue("ORDER_NO_SEQ");
		String dateString = DateUtils.formatDate(new Date(),"yyMMdd");
		String orderNo = ORDER_NO_PREFIX + dateString + noSeq.substring(noSeq.length() - 8, noSeq.length());
		return orderNo;
	}

	/**
	 * 根据序列名称,获取序列值
	 */
	@Transactional(rollbackFor = Exception.class)
	public String getSeqNextValue(String seqName) {
		return dao.getSeqNextValue(seqName);
	}
}