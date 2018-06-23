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
import com.rui.xb.modules.xb.entity.RuiBrowingHistory;
import com.rui.xb.modules.xb.service.RuiBrowingHistoryService;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2018-06-22
 */
@Controller
@RequestMapping(value = "${adminPath}/xb/ruiBrowingHistory")
public class RuiBrowingHistoryController extends BaseController {

	@Autowired
	private RuiBrowingHistoryService ruiBrowingHistoryService;
	
	@ModelAttribute
	public RuiBrowingHistory get(@RequestParam(required=false) String id) {
		RuiBrowingHistory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ruiBrowingHistoryService.get(id);
		}
		if (entity == null){
			entity = new RuiBrowingHistory();
		}
		return entity;
	}
	
	@RequiresPermissions("xb:ruiBrowingHistory:view")
	@RequestMapping(value = {"list", ""})
	public String list(RuiBrowingHistory ruiBrowingHistory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RuiBrowingHistory> page = ruiBrowingHistoryService.findPage(new Page<RuiBrowingHistory>(request, response), ruiBrowingHistory); 
		model.addAttribute("page", page);
		return "modules/xb/ruiBrowingHistoryList";
	}

	@RequiresPermissions("xb:ruiBrowingHistory:view")
	@RequestMapping(value = "form")
	public String form(RuiBrowingHistory ruiBrowingHistory, Model model) {
		model.addAttribute("ruiBrowingHistory", ruiBrowingHistory);
		return "modules/xb/ruiBrowingHistoryForm";
	}

	@RequiresPermissions("xb:ruiBrowingHistory:edit")
	@RequestMapping(value = "save")
	public String save(RuiBrowingHistory ruiBrowingHistory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruiBrowingHistory)){
			return form(ruiBrowingHistory, model);
		}
		ruiBrowingHistoryService.save(ruiBrowingHistory);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiBrowingHistory/?repage";
	}
	
	@RequiresPermissions("xb:ruiBrowingHistory:edit")
	@RequestMapping(value = "delete")
	public String delete(RuiBrowingHistory ruiBrowingHistory, RedirectAttributes redirectAttributes) {
		ruiBrowingHistoryService.delete(ruiBrowingHistory);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiBrowingHistory/?repage";
	}

}