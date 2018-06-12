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
import com.rui.xb.modules.xb.entity.RuiOrderLog;
import com.rui.xb.modules.xb.service.RuiOrderLogService;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2018-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/xb/ruiOrderLog")
public class RuiOrderLogController extends BaseController {

	@Autowired
	private RuiOrderLogService ruiOrderLogService;
	
	@ModelAttribute
	public RuiOrderLog get(@RequestParam(required=false) String id) {
		RuiOrderLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ruiOrderLogService.get(id);
		}
		if (entity == null){
			entity = new RuiOrderLog();
		}
		return entity;
	}
	
	@RequiresPermissions("xb:ruiOrderLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(RuiOrderLog ruiOrderLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RuiOrderLog> page = ruiOrderLogService.findPage(new Page<RuiOrderLog>(request, response), ruiOrderLog); 
		model.addAttribute("page", page);
		return "modules/xb/ruiOrderLogList";
	}

	@RequiresPermissions("xb:ruiOrderLog:view")
	@RequestMapping(value = "form")
	public String form(RuiOrderLog ruiOrderLog, Model model) {
		model.addAttribute("ruiOrderLog", ruiOrderLog);
		return "modules/xb/ruiOrderLogForm";
	}

	@RequiresPermissions("xb:ruiOrderLog:edit")
	@RequestMapping(value = "save")
	public String save(RuiOrderLog ruiOrderLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruiOrderLog)){
			return form(ruiOrderLog, model);
		}
		ruiOrderLogService.save(ruiOrderLog);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiOrderLog/?repage";
	}
	
	@RequiresPermissions("xb:ruiOrderLog:edit")
	@RequestMapping(value = "delete")
	public String delete(RuiOrderLog ruiOrderLog, RedirectAttributes redirectAttributes) {
		ruiOrderLogService.delete(ruiOrderLog);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiOrderLog/?repage";
	}

}