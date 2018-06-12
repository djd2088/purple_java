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
import com.rui.xb.modules.xb.entity.RuiProductProperty;
import com.rui.xb.modules.xb.service.RuiProductPropertyService;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2018-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/xb/ruiProductProperty")
public class RuiProductPropertyController extends BaseController {

	@Autowired
	private RuiProductPropertyService ruiProductPropertyService;
	
	@ModelAttribute
	public RuiProductProperty get(@RequestParam(required=false) String id) {
		RuiProductProperty entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ruiProductPropertyService.get(id);
		}
		if (entity == null){
			entity = new RuiProductProperty();
		}
		return entity;
	}
	
	@RequiresPermissions("xb:ruiProductProperty:view")
	@RequestMapping(value = {"list", ""})
	public String list(RuiProductProperty ruiProductProperty, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RuiProductProperty> page = ruiProductPropertyService.findPage(new Page<RuiProductProperty>(request, response), ruiProductProperty); 
		model.addAttribute("page", page);
		return "modules/xb/ruiProductPropertyList";
	}

	@RequiresPermissions("xb:ruiProductProperty:view")
	@RequestMapping(value = "form")
	public String form(RuiProductProperty ruiProductProperty, Model model) {
		model.addAttribute("ruiProductProperty", ruiProductProperty);
		return "modules/xb/ruiProductPropertyForm";
	}

	@RequiresPermissions("xb:ruiProductProperty:edit")
	@RequestMapping(value = "save")
	public String save(RuiProductProperty ruiProductProperty, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruiProductProperty)){
			return form(ruiProductProperty, model);
		}
		ruiProductPropertyService.save(ruiProductProperty);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiProductProperty/?repage";
	}
	
	@RequiresPermissions("xb:ruiProductProperty:edit")
	@RequestMapping(value = "delete")
	public String delete(RuiProductProperty ruiProductProperty, RedirectAttributes redirectAttributes) {
		ruiProductPropertyService.delete(ruiProductProperty);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiProductProperty/?repage";
	}

}