/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.entity;

import org.hibernate.validator.constraints.Length;
import com.rui.xb.modules.sys.entity.User;

import com.rui.xb.common.persistence.DataEntity;

/**
 * 单表生成Entity
 * @author ThinkGem
 * @version 2018-06-12
 */
public class RuiEvaluateGoods extends DataEntity<RuiEvaluateGoods> {
	
	private static final long serialVersionUID = 1L;
	private String orderId;		// order_id
	private String orderNo;		// order_no
	private String evaluateContent;		// 内容
	private String evaluateScores;		// 1-5
	private String isAnonymous;		// 是否匿名
	private String sellerId;		// seller_id
	private User user;		// user_id
	private String state;		// 0为正常 1为禁止显示
	private String evaluateImage;		// evaluate_image
	private String pre1;		// pre1
	private String pre2;		// pre2
	private String pre3;		// pre3
	
	public RuiEvaluateGoods() {
		super();
	}

	public RuiEvaluateGoods(String id){
		super(id);
	}

	@Length(min=0, max=11, message="order_id长度必须介于 0 和 11 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=0, max=20, message="order_no长度必须介于 0 和 20 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=1000, message="内容长度必须介于 0 和 1000 之间")
	public String getEvaluateContent() {
		return evaluateContent;
	}

	public void setEvaluateContent(String evaluateContent) {
		this.evaluateContent = evaluateContent;
	}
	
	@Length(min=0, max=1, message="1-5长度必须介于 0 和 1 之间")
	public String getEvaluateScores() {
		return evaluateScores;
	}

	public void setEvaluateScores(String evaluateScores) {
		this.evaluateScores = evaluateScores;
	}
	
	@Length(min=0, max=4, message="是否匿名长度必须介于 0 和 4 之间")
	public String getIsAnonymous() {
		return isAnonymous;
	}

	public void setIsAnonymous(String isAnonymous) {
		this.isAnonymous = isAnonymous;
	}
	
	@Length(min=0, max=11, message="seller_id长度必须介于 0 和 11 之间")
	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=4, message="0为正常 1为禁止显示长度必须介于 0 和 4 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=500, message="evaluate_image长度必须介于 0 和 500 之间")
	public String getEvaluateImage() {
		return evaluateImage;
	}

	public void setEvaluateImage(String evaluateImage) {
		this.evaluateImage = evaluateImage;
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