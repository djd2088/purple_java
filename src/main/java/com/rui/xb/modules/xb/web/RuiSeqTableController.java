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
import com.rui.xb.modules.xb.entity.RuiSeqTable;
import com.rui.xb.modules.xb.service.RuiSeqTableService;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2018-07-02
 */
@Controller
@RequestMapping(value = "${adminPath}/xb/ruiSeqTable")
public class RuiSeqTableController extends BaseController {

	@Autowired
	private RuiSeqTableService ruiSeqTableService;
	
	@ModelAttribute
	public RuiSeqTable get(@RequestParam(required=false) String id) {
		RuiSeqTable entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ruiSeqTableService.get(id);
		}
		if (entity == null){
			entity = new RuiSeqTable();
		}
		return entity;
	}
	
	@RequiresPermissions("xb:ruiSeqTable:view")
	@RequestMapping(value = {"list", ""})
	public String list(RuiSeqTable ruiSeqTable, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RuiSeqTable> page = ruiSeqTableService.findPage(new Page<RuiSeqTable>(request, response), ruiSeqTable); 
		model.addAttribute("page", page);
		return "modules/xb/ruiSeqTableList";
	}

	@RequiresPermissions("xb:ruiSeqTable:view")
	@RequestMapping(value = "form")
	public String form(RuiSeqTable ruiSeqTable, Model model) {
		model.addAttribute("ruiSeqTable", ruiSeqTable);
		return "modules/xb/ruiSeqTableForm";
	}

	@RequiresPermissions("xb:ruiSeqTable:edit")
	@RequestMapping(value = "save")
	public String save(RuiSeqTable ruiSeqTable, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruiSeqTable)){
			return form(ruiSeqTable, model);
		}
		ruiSeqTableService.save(ruiSeqTable);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiSeqTable/?repage";
	}
	
	@RequiresPermissions("xb:ruiSeqTable:edit")
	@RequestMapping(value = "delete")
	public String delete(RuiSeqTable ruiSeqTable, RedirectAttributes redirectAttributes) {
		ruiSeqTableService.delete(ruiSeqTable);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiSeqTable/?repage";
	}

}