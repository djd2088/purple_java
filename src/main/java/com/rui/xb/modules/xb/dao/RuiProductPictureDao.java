/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.dao;

import com.rui.xb.common.persistence.CrudDao;
import com.rui.xb.common.persistence.annotation.MyBatisDao;
import com.rui.xb.modules.xb.entity.RuiProductPicture;

import java.util.List;

/**
 * 单表生成DAO接口
 * @author ThinkGem
 * @version 2018-06-12
 */
@MyBatisDao
public interface RuiProductPictureDao extends CrudDao<RuiProductPicture> {


    List<RuiProductPicture> findListByProductId(String productId);
}