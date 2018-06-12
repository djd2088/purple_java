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
import com.rui.xb.modules.xb.entity.RuiProductPicture;
import com.rui.xb.modules.xb.service.RuiProductPictureService;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2018-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/xb/ruiProductPicture")
public class RuiProductPictureController extends BaseController {

	@Autowired
	private RuiProductPictureService ruiProductPictureService;
	
	@ModelAttribute
	public RuiProductPicture get(@RequestParam(required=false) String id) {
		RuiProductPicture entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ruiProductPictureService.get(id);
		}
		if (entity == null){
			entity = new RuiProductPicture();
		}
		return entity;
	}
	
	@RequiresPermissions("xb:ruiProductPicture:view")
	@RequestMapping(value = {"list", ""})
	public String list(RuiProductPicture ruiProductPicture, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RuiProductPicture> page = ruiProductPictureService.findPage(new Page<RuiProductPicture>(request, response), ruiProductPicture); 
		model.addAttribute("page", page);
		return "modules/xb/ruiProductPictureList";
	}

	@RequiresPermissions("xb:ruiProductPicture:view")
	@RequestMapping(value = "form")
	public String form(RuiProductPicture ruiProductPicture, Model model) {
		model.addAttribute("ruiProductPicture", ruiProductPicture);
		return "modules/xb/ruiProductPictureForm";
	}

	@RequiresPermissions("xb:ruiProductPicture:edit")
	@RequestMapping(value = "save")
	public String save(RuiProductPicture ruiProductPicture, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruiProductPicture)){
			return form(ruiProductPicture, model);
		}
		ruiProductPictureService.save(ruiProductPicture);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiProductPicture/?repage";
	}
	
	@RequiresPermissions("xb:ruiProductPicture:edit")
	@RequestMapping(value = "delete")
	public String delete(RuiProductPicture ruiProductPicture, RedirectAttributes redirectAttributes) {
		ruiProductPictureService.delete(ruiProductPicture);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiProductPicture/?repage";
	}

}