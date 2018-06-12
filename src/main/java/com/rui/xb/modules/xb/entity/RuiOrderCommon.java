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
 * @version 2018-06-12
 */
public class RuiOrderCommon extends DataEntity<RuiOrderCommon> {
	
	private static final long serialVersionUID = 1L;
	private String orderId;		// order_id
	private String sellerId;		// seller_id
	private Date shippingTime;		// 配送时间
	private String shippingExpressId;		// 配送公司id
	private String reciverName;		// 收货人名字
	private String sendAddressId;		// 发货地址Id
	private String reviceAddressId;		// 收货人地址id
	private String pre1;		// pre1
	private String pre2;		// pre2
	private String pre3;		// pre3
	
	public RuiOrderCommon() {
		super();
	}

	public RuiOrderCommon(String id){
		super(id);
	}

	@Length(min=0, max=11, message="order_id长度必须介于 0 和 11 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=0, max=11, message="seller_id长度必须介于 0 和 11 之间")
	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getShippingTime() {
		return shippingTime;
	}

	public void setShippingTime(Date shippingTime) {
		this.shippingTime = shippingTime;
	}
	
	@Length(min=0, max=11, message="配送公司id长度必须介于 0 和 11 之间")
	public String getShippingExpressId() {
		return shippingExpressId;
	}

	public void setShippingExpressId(String shippingExpressId) {
		this.shippingExpressId = shippingExpressId;
	}
	
	@Length(min=0, max=20, message="收货人名字长度必须介于 0 和 20 之间")
	public String getReciverName() {
		return reciverName;
	}

	public void setReciverName(String reciverName) {
		this.reciverName = reciverName;
	}
	
	@Length(min=0, max=11, message="发货地址Id长度必须介于 0 和 11 之间")
	public String getSendAddressId() {
		return sendAddressId;
	}

	public void setSendAddressId(String sendAddressId) {
		this.sendAddressId = sendAddressId;
	}
	
	@Length(min=0, max=11, message="收货人地址id长度必须介于 0 和 11 之间")
	public String getReviceAddressId() {
		return reviceAddressId;
	}

	public void setReviceAddressId(String reviceAddressId) {
		this.reviceAddressId = reviceAddressId;
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