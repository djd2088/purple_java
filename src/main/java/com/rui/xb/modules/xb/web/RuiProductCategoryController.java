/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rui.xb.modules.xb.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rui.xb.common.config.Global;
import com.rui.xb.common.persistence.Page;
import com.rui.xb.common.web.BaseController;
import com.rui.xb.common.utils.StringUtils;
import com.rui.xb.modules.xb.entity.RuiProductCategory;
import com.rui.xb.modules.xb.service.RuiProductCategoryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2018-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/xb/ruiProductCategory")
public class RuiProductCategoryController extends BaseController {

	@Autowired
	private RuiProductCategoryService ruiProductCategoryService;
	
	@ModelAttribute
	public RuiProductCategory get(@RequestParam(required=false) String id) {
		RuiProductCategory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ruiProductCategoryService.get(id);
		}
		if (entity == null){
			entity = new RuiProductCategory();
		}
		return entity;
	}
	
	@RequiresPermissions("xb:ruiProductCategory:view")
	@RequestMapping(value = {"list", ""})
	public String list(RuiProductCategory ruiProductCategory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RuiProductCategory> page = ruiProductCategoryService.findPage(new Page<RuiProductCategory>(request, response), ruiProductCategory); 
		model.addAttribute("page", page);
		return "modules/xb/ruiProductCategoryList";
	}

	@RequiresPermissions("xb:ruiProductCategory:view")
	@RequestMapping(value = "form")
	public String form(RuiProductCategory ruiProductCategory, Model model) {
		model.addAttribute("ruiProductCategory", ruiProductCategory);
		return "modules/xb/ruiProductCategoryForm";
	}

	@RequiresPermissions("xb:ruiProductCategory:edit")
	@RequestMapping(value = "save")
	public String save(RuiProductCategory ruiProductCategory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruiProductCategory)){
			return form(ruiProductCategory, model);
		}
		ruiProductCategoryService.save(ruiProductCategory);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiProductCategory/?repage";
	}
	
	@RequiresPermissions("xb:ruiProductCategory:edit")
	@RequestMapping(value = "delete")
	public String delete(RuiProductCategory ruiProductCategory, RedirectAttributes redirectAttributes) {
		ruiProductCategoryService.delete(ruiProductCategory);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiProductCategory/?repage";
	}


	@RequestMapping(value = "getParentByLevel")
    @ResponseBody
	public String getParentByLevel(String level){
		List<RuiProductCategory> list = ruiProductCategoryService.findParentByCategoryLevel(level);
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("items",list);
		return new Gson().toJson(data);
	}

}