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
import com.rui.xb.modules.xb.entity.RuiReceiveAddress;
import com.rui.xb.modules.xb.service.RuiReceiveAddressService;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2018-06-29
 */
@Controller
@RequestMapping(value = "${adminPath}/xb/ruiReceiveAddress")
public class RuiReceiveAddressController extends BaseController {

	@Autowired
	private RuiReceiveAddressService ruiReceiveAddressService;
	
	@ModelAttribute
	public RuiReceiveAddress get(@RequestParam(required=false) String id) {
		RuiReceiveAddress entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ruiReceiveAddressService.get(id);
		}
		if (entity == null){
			entity = new RuiReceiveAddress();
		}
		return entity;
	}
	
	@RequiresPermissions("xb:ruiReceiveAddress:view")
	@RequestMapping(value = {"list", ""})
	public String list(RuiReceiveAddress ruiReceiveAddress, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RuiReceiveAddress> page = ruiReceiveAddressService.findPage(new Page<RuiReceiveAddress>(request, response), ruiReceiveAddress); 
		model.addAttribute("page", page);
		return "modules/xb/ruiReceiveAddressList";
	}

	@RequiresPermissions("xb:ruiReceiveAddress:view")
	@RequestMapping(value = "form")
	public String form(RuiReceiveAddress ruiReceiveAddress, Model model) {
		model.addAttribute("ruiReceiveAddress", ruiReceiveAddress);
		return "modules/xb/ruiReceiveAddressForm";
	}

	@RequiresPermissions("xb:ruiReceiveAddress:edit")
	@RequestMapping(value = "save")
	public String save(RuiReceiveAddress ruiReceiveAddress, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruiReceiveAddress)){
			return form(ruiReceiveAddress, model);
		}
		ruiReceiveAddressService.save(ruiReceiveAddress);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiReceiveAddress/?repage";
	}
	
	@RequiresPermissions("xb:ruiReceiveAddress:edit")
	@RequestMapping(value = "delete")
	public String delete(RuiReceiveAddress ruiReceiveAddress, RedirectAttributes redirectAttributes) {
		ruiReceiveAddressService.delete(ruiReceiveAddress);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiReceiveAddress/?repage";
	}

}