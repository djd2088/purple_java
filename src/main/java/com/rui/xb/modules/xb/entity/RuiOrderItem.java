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
 * @version 2018-06-12
 */
public class RuiOrderItem extends DataEntity<RuiOrderItem> {
	
	private static final long serialVersionUID = 1L;
	@Expose
	private Long orderId;		// order_id
	@Expose
	private String unitprice;		// 商品单价
	@Expose
	private String number;		// 购买数量
	@Expose
	private String productId;		// product_id
	private String sellerId;		// seller_id
	private String buyerId;		// buyer_id
	@Expose
	private String isAppraise;		// 是否评价
	private String activityId;		// 活动id
	private String activityType;		// 活动类型
	private Date createTime;		// create_time
	private Date updateTime;		// update_time
	private String commisRate;		// 分佣比例
	@Expose
	private String mainPic;		// pre1
	@Expose
	private String productName;		// pre2
	private String pre3;		// pre3
	
	public RuiOrderItem() {
		super();
	}

	public RuiOrderItem(String id){
		super(id);
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=0, max=11, message="商品单价长度必须介于 0 和 11 之间")
	public String getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}
	
	@Length(min=0, max=11, message="购买数量长度必须介于 0 和 11 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Length(min=0, max=11, message="product_id长度必须介于 0 和 11 之间")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	@Length(min=0, max=11, message="seller_id长度必须介于 0 和 11 之间")
	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	
	@Length(min=0, max=11, message="buyer_id长度必须介于 0 和 11 之间")
	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	
	@Length(min=0, max=4, message="是否评价长度必须介于 0 和 4 之间")
	public String getIsAppraise() {
		return isAppraise;
	}

	public void setIsAppraise(String isAppraise) {
		this.isAppraise = isAppraise;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
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
	
	@Length(min=0, max=11, message="分佣比例长度必须介于 0 和 11 之间")
	public String getCommisRate() {
		return commisRate;
	}

	public void setCommisRate(String commisRate) {
		this.commisRate = commisRate;
	}

	public String getMainPic() {
		return mainPic;
	}

	public void setMainPic(String mainPic) {
		this.mainPic = mainPic;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Length(min=0, max=255, message="pre3长度必须介于 0 和 255 之间")
	public String getPre3() {
		return pre3;
	}

	public void setPre3(String pre3) {
		this.pre3 = pre3;
	}
	
}