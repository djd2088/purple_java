/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length(min=0, max=255, message="投诉状态;

import com.rui.xb.common.persistence.DataEntity;

/**
 * 单表生成Entity
 * @author ThinkGem
 * @version 2018-06-12
 */
public class RuiComplain extends DataEntity<RuiComplain> {
	
	private static final long serialVersionUID = 1L;
	private String orderId;		// order_id
	private String accuserId;		// 原告id
	private String accuserName;		// accuser_name
	private String accusedId;		// 被告人id
	private String accusedName;		// accused_name
	private String complainId;		// complain_id
	private String complainPic1;		// complain_pic1
	private String complainPic2;		// complain_pic2
	private String complainPic3;		// complain_pic3
	private Date createTime;		// create_time
	private Date handleTime;		// 处理时间
	private String handlerId;		// 处理人id
	private String finalHandleMessage;		// final_handle_message
	private Date finalHandleTime;		// final_handle_time
	private String finalHandlerId;		// final_handler_id
	private String state;		// 投诉状态(10-新投诉/20-投诉通过转给被投诉人/30-被投诉人已申诉/40-提交仲裁/99-已关闭)
	private String isPlatformHandle;		// 是否平台处理
	private String pre1;		// pre1
	private String pre2;		// pre2
	private String pre3;		// pre3
	
	public RuiComplain() {
		super();
	}

	public RuiComplain(String id){
		super(id);
	}

	@Length(min=0, max=11, message="order_id长度必须介于 0 和 11 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=0, max=11, message="原告id长度必须介于 0 和 11 之间")
	public String getAccuserId() {
		return accuserId;
	}

	public void setAccuserId(String accuserId) {
		this.accuserId = accuserId;
	}
	
	@Length(min=0, max=50, message="accuser_name长度必须介于 0 和 50 之间")
	public String getAccuserName() {
		return accuserName;
	}

	public void setAccuserName(String accuserName) {
		this.accuserName = accuserName;
	}
	
	@Length(min=0, max=11, message="被告人id长度必须介于 0 和 11 之间")
	public String getAccusedId() {
		return accusedId;
	}

	public void setAccusedId(String accusedId) {
		this.accusedId = accusedId;
	}
	
	@Length(min=0, max=50, message="accused_name长度必须介于 0 和 50 之间")
	public String getAccusedName() {
		return accusedName;
	}

	public void setAccusedName(String accusedName) {
		this.accusedName = accusedName;
	}
	
	@Length(min=0, max=11, message="complain_id长度必须介于 0 和 11 之间")
	public String getComplainId() {
		return complainId;
	}

	public void setComplainId(String complainId) {
		this.complainId = complainId;
	}
	
	@Length(min=0, max=500, message="complain_pic1长度必须介于 0 和 500 之间")
	public String getComplainPic1() {
		return complainPic1;
	}

	public void setComplainPic1(String complainPic1) {
		this.complainPic1 = complainPic1;
	}
	
	@Length(min=0, max=500, message="complain_pic2长度必须介于 0 和 500 之间")
	public String getComplainPic2() {
		return complainPic2;
	}

	public void setComplainPic2(String complainPic2) {
		this.complainPic2 = complainPic2;
	}
	
	@Length(min=0, max=500, message="complain_pic3长度必须介于 0 和 500 之间")
	public String getComplainPic3() {
		return complainPic3;
	}

	public void setComplainPic3(String complainPic3) {
		this.complainPic3 = complainPic3;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}
	
	@Length(min=0, max=11, message="处理人id长度必须介于 0 和 11 之间")
	public String getHandlerId() {
		return handlerId;
	}

	public void setHandlerId(String handlerId) {
		this.handlerId = handlerId;
	}
	
	@Length(min=0, max=100, message="final_handle_message长度必须介于 0 和 100 之间")
	public String getFinalHandleMessage() {
		return finalHandleMessage;
	}

	public void setFinalHandleMessage(String finalHandleMessage) {
		this.finalHandleMessage = finalHandleMessage;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getFinalHandleTime() {
		return finalHandleTime;
	}

	public void setFinalHandleTime(Date finalHandleTime) {
		this.finalHandleTime = finalHandleTime;
	}
	
	@Length(min=0, max=11, message="final_handler_id长度必须介于 0 和 11 之间")
	public String getFinalHandlerId() {
		return finalHandlerId;
	}

	public void setFinalHandlerId(String finalHandlerId) {
		this.finalHandlerId = finalHandlerId;
	}
	
	@Length(min=0, max=255, message="投诉状态(10-新投诉/20-投诉通过转给被投诉人/30-被投诉人已申诉/40-提交仲裁/99-已关闭)长度必须介于 0 和 255 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=255, message="是否平台处理长度必须介于 0 和 255 之间")
	public String getIsPlatformHandle() {
		return isPlatformHandle;
	}

	public void setIsPlatformHandle(String isPlatformHandle) {
		this.isPlatformHandle = isPlatformHandle;
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