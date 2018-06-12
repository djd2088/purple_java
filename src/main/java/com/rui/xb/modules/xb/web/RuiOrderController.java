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
import com.rui.xb.modules.xb.entity.RuiOrder;
import com.rui.xb.modules.xb.service.RuiOrderService;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2018-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/xb/ruiOrder")
public class RuiOrderController extends BaseController {

	@Autowired
	private RuiOrderService ruiOrderService;
	
	@ModelAttribute
	public RuiOrder get(@RequestParam(required=false) String id) {
		RuiOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ruiOrderService.get(id);
		}
		if (entity == null){
			entity = new RuiOrder();
		}
		return entity;
	}
	
	@RequiresPermissions("xb:ruiOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(RuiOrder ruiOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RuiOrder> page = ruiOrderService.findPage(new Page<RuiOrder>(request, response), ruiOrder); 
		model.addAttribute("page", page);
		return "modules/xb/ruiOrderList";
	}

	@RequiresPermissions("xb:ruiOrder:view")
	@RequestMapping(value = "form")
	public String form(RuiOrder ruiOrder, Model model) {
		model.addAttribute("ruiOrder", ruiOrder);
		return "modules/xb/ruiOrderForm";
	}

	@RequiresPermissions("xb:ruiOrder:edit")
	@RequestMapping(value = "save")
	public String save(RuiOrder ruiOrder, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruiOrder)){
			return form(ruiOrder, model);
		}
		ruiOrderService.save(ruiOrder);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiOrder/?repage";
	}
	
	@RequiresPermissions("xb:ruiOrder:edit")
	@RequestMapping(value = "delete")
	public String delete(RuiOrder ruiOrder, RedirectAttributes redirectAttributes) {
		ruiOrderService.delete(ruiOrder);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiOrder/?repage";
	}

}