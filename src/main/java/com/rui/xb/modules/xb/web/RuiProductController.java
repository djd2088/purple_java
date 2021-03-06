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
import com.rui.xb.modules.xb.entity.RuiProduct;
import com.rui.xb.modules.xb.service.RuiProductService;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2018-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/xb/ruiProduct")
public class RuiProductController extends BaseController {

	@Autowired
	private RuiProductService ruiProductService;
	
	@ModelAttribute
	public RuiProduct get(@RequestParam(required=false) String id) {
		RuiProduct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ruiProductService.get(id);
		}
		if (entity == null){
			entity = new RuiProduct();
		}
		return entity;
	}
	
	@RequiresPermissions("xb:ruiProduct:view")
	@RequestMapping(value = {"list", ""})
	public String list(RuiProduct ruiProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RuiProduct> page = ruiProductService.findPage(new Page<RuiProduct>(request, response), ruiProduct); 
		model.addAttribute("page", page);
		return "modules/xb/ruiProductList";
	}

	@RequiresPermissions("xb:ruiProduct:view")
	@RequestMapping(value = "form")
	public String form(RuiProduct ruiProduct, Model model) {
		model.addAttribute("ruiProduct", ruiProduct);
		return "modules/xb/ruiProductForm";
	}

	@RequiresPermissions("xb:ruiProduct:edit")
	@RequestMapping(value = "save")
	public String save(RuiProduct ruiProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruiProduct)){
			return form(ruiProduct, model);
		}
		ruiProductService.save(ruiProduct);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiProduct/?repage";
	}
	
	@RequiresPermissions("xb:ruiProduct:edit")
	@RequestMapping(value = "delete")
	public String delete(RuiProduct ruiProduct, RedirectAttributes redirectAttributes) {
		ruiProductService.delete(ruiProduct);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiProduct/?repage";
	}

}