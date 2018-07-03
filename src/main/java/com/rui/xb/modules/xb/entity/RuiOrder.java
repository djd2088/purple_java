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

/**
 * 单表生成Entity
 * @author ThinkGem
 * @version 2018-06-12
 */
public class RuiOrder extends DataEntity<RuiOrder> {
	
	private static final long serialVersionUID = 1L;
	@Expose
	private String orderNo;		// order_no
	@Expose
	private String payNo;		// pay_no
	@Expose
	private String sellerId;		// seller_id
	private String sellerName;		// seller_name
	@Expose
	private String buyerId;		// buyer_id
	private String buyerName;		// buyer_name
	@Expose
	private Date createTime;		// create_time
	@Expose
	private Date payTime;		// pay_time
	private Date finishTime;		// finish_time
	private String goodsAmount;		// 商品总价
	@Expose
	private String orderAmount;		// 订单总价
	private String pdAmount;		// 预付金额
	@Expose
	private String freight;		// 运费
	@Expose
	private String orderType;		// 1普通订单(默认),2预定订单,3门店自提订单
	private String chainCode;		// 自提码
	@Expose
	private int state;		// 10未支付）20已支付）30已发货）40已完成）50退款中）60退货中）
	@Expose
	private String receiveAddress;		// pre2
	private String receiveId;		// pre3

	//model
	private Date endTime;
	@Expose
	private List<RuiOrderItem> items;

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


	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public String getReceiveId() {
		return receiveId;
	}

	public void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List<RuiOrderItem> getItems() {
		return items;
	}

	public void setItems(List<RuiOrderItem> items) {
		this.items = items;
	}

	public enum ORDER_STATE_TYPE {

		STATE_WAIT_PAY(10, "未支付"),

		STATE_PAYED(20,"已支付"),

		STATE_DELIVER(30,"已发货"),

		STATE_FINISH(40,"已完成"),

		STATE_REFUND(50,"退款中"),

		STATE_BACK_SALE(60,"退货中"),

		STATE_CANCEL(70,"已取消"),

		TYPE_NORMAL(1,"正常订单"),

		TYPE_BY_FACE(3,"当面交易");

		public int code;

		public String message;

		ORDER_STATE_TYPE(int code, String message) {
			this.code = code;
			this.message = message;
		}
		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}


	}
}