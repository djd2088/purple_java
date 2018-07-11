/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.entity;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.rui.xb.common.persistence.DataEntity;

/**
 * 单表生成Entity
 * @author ThinkGem
 * @version 2018-06-12
 */
public class RuiProductCategory extends DataEntity<RuiProductCategory> {
	
	private static final long serialVersionUID = 1L;
	@Expose
	private String name;		// name
	private String description;		// description
//	private RuiProductCategory parent;		// parent_id
	@Expose
	private String parentId;
	private String isDelete;		// is_delete
	private Date createTime;		// create_time
	private Date updateTime;		// update_time
	@Expose
	private String categoryLevel;		// pre1
	private String isCommend;		// pre2
	private String pre3;		// pre3


	private String parentName;


	@Expose
	private List<RuiProductCategory> subClass;
	
	public RuiProductCategory() {
		super();
	}

	public RuiProductCategory(String id){
		super(id);
	}

	@Length(min=0, max=20, message="name长度必须介于 0 和 20 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=50, message="description长度必须介于 0 和 50 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	@JsonBackReference
//	public RuiProductCategory getParent() {
//		return parent;
//	}
//
//	public void setParent(RuiProductCategory parent) {
//		this.parent = parent;
//	}


	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Length(min=0, max=4, message="is_delete长度必须介于 0 和 4 之间")
	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(String categoryLevel) {
		this.categoryLevel = categoryLevel;
	}

	public String getIsCommend() {
		return isCommend;
	}

	public void setIsCommend(String isCommend) {
		this.isCommend = isCommend;
	}

	@Length(min=0, max=255, message="pre3长度必须介于 0 和 255 之间")
	public String getPre3() {
		return pre3;
	}

	public void setPre3(String pre3) {
		this.pre3 = pre3;
	}


	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public List<RuiProductCategory> getSubClass() {
		return subClass;
	}

	public void setSubClass(List<RuiProductCategory> subClass) {
		this.subClass = subClass;
	}
}