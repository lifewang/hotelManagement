/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hotel.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.hotel.entity.TbConsumer;
import com.thinkgem.jeesite.modules.hotel.service.TbConsumerService;

/**
 * 客户入住信息Controller
 * 
 * @author wang
 * @version 2017-04-25
 */
@Controller
@RequestMapping(value = "${adminPath}/hotel/tbConsumer")
public class TbConsumerController extends BaseController {

	@Autowired
	private TbConsumerService tbConsumerService;

	@ModelAttribute
	public TbConsumer get(@RequestParam(required = false) String id) {
		TbConsumer entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = tbConsumerService.get(id);
		}
		if (entity == null) {
			entity = new TbConsumer();
		}
		return entity;
	}

	@RequiresPermissions("hotel:tbConsumer:view")
	@RequestMapping(value = { "list", "" })
	public String list(TbConsumer tbConsumer, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<TbConsumer> page = tbConsumerService.findPage(
				new Page<TbConsumer>(request, response), tbConsumer);
		model.addAttribute("page", page);
		return "modules/hotel/tbConsumerList";
	}

	@RequiresPermissions("hotel:tbConsumer:view")
	@RequestMapping(value = "form")
	public String form(TbConsumer tbConsumer, Model model) {
		model.addAttribute("tbConsumer", tbConsumer);
		return "modules/hotel/tbConsumerForm";
	}

	@RequiresPermissions("hotel:tbConsumer:edit")
	@RequestMapping(value = "save")
	public String save(TbConsumer tbConsumer, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tbConsumer)) {
			return form(tbConsumer, model);
		}
		tbConsumerService.save(tbConsumer);
		addMessage(redirectAttributes, "保存客户入住信息成功");
		return "redirect:" + Global.getAdminPath()
				+ "/hotel/tbConsumer/?repage";
	}

	@RequiresPermissions("hotel:tbConsumer:edit")
	@RequestMapping(value = "delete")
	public String delete(TbConsumer tbConsumer,
			RedirectAttributes redirectAttributes) {
		tbConsumerService.delete(tbConsumer);
		addMessage(redirectAttributes, "删除客户入住信息成功");
		return "redirect:" + Global.getAdminPath()
				+ "/hotel/tbConsumer/?repage";
	}

	/**
	 * 导出客户入住信息
	 */
	@RequiresPermissions("hotel:tbCustomer:view")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(TbConsumer tbConsumer, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "客户入住信息" + DateUtils.getDate("yyyyMMddHHmmss")
					+ ".xlsx";
			Page<TbConsumer> page = tbConsumerService.findPage(
					new Page<TbConsumer>(request, response), new TbConsumer());

			new ExportExcel("客户入住信息", TbConsumer.class)
					.setDataList(page.getList()).write(response, fileName)
					.dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath()
				+ "/hotel/tbConsumer/?repage";
	}

	// 自动补全客户姓名
	@RequestMapping(value = "selecttbConsumerName")
	@ResponseBody
	List<String> getAlltbConsumerName() {
		List<TbConsumer> list = tbConsumerService.findList(new TbConsumer());
		List<String> strList = new ArrayList<String>();
		for (TbConsumer tbConsumer : list) {
			strList.add(tbConsumer.getName());
		}
		return strList;
	}

	// 自动补全客户身份证号
	@RequestMapping(value = "selecttbConsumerCard")
	@ResponseBody
	List<String> getAlltbConsumerCard() {
		List<TbConsumer> list = tbConsumerService.findList(new TbConsumer());
		List<String> strList = new ArrayList<String>();
		for (TbConsumer tbConsumer : list) {
			strList.add(tbConsumer.getCard());
		}
		return strList;
	}

}