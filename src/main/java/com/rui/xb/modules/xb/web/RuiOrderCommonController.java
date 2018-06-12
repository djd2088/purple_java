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
import com.rui.xb.modules.xb.entity.RuiOrderCommon;
import com.rui.xb.modules.xb.service.RuiOrderCommonService;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2018-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/xb/ruiOrderCommon")
public class RuiOrderCommonController extends BaseController {

	@Autowired
	private RuiOrderCommonService ruiOrderCommonService;
	
	@ModelAttribute
	public RuiOrderCommon get(@RequestParam(required=false) String id) {
		RuiOrderCommon entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ruiOrderCommonService.get(id);
		}
		if (entity == null){
			entity = new RuiOrderCommon();
		}
		return entity;
	}
	
	@RequiresPermissions("xb:ruiOrderCommon:view")
	@RequestMapping(value = {"list", ""})
	public String list(RuiOrderCommon ruiOrderCommon, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RuiOrderCommon> page = ruiOrderCommonService.findPage(new Page<RuiOrderCommon>(request, response), ruiOrderCommon); 
		model.addAttribute("page", page);
		return "modules/xb/ruiOrderCommonList";
	}

	@RequiresPermissions("xb:ruiOrderCommon:view")
	@RequestMapping(value = "form")
	public String form(RuiOrderCommon ruiOrderCommon, Model model) {
		model.addAttribute("ruiOrderCommon", ruiOrderCommon);
		return "modules/xb/ruiOrderCommonForm";
	}

	@RequiresPermissions("xb:ruiOrderCommon:edit")
	@RequestMapping(value = "save")
	public String save(RuiOrderCommon ruiOrderCommon, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruiOrderCommon)){
			return form(ruiOrderCommon, model);
		}
		ruiOrderCommonService.save(ruiOrderCommon);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiOrderCommon/?repage";
	}
	
	@RequiresPermissions("xb:ruiOrderCommon:edit")
	@RequestMapping(value = "delete")
	public String delete(RuiOrderCommon ruiOrderCommon, RedirectAttributes redirectAttributes) {
		ruiOrderCommonService.delete(ruiOrderCommon);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiOrderCommon/?repage";
	}

}