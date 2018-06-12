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
import com.rui.xb.modules.xb.entity.RuiProductHtml;
import com.rui.xb.modules.xb.service.RuiProductHtmlService;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2018-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/xb/ruiProductHtml")
public class RuiProductHtmlController extends BaseController {

	@Autowired
	private RuiProductHtmlService ruiProductHtmlService;
	
	@ModelAttribute
	public RuiProductHtml get(@RequestParam(required=false) String id) {
		RuiProductHtml entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ruiProductHtmlService.get(id);
		}
		if (entity == null){
			entity = new RuiProductHtml();
		}
		return entity;
	}
	
	@RequiresPermissions("xb:ruiProductHtml:view")
	@RequestMapping(value = {"list", ""})
	public String list(RuiProductHtml ruiProductHtml, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RuiProductHtml> page = ruiProductHtmlService.findPage(new Page<RuiProductHtml>(request, response), ruiProductHtml); 
		model.addAttribute("page", page);
		return "modules/xb/ruiProductHtmlList";
	}

	@RequiresPermissions("xb:ruiProductHtml:view")
	@RequestMapping(value = "form")
	public String form(RuiProductHtml ruiProductHtml, Model model) {
		model.addAttribute("ruiProductHtml", ruiProductHtml);
		return "modules/xb/ruiProductHtmlForm";
	}

	@RequiresPermissions("xb:ruiProductHtml:edit")
	@RequestMapping(value = "save")
	public String save(RuiProductHtml ruiProductHtml, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruiProductHtml)){
			return form(ruiProductHtml, model);
		}
		ruiProductHtmlService.save(ruiProductHtml);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiProductHtml/?repage";
	}
	
	@RequiresPermissions("xb:ruiProductHtml:edit")
	@RequestMapping(value = "delete")
	public String delete(RuiProductHtml ruiProductHtml, RedirectAttributes redirectAttributes) {
		ruiProductHtmlService.delete(ruiProductHtml);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiProductHtml/?repage";
	}

}