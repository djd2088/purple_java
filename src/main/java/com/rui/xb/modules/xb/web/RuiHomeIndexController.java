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
import com.rui.xb.modules.xb.entity.RuiHomeIndex;
import com.rui.xb.modules.xb.service.RuiHomeIndexService;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2018-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/xb/ruiHomeIndex")
public class RuiHomeIndexController extends BaseController {

	@Autowired
	private RuiHomeIndexService ruiHomeIndexService;
	
	@ModelAttribute
	public RuiHomeIndex get(@RequestParam(required=false) String id) {
		RuiHomeIndex entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ruiHomeIndexService.get(id);
		}
		if (entity == null){
			entity = new RuiHomeIndex();
		}
		return entity;
	}
	
	@RequiresPermissions("xb:ruiHomeIndex:view")
	@RequestMapping(value = {"list", ""})
	public String list(RuiHomeIndex ruiHomeIndex, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RuiHomeIndex> page = ruiHomeIndexService.findPage(new Page<RuiHomeIndex>(request, response), ruiHomeIndex); 
		model.addAttribute("page", page);
		return "modules/xb/ruiHomeIndexList";
	}

	@RequiresPermissions("xb:ruiHomeIndex:view")
	@RequestMapping(value = "form")
	public String form(RuiHomeIndex ruiHomeIndex, Model model) {
		model.addAttribute("ruiHomeIndex", ruiHomeIndex);
		return "modules/xb/ruiHomeIndexForm";
	}

	@RequiresPermissions("xb:ruiHomeIndex:edit")
	@RequestMapping(value = "save")
	public String save(RuiHomeIndex ruiHomeIndex, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruiHomeIndex)){
			return form(ruiHomeIndex, model);
		}
		ruiHomeIndexService.save(ruiHomeIndex);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiHomeIndex/?repage";
	}
	
	@RequiresPermissions("xb:ruiHomeIndex:edit")
	@RequestMapping(value = "delete")
	public String delete(RuiHomeIndex ruiHomeIndex, RedirectAttributes redirectAttributes) {
		ruiHomeIndexService.delete(ruiHomeIndex);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiHomeIndex/?repage";
	}

}