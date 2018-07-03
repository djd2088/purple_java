/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.dao;

import com.google.gson.JsonObject;
import com.rui.xb.common.persistence.CrudDao;
import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.persistence.annotation.MyBatisDao;
import com.rui.xb.modules.xb.entity.RuiProduct;

import java.util.List;
import java.util.Map;

/**
 * 单表生成DAO接口
 * @author ThinkGem
 * @version 2018-06-12
 */
@MyBatisDao
public interface RuiProductDao extends CrudDao<RuiProduct> {



    List<RuiProduct> findBaseProListByFilter(RuiProduct product);

    RuiProduct getProductDetailById(Integer productId);

    int insertGetId(RuiProduct product);

    List<RuiProduct> findCollectProBaseListByUserId(RuiProduct product);
}