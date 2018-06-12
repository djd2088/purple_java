/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length(min=0, max=255, message="1普通订单;

import com.rui.xb.common.persistence.DataEntity;

/**
 * 单表生成Entity
 * @author ThinkGem
 * @version 2018-06-12
 */
public class RuiOrder extends DataEntity<RuiOrder> {
	
	private static final long serialVersionUID = 1L;
	private String orderNo;		// order_no
	private String payNo;		// pay_no
	private String sellerId;		// seller_id
	private String sellerName;		// seller_name
	private String buyerId;		// buyer_id
	private String buyerName;		// buyer_name
	private Date createTime;		// create_time
	private Date payTime;		// pay_time
	private Date finishTime;		// finish_time
	private String goodsAmount;		// 商品总价
	private String orderAmount;		// 订单总价
	private String pdAmount;		// 预付金额
	private String freight;		// 运费
	private String orderType;		// 1普通订单(默认),2预定订单,3门店自提订单
	private String chainCode;		// 自提码
	private String pre1;		// pre1
	private String pre2;		// pre2
	private String pre3;		// pre3
	
	public RuiOrder() {
		super();
	}

	public RuiOrder(String id){
		super(id);
	}

	@Length(min=0, max=20, message="order_no长度必须介于 0 和 20 之间")
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
	
	@Length(min=0, max=11, message="seller_id长度必须介于 0 和 11 之间")
	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	
	@Length(min=0, max=50, message="seller_name长度必须介于 0 和 50 之间")
	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	
	@Length(min=0, max=11, message="buyer_id长度必须介于 0 和 11 之间")
	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	
	@Length(min=0, max=50, message="buyer_name长度必须介于 0 和 50 之间")
	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
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
	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	
	public String getGoodsAmount() {
		return goodsAmount;
	}

	public void setGoodsAmount(String goodsAmount) {
		this.goodsAmount = goodsAmount;
	}
	
	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	
	public String getPdAmount() {
		return pdAmount;
	}

	public void setPdAmount(String pdAmount) {
		this.pdAmount = pdAmount;
	}
	
	public String getFreight() {
		return freight;
	}

	public void setFreight(String freight) {
		this.freight = freight;
	}
	
	@Length(min=0, max=255, message="1普通订单(默认),2预定订单,3门店自提订单长度必须介于 0 和 255 之间")
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	@Length(min=0, max=20, message="自提码长度必须介于 0 和 20 之间")
	public String getChainCode() {
		return chainCode;
	}

	public void setChainCode(String chainCode) {
		this.chainCode = chainCode;
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