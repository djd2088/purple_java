/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rui.xb.common.config.Global;
import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.web.BaseController;
import com.rui.xb.common.utils.StringUtils;
import com.rui.xb.modules.xb.entity.RuiUser;
import com.rui.xb.modules.xb.service.RuiUserService;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2018-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/xb/ruiUser")
public class RuiUserController extends BaseController {

	@Autowired
	private RuiUserService ruiUserService;
	
	@ModelAttribute
	public RuiUser get(@RequestParam(required=false) String id) {
		RuiUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ruiUserService.get(id);
		}
		if (entity == null){
			entity = new RuiUser();
		}
		return entity;
	}
	
	@RequiresPermissions("xb:ruiUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(RuiUser ruiUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RuiUser> page = ruiUserService.findPage(new Page<RuiUser>(request, response), ruiUser); 
		model.addAttribute("page", page);
		return "modules/xb/ruiUserList";
	}

	@RequiresPermissions("xb:ruiUser:view")
	@RequestMapping(value = "form")
	public String form(RuiUser ruiUser, Model model) {
		model.addAttribute("ruiUser", ruiUser);
		return "modules/xb/ruiUserForm";
	}

	@RequiresPermissions("xb:ruiUser:edit")
	@RequestMapping(value = "save")
	public String save(RuiUser ruiUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruiUser)){
			return form(ruiUser, model);
		}
		ruiUserService.save(ruiUser);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiUser/?repage";
	}
	
	@RequiresPermissions("xb:ruiUser:edit")
	@RequestMapping(value = "delete")
	public String delete(RuiUser ruiUser, RedirectAttributes redirectAttributes) {
		ruiUserService.delete(ruiUser);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiUser/?repage";
	}

}