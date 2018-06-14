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
import com.rui.xb.modules.xb.entity.RuiComplain;
import com.rui.xb.modules.xb.service.RuiComplainService;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2018-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/xb/ruiComplain")
public class RuiComplainController extends BaseController {

	@Autowired
	private RuiComplainService ruiComplainService;
	
	@ModelAttribute
	public RuiComplain get(@RequestParam(required=false) String id) {
		RuiComplain entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ruiComplainService.get(id);
		}
		if (entity == null){
			entity = new RuiComplain();
		}
		return entity;
	}
	
	@RequiresPermissions("xb:ruiComplain:view")
	@RequestMapping(value = {"list", ""})
	public String list(RuiComplain ruiComplain, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RuiComplain> page = ruiComplainService.findPage(new Page<RuiComplain>(request, response), ruiComplain);
		model.addAttribute("page", page);
		return "modules/xb/ruiComplainList";
	}

	@RequiresPermissions("xb:ruiComplain:view")
	@RequestMapping(value = "form")
	public String form(RuiComplain ruiComplain, Model model) {
		model.addAttribute("ruiComplain", ruiComplain);
		return "modules/xb/ruiComplainForm";
	}

	@RequiresPermissions("xb:ruiComplain:edit")
	@RequestMapping(value = "save")
	public String save(RuiComplain ruiComplain, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruiComplain)){
			return form(ruiComplain, model);
		}
		ruiComplainService.save(ruiComplain);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiComplain/?repage";
	}
	
	@RequiresPermissions("xb:ruiComplain:edit")
	@RequestMapping(value = "delete")
	public String delete(RuiComplain ruiComplain, RedirectAttributes redirectAttributes) {
		ruiComplainService.delete(ruiComplain);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiComplain/?repage";
	}

}