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
import com.rui.xb.modules.xb.entity.RuiBugAdvice;
import com.rui.xb.modules.xb.service.RuiBugAdviceService;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2018-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/xb/ruiBugAdvice")
public class RuiBugAdviceController extends BaseController {

	@Autowired
	private RuiBugAdviceService ruiBugAdviceService;
	
	@ModelAttribute
	public RuiBugAdvice get(@RequestParam(required=false) String id) {
		RuiBugAdvice entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ruiBugAdviceService.get(id);
		}
		if (entity == null){
			entity = new RuiBugAdvice();
		}
		return entity;
	}
	
	@RequiresPermissions("xb:ruiBugAdvice:view")
	@RequestMapping(value = {"list", ""})
	public String list(RuiBugAdvice ruiBugAdvice, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RuiBugAdvice> page = ruiBugAdviceService.findPage(new Page<RuiBugAdvice>(request, response), ruiBugAdvice); 
		model.addAttribute("page", page);
		return "modules/xb/ruiBugAdviceList";
	}

	@RequiresPermissions("xb:ruiBugAdvice:view")
	@RequestMapping(value = "form")
	public String form(RuiBugAdvice ruiBugAdvice, Model model) {
		model.addAttribute("ruiBugAdvice", ruiBugAdvice);
		return "modules/xb/ruiBugAdviceForm";
	}

	@RequiresPermissions("xb:ruiBugAdvice:edit")
	@RequestMapping(value = "save")
	public String save(RuiBugAdvice ruiBugAdvice, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruiBugAdvice)){
			return form(ruiBugAdvice, model);
		}
		ruiBugAdviceService.save(ruiBugAdvice);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiBugAdvice/?repage";
	}
	
	@RequiresPermissions("xb:ruiBugAdvice:edit")
	@RequestMapping(value = "delete")
	public String delete(RuiBugAdvice ruiBugAdvice, RedirectAttributes redirectAttributes) {
		ruiBugAdviceService.delete(ruiBugAdvice);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiBugAdvice/?repage";
	}

	@RequiresPermissions("xb:ruiBugAdvice:edit")
	@RequestMapping(value = "deal")
	public String deal(RuiBugAdvice ruiBugAdvice, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruiBugAdvice)){
			return form(ruiBugAdvice, model);
		}
		ruiBugAdvice.setIsDeal("1");
		ruiBugAdviceService.save(ruiBugAdvice);
		addMessage(redirectAttributes, "处理成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiBugAdvice/?repage";
	}

}