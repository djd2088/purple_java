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
import com.rui.xb.modules.xb.entity.RuiTradeOrder;
import com.rui.xb.modules.xb.service.RuiTradeOrderService;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2018-06-22
 */
@Controller
@RequestMapping(value = "${adminPath}/xb/ruiTradeOrder")
public class RuiTradeOrderController extends BaseController {

	@Autowired
	private RuiTradeOrderService ruiTradeOrderService;
	
	@ModelAttribute
	public RuiTradeOrder get(@RequestParam(required=false) String id) {
		RuiTradeOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ruiTradeOrderService.get(id);
		}
		if (entity == null){
			entity = new RuiTradeOrder();
		}
		return entity;
	}
	
	@RequiresPermissions("xb:ruiTradeOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(RuiTradeOrder ruiTradeOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RuiTradeOrder> page = ruiTradeOrderService.findPage(new Page<RuiTradeOrder>(request, response), ruiTradeOrder); 
		model.addAttribute("page", page);
		return "modules/xb/ruiTradeOrderList";
	}

	@RequiresPermissions("xb:ruiTradeOrder:view")
	@RequestMapping(value = "form")
	public String form(RuiTradeOrder ruiTradeOrder, Model model) {
		model.addAttribute("ruiTradeOrder", ruiTradeOrder);
		return "modules/xb/ruiTradeOrderForm";
	}

	@RequiresPermissions("xb:ruiTradeOrder:edit")
	@RequestMapping(value = "save")
	public String save(RuiTradeOrder ruiTradeOrder, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruiTradeOrder)){
			return form(ruiTradeOrder, model);
		}
		ruiTradeOrderService.save(ruiTradeOrder);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiTradeOrder/?repage";
	}
	
	@RequiresPermissions("xb:ruiTradeOrder:edit")
	@RequestMapping(value = "delete")
	public String delete(RuiTradeOrder ruiTradeOrder, RedirectAttributes redirectAttributes) {
		ruiTradeOrderService.delete(ruiTradeOrder);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiTradeOrder/?repage";
	}

}