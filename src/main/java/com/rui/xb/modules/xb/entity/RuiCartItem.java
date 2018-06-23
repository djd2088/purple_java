/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.entity;

import org.hibernate.validator.constraints.Length;

import com.rui.xb.common.persistence.DataEntity;

/**
 * 单表生成Entity
 * @author ThinkGem
 * @version 2018-06-22
 */
public class RuiCartItem extends DataEntity<RuiCartItem> {
	
	private static final long serialVersionUID = 1L;
	private String cartId;		// cart_id
	private String sellerId;		// seller_id
	private String sellerName;		// seller_name
	private String productId;		// product_id
	private String productName;		// product_name
	private String productImg;		// product_img
	private String spec;		// spec
	private String number;		// number
	
	public RuiCartItem() {
		super();
	}

	public RuiCartItem(String id){
		super(id);
	}

	@Length(min=0, max=11, message="cart_id长度必须介于 0 和 11 之间")
	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	
	@Length(min=0, max=11, message="seller_id长度必须介于 0 和 11 之间")
	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	
	@Length(min=0, max=20, message="seller_name长度必须介于 0 和 20 之间")
	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	
	@Length(min=0, max=11, message="product_id长度必须介于 0 和 11 之间")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	@Length(min=0, max=50, message="product_name长度必须介于 0 和 50 之间")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@Length(min=0, max=500, message="product_img长度必须介于 0 和 500 之间")
	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	
	@Length(min=0, max=50, message="spec长度必须介于 0 和 50 之间")
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}
	
	@Length(min=0, max=11, message="number长度必须介于 0 和 11 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
}