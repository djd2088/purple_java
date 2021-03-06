/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rui.xb.common.utils.StringUtils;
import com.rui.xb.modules.xb.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.service.CrudService;
import com.rui.xb.modules.xb.dao.RuiProductDao;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2018-06-12
 */
@Service
@Transactional(readOnly = true)
public class RuiProductService extends CrudService<RuiProductDao, RuiProduct> {


	@Autowired
	RuiBrowingHistoryService browingHistoryService;

	@Autowired
	RuiProductPictureService pictureService;

	@Autowired
	RuiProductCollectService collectService;

	@Autowired
	RuiUserService userService;


	public RuiProduct get(String id) {
		return super.get(id);
	}
	
	public List<RuiProduct> findList(RuiProduct ruiProduct) {
		return super.findList(ruiProduct);
	}
	
	public Page<RuiProduct> findPage(Page<RuiProduct> page, RuiProduct ruiProduct) {
		return super.findPage(page, ruiProduct);
	}
	
	@Transactional(readOnly = false)
	public void save(RuiProduct ruiProduct) {
		super.save(ruiProduct);
	}
	
	@Transactional(readOnly = false)
	public void delete(RuiProduct ruiProduct) {
		super.delete(ruiProduct);
	}


	public Page<RuiProduct> findBaseProListByFilter(JSONObject params){
		RuiProduct product = new RuiProduct();
		int pageNo = params.getIntValue("pageNo");
		int pageSize = params.getIntValue("pageSize");
		String type = params.getString("type");
		String userId = params.getString("userId");
		String categoryId = params.getString("categoryId");
		String keyWord = params.getString("keyWord");
		String orderBy = params.getString("orderBy");
		JSONObject filter = params.getJSONObject("filter");
		Page<RuiProduct> page = new Page<>(pageNo,pageSize);
		product.setPage(page);
		product.setType(type);
		if (StringUtils.isNotEmpty(categoryId))product.setCategoryId(categoryId);
		if (StringUtils.isNotEmpty(keyWord))product.setKeyWord(keyWord);
		if (StringUtils.isNotEmpty(orderBy))product.setOrderBy(orderBy);
		if (StringUtils.isNotEmpty(userId))product.setUserId(userId);
//		if (filter != null);
		return page.setList(dao.findBaseProListByFilter(product));
	}
	@Transactional(readOnly = false)
	public RuiProduct getProductDetailById(String productId,String userId){
		RuiProduct product = super.get(productId);
		buildProduct(product,userId);

		return product;
	}

	private void buildProduct(RuiProduct product,String userId) {
		setBrowsCount(product,userId);
		setPictures(product);
		setIsCollect(product,userId);
		RuiUser seller = userService.get(product.getUserId());
		product.setSellerInfo(seller);
	}

	private void setPictures(RuiProduct product) {
		List<RuiProductPicture> pictures = pictureService.findListByProductId(product.getId());
		product.setPictures(pictures);
	}

	private void setBrowsCount(RuiProduct product,String userId) {
		if (!product.getUserId().equals(userId)){
			RuiBrowingHistory history = new RuiBrowingHistory();
			history.setProductId(product.getId());
			history.setUserId(userId);
			browingHistoryService.save(history);
		}
		Integer browsCount = browingHistoryService.findConutByProductId(product.getId());
		product.setClickCount(browsCount);

	}

	private void setIsCollect(RuiProduct product,String userId) {
		RuiProductCollect collect = collectService.getByProductIdAndUserId(product.getId(),userId);
		if (collect != null)product.setCollect(true);
	}


	@Transactional(readOnly = false)
	public RuiProduct dispatchIdle(String userId, String title, String desc, String categoryId, String price, String
			freight, JSONArray pics){
		RuiProduct product = saveIdleOrRequest(userId, title, desc, categoryId, price, freight,"1");
		String id = product.getId();
		for (int i = 0; i < pics.size(); i++){
			RuiProductPicture picture = new RuiProductPicture();
			if (i == 0)picture.setIsMainPic(true);
			picture.setProductId(id);
			picture.setPicUrl(pics.get(i).toString());
			pictureService.save(picture);
		}
		return product;
	}

	private RuiProduct saveIdleOrRequest(String userId, String title, String desc, String categoryId, String price, String
            freight, String type) {
		RuiProduct product = new RuiProduct();
		product.setUserId(userId);
		product.setProductName(title);
		product.setProductDesc(desc);
		product.setCategoryId(categoryId);
		product.setPrice(price);
		product.setFreight(freight);
		product.setType(type);
		dao.insertGetId(product);
		return product;
	}

    @Transactional(readOnly = false)
    public RuiProduct dispatchRequest(String userId, String title, String desc, String categoryId, String price){
	   return saveIdleOrRequest(userId,title,desc,categoryId,price,"0","2");
    }


    public Page<RuiProduct> collectList(String userId,String type,String pageNo,String pageSize){
		RuiProduct product = new RuiProduct();
		Page<RuiProduct> page = new Page<>(Integer.parseInt(pageNo),Integer.parseInt(pageSize));
		product.setPage(page);
		product.setType(type);
		product.setUserId(userId);//实际传入是收藏者id  供collect表使用
		return page.setList(dao.findCollectProBaseListByUserId(product));
	}

}