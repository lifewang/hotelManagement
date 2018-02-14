/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hotel.web;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.hotel.entity.TbConsumer;
import com.thinkgem.jeesite.modules.hotel.entity.TbFinance;
import com.thinkgem.jeesite.modules.hotel.service.TbFinanceService;

/**
 * 日收入统计Controller
 * @author wang
 * @version 2017-04-27
 */
@Controller
@RequestMapping(value = "${adminPath}/hotel/tbFinance")
public class TbFinanceController extends BaseController {

	@Autowired
	private TbFinanceService tbFinanceService;
	
	@ModelAttribute
	public TbFinance get(@RequestParam(required=false) String id) {
		TbFinance entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbFinanceService.get(id);
		}
		if (entity == null){
			entity = new TbFinance();
		}
		return entity;
	}
	
	@RequiresPermissions("hotel:tbFinance:view")
	@RequestMapping(value = {"list", ""})
	public String list(TbFinance tbFinance, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TbFinance> page = tbFinanceService.findPage(new Page<TbFinance>(request, response), tbFinance); 
		model.addAttribute("page", page);
		return "modules/hotel/tbFinanceList";
	}

	@RequiresPermissions("hotel:tbFinance:view")
	@RequestMapping(value = "form")
	public String form(TbFinance tbFinance, Model model) {
		model.addAttribute("tbFinance", tbFinance);
		return "modules/hotel/tbFinanceForm";
	}

	@RequiresPermissions("hotel:tbFinance:edit")
	@RequestMapping(value = "save")
	public String save(TbFinance tbFinance, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tbFinance)){
			return form(tbFinance, model);
		}
		tbFinanceService.save(tbFinance);
		addMessage(redirectAttributes, "保存日收入信息统计成功");
		return "redirect:"+Global.getAdminPath()+"/hotel/tbFinance/?repage";
	}
	
	@RequiresPermissions("hotel:tbFinance:edit")
	@RequestMapping(value = "delete")
	public String delete(TbFinance tbFinance, RedirectAttributes redirectAttributes) {
		tbFinanceService.delete(tbFinance);
		addMessage(redirectAttributes, "删除日收入信息统计成功");
		return "redirect:"+Global.getAdminPath()+"/hotel/tbFinance/?repage";
	}
	
	/*月收入*/
	@RequestMapping(value = { "financeInfo" })
	public String priceInfo(HttpServletRequest request, HttpServletResponse response, Model model){
		TbFinance tbFinance = tbFinanceService.getfinanceInfo();
		model.addAttribute("tbFinance", tbFinance);
		return "modules/hotel/monthprice";
	}
	
	/*年收入*/
	@RequestMapping(value = { "financeInfodate" })
	public String priceInfodate(HttpServletRequest request, HttpServletResponse response, Model model){
		TbFinance tbFinance = tbFinanceService.getfinanceInfodate();
		model.addAttribute("tbFinance", tbFinance);
		return "modules/hotel/yearprice";
	}
	
	/**
	 * 导出财务报表信息
	 */
	@RequiresPermissions("hotel:tbFinance:view")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(TbFinance tbFinance, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "财务报表" + DateUtils.getDate("yyyyMMddHHmmss")+ ".xlsx";
			Page<TbFinance> page = tbFinanceService.findPage(new Page<TbFinance>(request, response), new TbFinance());
			new ExportExcel("财务报表", TbFinance.class).setDataList(page.getList()).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath()+ "/hotel/tbFinance/?repage";
	}

}