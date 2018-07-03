/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.rui.xb.common.persistence.DataEntity;

/**
 * 单表生成Entity
 * @author ThinkGem
 * @version 2018-07-02
 */
public class RuiSeqTable extends DataEntity<RuiSeqTable> {
	
	private static final long serialVersionUID = 1L;
	private String seqName;		// seq_name
	private Long currentValue;		// current_value
	private String increment;		// increment
	private String remark;		// remark
	
	public RuiSeqTable() {
		super();
	}

	public RuiSeqTable(String id){
		super(id);
	}

	@Length(min=1, max=50, message="seq_name长度必须介于 1 和 50 之间")
	public String getSeqName() {
		return seqName;
	}

	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}
	
	@NotNull(message="current_value不能为空")
	public Long getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(Long currentValue) {
		this.currentValue = currentValue;
	}
	
	@Length(min=1, max=6, message="increment长度必须介于 1 和 6 之间")
	public String getIncrement() {
		return increment;
	}

	public void setIncrement(String increment) {
		this.increment = increment;
	}
	
	@Length(min=1, max=100, message="remark长度必须介于 1 和 100 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}