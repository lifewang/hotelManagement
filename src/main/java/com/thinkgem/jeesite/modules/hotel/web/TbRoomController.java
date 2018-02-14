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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.hotel.entity.TbRoom;
import com.thinkgem.jeesite.modules.hotel.service.TbRoomService;

/**
 * 客房信息管理Controller
 * 
 * @author 王豹垒
 * @version 2017-04-08
 */
@Controller
@RequestMapping(value = "${adminPath}/hotel/tbRoom")
public class TbRoomController extends BaseController {

	@Autowired
	private TbRoomService tbRoomService;

	@ModelAttribute
	public TbRoom get(@RequestParam(required = false) String id) {
		TbRoom entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = tbRoomService.get(id);
		}
		if (entity == null) {
			entity = new TbRoom();
		}
		return entity;
	}

	@RequiresPermissions("hotel:tbRoom:view")
	@RequestMapping(value = { "list", "" })
	public String list(TbRoom tbRoom, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<TbRoom> page = tbRoomService.findPage(new Page<TbRoom>(request,
				response), tbRoom);
		model.addAttribute("page", page);
		return "modules/hotel/tbRoomList";
	}

	@RequiresPermissions("hotel:tbRoom:view")
	@RequestMapping(value = "form")
	public String form(TbRoom tbRoom, Model model) {
		model.addAttribute("tbRoom", tbRoom);
		return "modules/hotel/tbRoomForm";
	}

	@RequiresPermissions("hotel:tbRoom:edit")
	@RequestMapping(value = "save")
	public String save(TbRoom tbRoom, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tbRoom)) {
			return form(tbRoom, model);
		}
		tbRoomService.save(tbRoom);
		addMessage(redirectAttributes, "保存客房信息成功");
		return "redirect:" + Global.getAdminPath() + "/hotel/tbRoom/?repage";
	}

	@RequiresPermissions("hotel:tbRoom:edit")
	@RequestMapping(value = "delete")
	public String delete(TbRoom tbRoom, RedirectAttributes redirectAttributes) {
		tbRoomService.delete(tbRoom);
		addMessage(redirectAttributes, "删除客房信息成功");
		return "redirect:" + Global.getAdminPath() + "/hotel/tbRoom/?repage";
	}

	// 获取房间号下拉列表
	@RequestMapping(value = "selectRoomList")
	@ResponseBody
	public List<TbRoom> selectRoomList() {
		List<TbRoom> list = tbRoomService.findList(new TbRoom());
		return list;
	}

	// 自动补全房间号
	@RequestMapping(value = "selectRoomNumber")
	@ResponseBody
	List<String> getAllRoomNumber() {
		List<TbRoom> list = tbRoomService.findList(new TbRoom());
		List<String> strList = new ArrayList<String>();
		for (TbRoom room : list) {
			strList.add(room.getNumber());
		}
		return strList;
	}

	// 自动补全房间类型
	@RequestMapping(value = "selectRoomCategory")
	@ResponseBody
	List<String> getAllRoomCategory() {
		List<TbRoom> list = tbRoomService.findList(new TbRoom());
		List<String> strList = new ArrayList<String>();
		for (TbRoom room : list) {
			strList.add(room.getCategory());
		}
		return strList;
	}

	// 自动补全房间价格
	@RequestMapping(value = "selectRoomPrice")
	@ResponseBody
	List<String> getAllRoomPrice() {
		List<TbRoom> list = tbRoomService.findList(new TbRoom());
		List<String> strList = new ArrayList<String>();
		for (TbRoom room : list) {
			strList.add(room.getPrice());
		}
		return strList;
	}

	// 根据房间号查询房间信息
	@RequestMapping(value = "listrooms")
	public String listrooms(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String number = request.getParameter("number");
		TbRoom list = tbRoomService.findrooms(number);
		Page<TbRoom> page = tbRoomService.findPage(new Page<TbRoom>(request,
				response), list);
		model.addAttribute("page", page);
		return "modules/hotel/tbRoomList";
	}

	// 是否结账
	@RequiresPermissions("hotel:tbRoom:edit")
	@RequestMapping(value = "checkout")
	public String checkout(@RequestParam(value = "id") String id,
			RedirectAttributes redirectAttributes) {
		TbRoom tbRoom = tbRoomService.get(id);
		if ("是".equals(tbRoom.getState())) {
			tbRoom.setState("否");
			tbRoom.setStatus("有");
			tbRoomService.save(tbRoom);
			addMessage(redirectAttributes, "未结账");
		} else {
			tbRoom.setState("是");
			tbRoom.setStatus("无");
			tbRoomService.save(tbRoom);
			addMessage(redirectAttributes, "已结账");
		}
		return "redirect:" + Global.getAdminPath() + "/hotel/tbRoom/list";
	}

	/* 年收入 */
	@RequestMapping(value = { "room" })
	public String getRoom(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		TbRoom tbRoom = tbRoomService.getRoom();
		model.addAttribute("tbRoom", tbRoom);
		return "modules/hotel/room";
	}
	
	@RequestMapping(value = "movie")
	public String movie() {

		return "modules/sys/movie";
	}

}