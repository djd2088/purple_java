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
import com.rui.xb.modules.xb.entity.RuiHomeDetail;
import com.rui.xb.modules.xb.service.RuiHomeDetailService;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2018-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/xb/ruiHomeDetail")
public class RuiHomeDetailController extends BaseController {

	@Autowired
	private RuiHomeDetailService ruiHomeDetailService;
	
	@ModelAttribute
	public RuiHomeDetail get(@RequestParam(required=false) String id) {
		RuiHomeDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ruiHomeDetailService.get(id);
		}
		if (entity == null){
			entity = new RuiHomeDetail();
		}
		return entity;
	}
	
	@RequiresPermissions("xb:ruiHomeDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(RuiHomeDetail ruiHomeDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RuiHomeDetail> page = ruiHomeDetailService.findPage(new Page<RuiHomeDetail>(request, response), ruiHomeDetail); 
		model.addAttribute("page", page);
		return "modules/xb/ruiHomeDetailList";
	}

	@RequiresPermissions("xb:ruiHomeDetail:view")
	@RequestMapping(value = "form")
	public String form(RuiHomeDetail ruiHomeDetail, Model model) {
		model.addAttribute("ruiHomeDetail", ruiHomeDetail);
		return "modules/xb/ruiHomeDetailForm";
	}

	@RequiresPermissions("xb:ruiHomeDetail:edit")
	@RequestMapping(value = "save")
	public String save(RuiHomeDetail ruiHomeDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruiHomeDetail)){
			return form(ruiHomeDetail, model);
		}
		ruiHomeDetailService.save(ruiHomeDetail);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiHomeDetail/?repage";
	}
	
	@RequiresPermissions("xb:ruiHomeDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(RuiHomeDetail ruiHomeDetail, RedirectAttributes redirectAttributes) {
		ruiHomeDetailService.delete(ruiHomeDetail);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiHomeDetail/?repage";
	}

}