/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.entity;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.rui.xb.common.persistence.DataEntity;
import org.springframework.aop.target.LazyInitTargetSource;

/**
 * 单表生成Entity
 * @author ThinkGem
 * @version 2018-06-12
 */
public class RuiHomeIndex extends DataEntity<RuiHomeIndex> {
	
	private static final long serialVersionUID = 1L;
	@Expose
	private String webName;		// web_name
	private Date createTime;		// create_time
	private String isShow;		// is_show
	private String sort;		// sort
	@Expose
	private String content;		// pre1
	private String isDelete;		// pre2
	private String pre3;		// pre3

	private List<RuiHomeDetail> details;
	
	public RuiHomeIndex() {
		super();
	}

	public RuiHomeIndex(String id){
		super(id);
	}

	@Length(min=0, max=50, message="web_name长度必须介于 0 和 50 之间")
	public String getWebName() {
		return webName;
	}

	public void setWebName(String webName) {
		this.webName = webName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Length(min=0, max=4, message="is_show长度必须介于 0 和 4 之间")
	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	
	@Length(min=0, max=4, message="sort长度必须介于 0 和 4 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	@Length(min=0, max=255, message="pre3长度必须介于 0 和 255 之间")
	public String getPre3() {
		return pre3;
	}

	public void setPre3(String pre3) {
		this.pre3 = pre3;
	}


	public List<RuiHomeDetail> getDetails() {
		return details;
	}

	public void setDetails(List<RuiHomeDetail> details) {
		this.details = details;
	}

	public enum TYPE{
		BANNER,
		CHANNEL
	}
}