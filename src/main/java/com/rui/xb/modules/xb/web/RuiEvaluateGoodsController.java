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
import com.rui.xb.modules.xb.entity.RuiEvaluateGoods;
import com.rui.xb.modules.xb.service.RuiEvaluateGoodsService;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2018-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/xb/ruiEvaluateGoods")
public class RuiEvaluateGoodsController extends BaseController {

	@Autowired
	private RuiEvaluateGoodsService ruiEvaluateGoodsService;
	
	@ModelAttribute
	public RuiEvaluateGoods get(@RequestParam(required=false) String id) {
		RuiEvaluateGoods entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ruiEvaluateGoodsService.get(id);
		}
		if (entity == null){
			entity = new RuiEvaluateGoods();
		}
		return entity;
	}
	
	@RequiresPermissions("xb:ruiEvaluateGoods:view")
	@RequestMapping(value = {"list", ""})
	public String list(RuiEvaluateGoods ruiEvaluateGoods, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RuiEvaluateGoods> page = ruiEvaluateGoodsService.findPage(new Page<RuiEvaluateGoods>(request, response), ruiEvaluateGoods); 
		model.addAttribute("page", page);
		return "modules/xb/ruiEvaluateGoodsList";
	}

	@RequiresPermissions("xb:ruiEvaluateGoods:view")
	@RequestMapping(value = "form")
	public String form(RuiEvaluateGoods ruiEvaluateGoods, Model model) {
		model.addAttribute("ruiEvaluateGoods", ruiEvaluateGoods);
		return "modules/xb/ruiEvaluateGoodsForm";
	}

	@RequiresPermissions("xb:ruiEvaluateGoods:edit")
	@RequestMapping(value = "save")
	public String save(RuiEvaluateGoods ruiEvaluateGoods, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruiEvaluateGoods)){
			return form(ruiEvaluateGoods, model);
		}
		ruiEvaluateGoodsService.save(ruiEvaluateGoods);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiEvaluateGoods/?repage";
	}
	
	@RequiresPermissions("xb:ruiEvaluateGoods:edit")
	@RequestMapping(value = "delete")
	public String delete(RuiEvaluateGoods ruiEvaluateGoods, RedirectAttributes redirectAttributes) {
		ruiEvaluateGoodsService.delete(ruiEvaluateGoods);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiEvaluateGoods/?repage";
	}

}