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
import com.rui.xb.modules.xb.entity.RuiUserLog;
import com.rui.xb.modules.xb.service.RuiUserLogService;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2018-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/xb/ruiUserLog")
public class RuiUserLogController extends BaseController {

	@Autowired
	private RuiUserLogService ruiUserLogService;
	
	@ModelAttribute
	public RuiUserLog get(@RequestParam(required=false) String id) {
		RuiUserLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ruiUserLogService.get(id);
		}
		if (entity == null){
			entity = new RuiUserLog();
		}
		return entity;
	}
	
	@RequiresPermissions("xb:ruiUserLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(RuiUserLog ruiUserLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RuiUserLog> page = ruiUserLogService.findPage(new Page<RuiUserLog>(request, response), ruiUserLog); 
		model.addAttribute("page", page);
		return "modules/xb/ruiUserLogList";
	}

	@RequiresPermissions("xb:ruiUserLog:view")
	@RequestMapping(value = "form")
	public String form(RuiUserLog ruiUserLog, Model model) {
		model.addAttribute("ruiUserLog", ruiUserLog);
		return "modules/xb/ruiUserLogForm";
	}

	@RequiresPermissions("xb:ruiUserLog:edit")
	@RequestMapping(value = "save")
	public String save(RuiUserLog ruiUserLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruiUserLog)){
			return form(ruiUserLog, model);
		}
		ruiUserLogService.save(ruiUserLog);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiUserLog/?repage";
	}
	
	@RequiresPermissions("xb:ruiUserLog:edit")
	@RequestMapping(value = "delete")
	public String delete(RuiUserLog ruiUserLog, RedirectAttributes redirectAttributes) {
		ruiUserLogService.delete(ruiUserLog);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiUserLog/?repage";
	}

}