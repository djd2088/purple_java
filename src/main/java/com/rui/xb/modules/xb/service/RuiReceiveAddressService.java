/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.rui.xb.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.service.CrudService;
import com.rui.xb.modules.xb.entity.RuiReceiveAddress;
import com.rui.xb.modules.xb.dao.RuiReceiveAddressDao;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2018-06-29
 */
@Service
@Transactional(readOnly = true)
public class RuiReceiveAddressService extends CrudService<RuiReceiveAddressDao, RuiReceiveAddress> {

	public RuiReceiveAddress get(String id) {
		return super.get(id);
	}
	
	public List<RuiReceiveAddress> findList(RuiReceiveAddress ruiReceiveAddress) {
		return super.findList(ruiReceiveAddress);
	}
	
	public Page<RuiReceiveAddress> findPage(Page<RuiReceiveAddress> page, RuiReceiveAddress ruiReceiveAddress) {
		return super.findPage(page, ruiReceiveAddress);
	}
	
	@Transactional(readOnly = false)
	public void save(RuiReceiveAddress ruiReceiveAddress) {
		super.save(ruiReceiveAddress);
	}
	
	@Transactional(readOnly = false)
	public void delete(RuiReceiveAddress ruiReceiveAddress) {
		super.delete(ruiReceiveAddress);
	}

	@Transactional(readOnly = false)
	public void editReceive(JSONObject params){
		String receiveId = params.getString("receiveId");
		String name = params.getString("name");
		String province = params.getString("province");
		String location = params.getString("location");
		String phone = params.getString("phone");
		boolean isDefault = params.getBoolean("isDefault");
		if (isDefault){//置位其他为0
			setNoDefault(params.getString("userId"));
		}
		RuiReceiveAddress address = get(receiveId);
		if (StringUtils.isNotEmpty(name))address.setName(name);
		if (StringUtils.isNotEmpty(province))address.setProvince(province);
		if (StringUtils.isNotEmpty(location))address.setLocation(location);
		if (StringUtils.isNotEmpty(phone))address.setPhone(phone);
		address.setDefault(isDefault);
		save(address);
	}

	public void setNoDefault(String userId){
		dao.setNoDefault(userId);
	}
	
}