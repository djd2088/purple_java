/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.entity;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.rui.xb.common.persistence.DataEntity;

/**
 * 单表生成Entity
 * @author ThinkGem
 * @version 2018-06-29
 */
public class RuiReceiveAddress extends DataEntity<RuiReceiveAddress> {
	
	private static final long serialVersionUID = 1L;
	@Expose
	private String userId;		// user_id
	@Expose
	private String name;		// name
	@Expose
	private String province;		// province
	@Expose
	private String phone;		// phone
	@Expose
	private String zipcode;		// zipcode
	@Expose
	private boolean isDefault;		// is_default
	private String isDelete;		// is_delete
	private Date createTime;		// create_time
	private Date updateTime;		// update_time
	private String location;		// pre1
	private String pre2;		// pre2
	
	public RuiReceiveAddress() {
		super();
	}

	public RuiReceiveAddress(String id){
		super(id);
	}

	@Length(min=0, max=11, message="user_id长度必须介于 0 和 11 之间")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=50, message="name长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=100, message="province长度必须介于 0 和 100 之间")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Length(min=0, max=20, message="phone长度必须介于 0 和 20 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=10, message="zipcode长度必须介于 0 和 10 之间")
	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean aDefault) {
		isDefault = aDefault;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Length(min=0, max=255, message="pre2长度必须介于 0 和 255 之间")
	public String getPre2() {
		return pre2;
	}

	public void setPre2(String pre2) {
		this.pre2 = pre2;
	}
	
}