/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.dao;

import com.rui.xb.common.persistence.CrudDao;
import com.rui.xb.common.persistence.annotation.MyBatisDao;
import com.rui.xb.modules.xb.entity.RuiSchoolDict;

/**
 * 单表生成DAO接口
 * @author ThinkGem
 * @version 2018-06-26
 */
@MyBatisDao
public interface RuiSchoolDictDao extends CrudDao<RuiSchoolDict> {
	
}