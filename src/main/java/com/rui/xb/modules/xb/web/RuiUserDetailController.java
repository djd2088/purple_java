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
import com.rui.xb.modules.xb.entity.RuiUserDetail;
import com.rui.xb.modules.xb.service.RuiUserDetailService;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2018-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/xb/ruiUserDetail")
public class RuiUserDetailController extends BaseController {

	@Autowired
	private RuiUserDetailService ruiUserDetailService;
	
	@ModelAttribute
	public RuiUserDetail get(@RequestParam(required=false) String id) {
		RuiUserDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ruiUserDetailService.get(id);
		}
		if (entity == null){
			entity = new RuiUserDetail();
		}
		return entity;
	}
	
	@RequiresPermissions("xb:ruiUserDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(RuiUserDetail ruiUserDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RuiUserDetail> page = ruiUserDetailService.findPage(new Page<RuiUserDetail>(request, response), ruiUserDetail); 
		model.addAttribute("page", page);
		return "modules/xb/ruiUserDetailList";
	}

	@RequiresPermissions("xb:ruiUserDetail:view")
	@RequestMapping(value = "form")
	public String form(RuiUserDetail ruiUserDetail, Model model) {
		model.addAttribute("ruiUserDetail", ruiUserDetail);
		return "modules/xb/ruiUserDetailForm";
	}

	@RequiresPermissions("xb:ruiUserDetail:edit")
	@RequestMapping(value = "save")
	public String save(RuiUserDetail ruiUserDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruiUserDetail)){
			return form(ruiUserDetail, model);
		}
		ruiUserDetailService.save(ruiUserDetail);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiUserDetail/?repage";
	}
	
	@RequiresPermissions("xb:ruiUserDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(RuiUserDetail ruiUserDetail, RedirectAttributes redirectAttributes) {
		ruiUserDetailService.delete(ruiUserDetail);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiUserDetail/?repage";
	}

}