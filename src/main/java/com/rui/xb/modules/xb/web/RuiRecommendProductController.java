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
import com.rui.xb.modules.xb.entity.RuiRecommendProduct;
import com.rui.xb.modules.xb.service.RuiRecommendProductService;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2018-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/xb/ruiRecommendProduct")
public class RuiRecommendProductController extends BaseController {

	@Autowired
	private RuiRecommendProductService ruiRecommendProductService;
	
	@ModelAttribute
	public RuiRecommendProduct get(@RequestParam(required=false) String id) {
		RuiRecommendProduct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ruiRecommendProductService.get(id);
		}
		if (entity == null){
			entity = new RuiRecommendProduct();
		}
		return entity;
	}
	
	@RequiresPermissions("xb:ruiRecommendProduct:view")
	@RequestMapping(value = {"list", ""})
	public String list(RuiRecommendProduct ruiRecommendProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RuiRecommendProduct> page = ruiRecommendProductService.findPage(new Page<RuiRecommendProduct>(request, response), ruiRecommendProduct); 
		model.addAttribute("page", page);
		return "modules/xb/ruiRecommendProductList";
	}

	@RequiresPermissions("xb:ruiRecommendProduct:view")
	@RequestMapping(value = "form")
	public String form(RuiRecommendProduct ruiRecommendProduct, Model model) {
		model.addAttribute("ruiRecommendProduct", ruiRecommendProduct);
		return "modules/xb/ruiRecommendProductForm";
	}

	@RequiresPermissions("xb:ruiRecommendProduct:edit")
	@RequestMapping(value = "save")
	public String save(RuiRecommendProduct ruiRecommendProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruiRecommendProduct)){
			return form(ruiRecommendProduct, model);
		}
		ruiRecommendProductService.save(ruiRecommendProduct);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiRecommendProduct/?repage";
	}
	
	@RequiresPermissions("xb:ruiRecommendProduct:edit")
	@RequestMapping(value = "delete")
	public String delete(RuiRecommendProduct ruiRecommendProduct, RedirectAttributes redirectAttributes) {
		ruiRecommendProductService.delete(ruiRecommendProduct);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/xb/ruiRecommendProduct/?repage";
	}

}