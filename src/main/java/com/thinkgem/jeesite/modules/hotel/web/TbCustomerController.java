/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hotel.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.hotel.entity.TbCustomer;
import com.thinkgem.jeesite.modules.hotel.entity.TbRoom;
import com.thinkgem.jeesite.modules.hotel.service.TbCustomerService;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 客户信息管理Controller
 * 
 * @author 王豹垒
 * @version 2017-04-16
 */
@Controller
@RequestMapping(value = "${adminPath}/hotel/tbCustomer")
public class TbCustomerController extends BaseController {

	@Autowired
	private TbCustomerService tbCustomerService;
	@Autowired
	private SystemService systemService;

	@ModelAttribute
	public TbCustomer get(@RequestParam(required = false) String id) {
		TbCustomer entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = tbCustomerService.get(id);
		}
		if (entity == null) {
			entity = new TbCustomer();
		}
		return entity;
	}

	@RequiresPermissions("hotel:tbCustomer:view")
	@RequestMapping(value = { "list", "" })
	public String list(TbCustomer tbCustomer, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<TbCustomer> page = tbCustomerService.findPage(
				new Page<TbCustomer>(request, response), tbCustomer);
		model.addAttribute("page", page);
		return "modules/hotel/tbCustomerList";
	}

	@RequiresPermissions("hotel:tbCustomer:view")
	@RequestMapping(value = "form")
	public String form(TbCustomer tbCustomer, Model model) {
		model.addAttribute("tbCustomer", tbCustomer);
		return "modules/hotel/tbCustomerForm";
	}

	@RequiresPermissions("hotel:tbCustomer:edit")
	@RequestMapping(value = "save")
	public String save(TbCustomer tbCustomer, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tbCustomer)) {
			return form(tbCustomer, model);
		}
		tbCustomerService.save(tbCustomer);
		addMessage(redirectAttributes, "保存客户信息成功");
		return "redirect:" + Global.getAdminPath()
				+ "/hotel/tbCustomer/?repage";
	}

	@RequiresPermissions("hotel:tbCustomer:edit")
	@RequestMapping(value = "delete")
	public String delete(TbCustomer tbCustomer,
			RedirectAttributes redirectAttributes) {
		tbCustomerService.delete(tbCustomer);
		addMessage(redirectAttributes, "删除客户信息成功");
		return "redirect:" + Global.getAdminPath()
				+ "/hotel/tbCustomer/?repage";
	}

	// 是否预定
	@RequiresPermissions("hotel:tbCustomer:edit")
	@RequestMapping(value = "checkout")
	public String checkout(@RequestParam(value = "id") String id,RedirectAttributes redirectAttributes) {
		TbCustomer tbCustomer = tbCustomerService.get(id);
		if ("未预订".equals(tbCustomer.getState())) {
			tbCustomer.setState("已预订");
			tbCustomerService.save(tbCustomer);
			addMessage(redirectAttributes, "未预订");
		} else {
			tbCustomer.setState("未预订");
			tbCustomerService.save(tbCustomer);
			addMessage(redirectAttributes, "已预订");
		}
		return "redirect:" + Global.getAdminPath() + "/hotel/tbCustomer/list";
	}
	
	//自动补全客户姓名
	@RequestMapping(value="selectCustomerName")
	@ResponseBody
	List<String> getAllCustomerName(){
		List<TbCustomer> list=tbCustomerService.findList(new TbCustomer());
		List<String> strlist=new ArrayList<String>();
		for(TbCustomer customer:list){
			strlist.add(customer.getName());
		}
		return strlist;
	}
	//自动补全客户证件号
	@RequestMapping(value="selectCustomerCard")
	@ResponseBody
	List<String> getAllCustomerCard(){
		List<TbCustomer> list=tbCustomerService.findList(new TbCustomer());
		List<String> strlist=new ArrayList<String>();
		for(TbCustomer customer:list){
			strlist.add(customer.getCard());
		}
		return strlist;
	}
	
	/**
	 * 导出客户入住信息
	 */
	@RequiresPermissions("hotel:tbCustomer:view")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(TbCustomer customer, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "客户入住信息" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			Page<TbCustomer> page = tbCustomerService.findPage(new Page<TbCustomer>(request, response), new TbCustomer()); 

			new ExportExcel("客户入住信息", TbCustomer.class).setDataList(page.getList()).write(response, fileName)
					.dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/hotel/tbCustomer/?repage";
	}

}