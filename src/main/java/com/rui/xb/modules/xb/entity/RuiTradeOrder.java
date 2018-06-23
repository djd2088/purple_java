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
public class RuiTradeOrder extends DataEntity<RuiTradeOrder> {
	
	private static final long serialVersionUID = 1L;
	private String tradeNo;		// trade_no
	private String orderNo;		// order_no
	private String payNo;		// pay_no
	private String payFlag;		// pay_flag 1是）0否）
	private String buyerId;		// buyer_id
	private String sellerId;		// seller_id
	private String payMethod;		// pay_method
	private String toltalFee;		// toltal_fee
	private String tradeState;		// trade_state 10已支付 20退款中 30已完成
	private Date createTime;		// create_time
	private Date payTime;		// pay_time
	private Date updateTime;		// update_time
	private String isNotify;		// is_notify
	private String notifyType;		// notify_type
	private String isDelete;		// is_delete

	private Date endTime;
	
	public RuiTradeOrder() {
		super();
	}

	public RuiTradeOrder(String id){
		super(id);
	}

	@Length(min=0, max=30, message="trade_no长度必须介于 0 和 30 之间")
	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	
	@Length(min=0, max=25, message="order_no长度必须介于 0 和 25 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=50, message="pay_no长度必须介于 0 和 50 之间")
	public String getPayNo() {
		return payNo;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	
	@Length(min=0, max=4, message="pay_flag长度必须介于 0 和 4 之间")
	public String getPayFlag() {
		return payFlag;
	}

	public void setPayFlag(String payFlag) {
		this.payFlag = payFlag;
	}
	
	@Length(min=0, max=11, message="buyer_id长度必须介于 0 和 11 之间")
	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	
	@Length(min=0, max=11, message="seller_id长度必须介于 0 和 11 之间")
	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	
	@Length(min=0, max=20, message="pay_method长度必须介于 0 和 20 之间")
	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	
	@Length(min=0, max=11, message="toltal_fee长度必须介于 0 和 11 之间")
	public String getToltalFee() {
		return toltalFee;
	}

	public void setToltalFee(String toltalFee) {
		this.toltalFee = toltalFee;
	}
	
	@Length(min=0, max=4, message="trade_state长度必须介于 0 和 4 之间")
	public String getTradeState() {
		return tradeState;
	}

	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Length(min=0, max=4, message="is_notify长度必须介于 0 和 4 之间")
	public String getIsNotify() {
		return isNotify;
	}

	public void setIsNotify(String isNotify) {
		this.isNotify = isNotify;
	}
	
	@Length(min=0, max=20, message="notify_type长度必须介于 0 和 20 之间")
	public String getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}
	
	@Length(min=0, max=4, message="is_delete长度必须介于 0 和 4 之间")
	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public enum PAY_METHOD{
		ALI_PAY,
		WECHAT_PAY
	}
	
}