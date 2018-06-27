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
import com.rui.xb.modules.xb.entity.RuiProductCollect;
import com.rui.xb.modules.xb.service.RuiProductCollectService;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2018-06-25
 */
@Controller
@RequestMapping(value = "${adminPath}/xb/ruiProductCollect")
public class RuiProductCollectController extends BaseController {

	@Autowired
	private RuiProductCollectService ruiProductCollectService;
	
	@ModelAttribute
	public RuiProductCollect get(@RequestParam(required=false) String id) {
		RuiProductCollect entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ruiProductCollectService.get(id);
		}
		if (entity == null){
			entity = new RuiProductCollect();
		}
		return entity;
	}
	
	@RequiresPermissions("xb:ruiProductCollect:view")
	@RequestMapping(value = {"list", ""})
	public String list(RuiProductCollect ruiProductCollect, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RuiProductCollect> page = ruiProductCollectService.findPage(new Page<RuiProductCollect>(request, response), ruiProductCollect); 
		model.addAttribute("page", page);
		return "modules/xb/ruiProductCollectList";
	}

	@RequiresPermissions("xb:ruiProductCollect:view")
	@RequestMapping(value = "form")
	public String form(RuiProductCollect ruiProductCollect, Model model) {
		model.addAttribute("ruiProductCollect", ruiProductCollect);
		return "modules/xb/ruiProductCollectForm";
	}

	@RequiresPermissions("xb:ruiProductCollect:edit")
	@RequestMapping(value = "save")
	public String save(RuiProductCollect ruiProductCollect, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruiProductCollect)){
			return form(ruiProductCollect, model);
		}
		ruiProductCollectService.save(ruiProductCollect);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiProductCollect/?repage";
	}
	
	@RequiresPermissions("xb:ruiProductCollect:edit")
	@RequestMapping(value = "delete")
	public String delete(RuiProductCollect ruiProductCollect, RedirectAttributes redirectAttributes) {
		ruiProductCollectService.delete(ruiProductCollect);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiProductCollect/?repage";
	}

}