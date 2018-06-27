/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.entity;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rui.xb.modules.sys.entity.User;

import com.rui.xb.common.persistence.DataEntity;

/**
 * 单表生成Entity
 * @author ThinkGem
 * @version 2018-06-12
 */
public class RuiProduct extends DataEntity<RuiProduct> {
	
	private static final long serialVersionUID = 1L;
	private String categoryId;		// category_id
	private String productCode;		// product_code
	@Expose
	private String productName;		// product_name
	@Expose
	private String productDesc;		// product_desc
	private String prodcutAdvert;		// 商品 广告
	private Date createTime;		// create_time
	@Expose
	private Date onlineTime;		// 上架时间
	private Date offlineTime;		// 下架时间
	private Date updateTime;		// update_time
	private String isDelete;		// is_delete
	@Expose
	private Integer state;		// 是否上架(0表示未上架, 1表示上架,10表示违规（禁售）)
	private String stateRemark;		// 违规原因
	private String auditState;		// 1通过，0未通过，10审核中
	private String auditRemark;		// 审核描述
	private String isLock;		// is_lock
	private String isCommend;		// 是否推荐
	private String freight;		// 运费，0为免运
	private String userId;		// 所属用户
	@Expose
	private String price;		// price
	private String pre2;		// pre2
	private String pre3;		// pre3


	//model字段
	private String owner;
	private String categoryName;
	private String username;
	private String phone;
	@Expose
	private boolean isAuthen;  // 用户是否认证
	@Expose
	private String schoolName;
	@Expose
	private List<RuiProductPicture> pictures;
	@Expose
	private String mainPic;
	@Expose
	private int clickCount;
	@Expose
	private boolean isCollect;
	@Expose
	private RuiUser sellerInfo;

	//query字段
	private String keyWord;
	private String orderBy;

	public RuiProduct() {
		super();
	}

	public RuiProduct(String id){
		super(id);
	}

	@Length(min=0, max=11, message="category_id长度必须介于 0 和 11 之间")
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	@Length(min=0, max=50, message="product_code长度必须介于 0 和 50 之间")
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	@Length(min=0, max=50, message="product_name长度必须介于 0 和 50 之间")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@Length(min=0, max=100, message="product_desc长度必须介于 0 和 100 之间")
	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	
	@Length(min=0, max=100, message="商品 广告长度必须介于 0 和 100 之间")
	public String getProdcutAdvert() {
		return prodcutAdvert;
	}

	public void setProdcutAdvert(String prodcutAdvert) {
		this.prodcutAdvert = prodcutAdvert;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(Date onlineTime) {
		this.onlineTime = onlineTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOfflineTime() {
		return offlineTime;
	}

	public void setOfflineTime(Date offlineTime) {
		this.offlineTime = offlineTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Length(min=0, max=4, message="is_delete长度必须介于 0 和 4 之间")
	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	@Length(min=0, max=50, message="违规原因长度必须介于 0 和 50 之间")
	public String getStateRemark() {
		return stateRemark;
	}

	public void setStateRemark(String stateRemark) {
		this.stateRemark = stateRemark;
	}
	
	@Length(min=0, max=4, message="1通过，0未通过，10审核中长度必须介于 0 和 4 之间")
	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}
	
	@Length(min=0, max=50, message="审核描述长度必须介于 0 和 50 之间")
	public String getAuditRemark() {
		return auditRemark;
	}

	public void setAuditRemark(String auditRemark) {
		this.auditRemark = auditRemark;
	}
	
	@Length(min=0, max=4, message="is_lock长度必须介于 0 和 4 之间")
	public String getIsLock() {
		return isLock;
	}

	public void setIsLock(String isLock) {
		this.isLock = isLock;
	}
	
	@Length(min=0, max=4, message="是否推荐长度必须介于 0 和 4 之间")
	public String getIsCommend() {
		return isCommend;
	}

	public void setIsCommend(String isCommend) {
		this.isCommend = isCommend;
	}
	
	public String getFreight() {
		return freight;
	}

	public void setFreight(String freight) {
		this.freight = freight;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
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

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getMainPic() {
		return mainPic;
	}

	public void setMainPic(String mainPic) {
		this.mainPic = mainPic;
	}

	public int getClickCount() {
		return clickCount;
	}

	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}

	public List<RuiProductPicture> getPictures() {
		return pictures;
	}

	public void setPictures(List<RuiProductPicture> pictures) {
		this.pictures = pictures;
	}

	public boolean isCollect() {
		return isCollect;
	}

	public void setCollect(boolean collect) {
		isCollect = collect;
	}

	public RuiUser getSellerInfo() {
		return sellerInfo;
	}

	public void setSellerInfo(RuiUser sellerInfo) {
		this.sellerInfo = sellerInfo;
	}

	public boolean isAuthen() {
		return isAuthen;
	}

	public void setAuthen(boolean authen) {
		isAuthen = authen;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
}