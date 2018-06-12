/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.entity;

import org.hibernate.validator.constraints.Length;

import com.rui.xb.common.persistence.DataEntity;

/**
 * 单表生成Entity
 * @author ThinkGem
 * @version 2018-06-12
 */
public class RuiProductPicture extends DataEntity<RuiProductPicture> {
	
	private static final long serialVersionUID = 1L;
	private String productId;		// product_id
	private String name;		// 图片名称
	private String isMainPic;		// is_main_pic
	private String picUrl;		// pic_url
	private String sort;		// 排序
	private String pre1;		// pre1
	private String pre2;		// pre2
	private String pre3;		// pre3
	
	public RuiProductPicture() {
		super();
	}

	public RuiProductPicture(String id){
		super(id);
	}

	@Length(min=0, max=11, message="product_id长度必须介于 0 和 11 之间")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	@Length(min=0, max=20, message="图片名称长度必须介于 0 和 20 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=4, message="is_main_pic长度必须介于 0 和 4 之间")
	public String getIsMainPic() {
		return isMainPic;
	}

	public void setIsMainPic(String isMainPic) {
		this.isMainPic = isMainPic;
	}
	
	@Length(min=0, max=200, message="pic_url长度必须介于 0 和 200 之间")
	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	@Length(min=0, max=4, message="排序长度必须介于 0 和 4 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
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