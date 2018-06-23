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
import com.rui.xb.modules.xb.entity.RuiCart;
import com.rui.xb.modules.xb.service.RuiCartService;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2018-06-22
 */
@Controller
@RequestMapping(value = "${adminPath}/xb/ruiCart")
public class RuiCartController extends BaseController {

	@Autowired
	private RuiCartService ruiCartService;
	
	@ModelAttribute
	public RuiCart get(@RequestParam(required=false) String id) {
		RuiCart entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ruiCartService.get(id);
		}
		if (entity == null){
			entity = new RuiCart();
		}
		return entity;
	}
	
	@RequiresPermissions("xb:ruiCart:view")
	@RequestMapping(value = {"list", ""})
	public String list(RuiCart ruiCart, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RuiCart> page = ruiCartService.findPage(new Page<RuiCart>(request, response), ruiCart); 
		model.addAttribute("page", page);
		return "modules/xb/ruiCartList";
	}

	@RequiresPermissions("xb:ruiCart:view")
	@RequestMapping(value = "form")
	public String form(RuiCart ruiCart, Model model) {
		model.addAttribute("ruiCart", ruiCart);
		return "modules/xb/ruiCartForm";
	}

	@RequiresPermissions("xb:ruiCart:edit")
	@RequestMapping(value = "save")
	public String save(RuiCart ruiCart, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruiCart)){
			return form(ruiCart, model);
		}
		ruiCartService.save(ruiCart);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiCart/?repage";
	}
	
	@RequiresPermissions("xb:ruiCart:edit")
	@RequestMapping(value = "delete")
	public String delete(RuiCart ruiCart, RedirectAttributes redirectAttributes) {
		ruiCartService.delete(ruiCart);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiCart/?repage";
	}

}