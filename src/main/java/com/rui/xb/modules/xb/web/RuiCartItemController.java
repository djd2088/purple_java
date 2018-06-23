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
import com.rui.xb.modules.xb.entity.RuiCartItem;
import com.rui.xb.modules.xb.service.RuiCartItemService;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2018-06-22
 */
@Controller
@RequestMapping(value = "${adminPath}/xb/ruiCartItem")
public class RuiCartItemController extends BaseController {

	@Autowired
	private RuiCartItemService ruiCartItemService;
	
	@ModelAttribute
	public RuiCartItem get(@RequestParam(required=false) String id) {
		RuiCartItem entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ruiCartItemService.get(id);
		}
		if (entity == null){
			entity = new RuiCartItem();
		}
		return entity;
	}
	
	@RequiresPermissions("xb:ruiCartItem:view")
	@RequestMapping(value = {"list", ""})
	public String list(RuiCartItem ruiCartItem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RuiCartItem> page = ruiCartItemService.findPage(new Page<RuiCartItem>(request, response), ruiCartItem); 
		model.addAttribute("page", page);
		return "modules/xb/ruiCartItemList";
	}

	@RequiresPermissions("xb:ruiCartItem:view")
	@RequestMapping(value = "form")
	public String form(RuiCartItem ruiCartItem, Model model) {
		model.addAttribute("ruiCartItem", ruiCartItem);
		return "modules/xb/ruiCartItemForm";
	}

	@RequiresPermissions("xb:ruiCartItem:edit")
	@RequestMapping(value = "save")
	public String save(RuiCartItem ruiCartItem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruiCartItem)){
			return form(ruiCartItem, model);
		}
		ruiCartItemService.save(ruiCartItem);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiCartItem/?repage";
	}
	
	@RequiresPermissions("xb:ruiCartItem:edit")
	@RequestMapping(value = "delete")
	public String delete(RuiCartItem ruiCartItem, RedirectAttributes redirectAttributes) {
		ruiCartItemService.delete(ruiCartItem);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiCartItem/?repage";
	}

}