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
import com.rui.xb.modules.xb.entity.RuiOrderItem;
import com.rui.xb.modules.xb.service.RuiOrderItemService;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2018-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/xb/ruiOrderItem")
public class RuiOrderItemController extends BaseController {

	@Autowired
	private RuiOrderItemService ruiOrderItemService;
	
	@ModelAttribute
	public RuiOrderItem get(@RequestParam(required=false) String id) {
		RuiOrderItem entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ruiOrderItemService.get(id);
		}
		if (entity == null){
			entity = new RuiOrderItem();
		}
		return entity;
	}
	
	@RequiresPermissions("xb:ruiOrderItem:view")
	@RequestMapping(value = {"list", ""})
	public String list(RuiOrderItem ruiOrderItem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RuiOrderItem> page = ruiOrderItemService.findPage(new Page<RuiOrderItem>(request, response), ruiOrderItem); 
		model.addAttribute("page", page);
		return "modules/xb/ruiOrderItemList";
	}

	@RequiresPermissions("xb:ruiOrderItem:view")
	@RequestMapping(value = "form")
	public String form(RuiOrderItem ruiOrderItem, Model model) {
		model.addAttribute("ruiOrderItem", ruiOrderItem);
		return "modules/xb/ruiOrderItemForm";
	}

	@RequiresPermissions("xb:ruiOrderItem:edit")
	@RequestMapping(value = "save")
	public String save(RuiOrderItem ruiOrderItem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruiOrderItem)){
			return form(ruiOrderItem, model);
		}
		ruiOrderItemService.save(ruiOrderItem);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiOrderItem/?repage";
	}
	
	@RequiresPermissions("xb:ruiOrderItem:edit")
	@RequestMapping(value = "delete")
	public String delete(RuiOrderItem ruiOrderItem, RedirectAttributes redirectAttributes) {
		ruiOrderItemService.delete(ruiOrderItem);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiOrderItem/?repage";
	}

}