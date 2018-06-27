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
import com.rui.xb.modules.xb.entity.RuiUser;
import com.rui.xb.modules.xb.dao.RuiUserDao;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2018-06-12
 */
@Service
@Transactional(readOnly = true)
public class RuiUserService extends CrudService<RuiUserDao, RuiUser> {

	public RuiUser get(String id) {
		return super.get(id);
	}
	
	public List<RuiUser> findList(RuiUser ruiUser) {
		return super.findList(ruiUser);
	}
	
	public Page<RuiUser> findPage(Page<RuiUser> page, RuiUser ruiUser) {
		return super.findPage(page, ruiUser);
	}
	
	@Transactional(readOnly = false)
	public void save(RuiUser ruiUser) {
		super.save(ruiUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(RuiUser ruiUser) {
		super.delete(ruiUser);
	}


	public RuiUser getByPhoneAndPswd(String phone,String pswd){
		RuiUser user = new RuiUser();
		user.setPhone(phone);
		user.setPassword(pswd);
		RuiUser result = dao.getByPhoneAndPswd(user);
		return result;
	}

    @Transactional(readOnly = false)
	public void logout(String userId){
		dao.logout(userId);
	}

	@Transactional(readOnly = false)
	public void editInfo(RuiUser user, JSONObject params){
		String nickName = params.getString("nickname");
		String sex = params.getString("sex");
		String email = params.getString("email");
		String avatar = params.getString("avatar");
		String qq = params.getString("qq");
		String wechat = params.getString("wechat");
		if (StringUtils.isNotEmpty(nickName))user.setNickname(nickName);
		if (StringUtils.isNotEmpty(sex))user.setSex(sex);
		if (StringUtils.isNotEmpty(email))user.setEmail(email);
		if (StringUtils.isNotEmpty(avatar))user.setAvatar(avatar);
		if (StringUtils.isNotEmpty(qq))user.setQq(qq);
		if (StringUtils.isNotEmpty(wechat))user.setWechat(wechat);
		super.save(user);
	}


}