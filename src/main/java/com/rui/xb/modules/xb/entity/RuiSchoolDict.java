/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.entity;

import org.hibernate.validator.constraints.Length;

import com.rui.xb.common.persistence.DataEntity;

/**
 * 单表生成Entity
 * @author ThinkGem
 * @version 2018-06-26
 */
public class RuiSchoolDict extends DataEntity<RuiSchoolDict> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	private String longitude;		// longitude
	private String latitude;		// latitude
	private String pre1;		// pre1
	private String pre2;		// pre2
	private String pre3;		// pre3
	
	public RuiSchoolDict() {
		super();
	}

	public RuiSchoolDict(String id){
		super(id);
	}

	@Length(min=0, max=255, message="name长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="longitude长度必须介于 0 和 255 之间")
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	@Length(min=0, max=255, message="latitude长度必须介于 0 和 255 之间")
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	@Length(min=0, max=255, message="pre1长度必须介于 0 和 255 之间")
	public String getPre1() {
		return pre1;
	}

	public void setPre1(String pre1) {
		this.pre1 = pre1;
	}
	
	@Length(min=0, max=255, message="pre2长度必须介于 0 和 255 之间")
	public String getPre2() {
		return pre2;
	}

	public void setPre2(String pre2) {
		this.pre2 = pre2;
	}
	
	@Length(min=0, max=255, message="pre3长度必须介于 0 和 255 之间")
	public String getPre3() {
		return pre3;
	}

	public void setPre3(String pre3) {
		this.pre3 = pre3;
	}
	
}