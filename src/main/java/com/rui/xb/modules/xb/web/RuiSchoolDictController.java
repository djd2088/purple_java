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
import com.rui.xb.modules.xb.entity.RuiSchoolDict;
import com.rui.xb.modules.xb.service.RuiSchoolDictService;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2018-06-26
 */
@Controller
@RequestMapping(value = "${adminPath}/xb/ruiSchoolDict")
public class RuiSchoolDictController extends BaseController {

	@Autowired
	private RuiSchoolDictService ruiSchoolDictService;
	
	@ModelAttribute
	public RuiSchoolDict get(@RequestParam(required=false) String id) {
		RuiSchoolDict entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ruiSchoolDictService.get(id);
		}
		if (entity == null){
			entity = new RuiSchoolDict();
		}
		return entity;
	}
	
	@RequiresPermissions("xb:ruiSchoolDict:view")
	@RequestMapping(value = {"list", ""})
	public String list(RuiSchoolDict ruiSchoolDict, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RuiSchoolDict> page = ruiSchoolDictService.findPage(new Page<RuiSchoolDict>(request, response), ruiSchoolDict); 
		model.addAttribute("page", page);
		return "modules/xb/ruiSchoolDictList";
	}

	@RequiresPermissions("xb:ruiSchoolDict:view")
	@RequestMapping(value = "form")
	public String form(RuiSchoolDict ruiSchoolDict, Model model) {
		model.addAttribute("ruiSchoolDict", ruiSchoolDict);
		return "modules/xb/ruiSchoolDictForm";
	}

	@RequiresPermissions("xb:ruiSchoolDict:edit")
	@RequestMapping(value = "save")
	public String save(RuiSchoolDict ruiSchoolDict, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruiSchoolDict)){
			return form(ruiSchoolDict, model);
		}
		ruiSchoolDictService.save(ruiSchoolDict);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiSchoolDict/?repage";
	}
	
	@RequiresPermissions("xb:ruiSchoolDict:edit")
	@RequestMapping(value = "delete")
	public String delete(RuiSchoolDict ruiSchoolDict, RedirectAttributes redirectAttributes) {
		ruiSchoolDictService.delete(ruiSchoolDict);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiSchoolDict/?repage";
	}

}