/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.rui.xb.common.persistence.DataEntity;

/**
 * 单表生成Entity
 * @author ThinkGem
 * @version 2018-06-22
 */
public class RuiBrowingHistory extends DataEntity<RuiBrowingHistory> {
	
	private static final long serialVersionUID = 1L;
	private String userId;		// user_id
	private String productId;		// product_id
	private String trackId;		// track_id
	private Date createTime;		// create_time
	private String isDelete;		// is_delete
	
	public RuiBrowingHistory() {
		super();
	}

	public RuiBrowingHistory(String id){
		super(id);
	}

	@Length(min=0, max=11, message="user_id长度必须介于 0 和 11 之间")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=11, message="product_id长度必须介于 0 和 11 之间")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	@Length(min=0, max=11, message="track_id长度必须介于 0 和 11 之间")
	public String getTrackId() {
		return trackId;
	}

	public void setTrackId(String trackId) {
		this.trackId = trackId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Length(min=0, max=4, message="is_delete长度必须介于 0 和 4 之间")
	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
}