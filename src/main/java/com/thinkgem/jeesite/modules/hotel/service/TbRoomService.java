/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hotel.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.hotel.entity.TbRoom;
import com.thinkgem.jeesite.modules.hotel.dao.TbRoomDao;

/**
 * 客房信息管理Service
 * 
 * @author 王豹垒
 * @version 2017-04-08
 */
@Service
@Transactional(readOnly = true)
public class TbRoomService extends CrudService<TbRoomDao, TbRoom> {

	@Autowired
	TbRoomDao tbRoomDao;

	public TbRoom get(String id) {
		return super.get(id);
	}

	public List<TbRoom> findList(TbRoom tbRoom) {
		return super.findList(tbRoom);
	}

	public Page<TbRoom> findPage(Page<TbRoom> page, TbRoom tbRoom) {
		return super.findPage(page, tbRoom);
	}

	@Transactional(readOnly = false)
	public void save(TbRoom tbRoom) {
		super.save(tbRoom);
	}

	@Transactional(readOnly = false)
	public void delete(TbRoom tbRoom) {
		super.delete(tbRoom);
	}

	// 结账操作
	public TbRoom getById(String id) {
		return this.dao.getById(id);
	}

	/* 未预定房间 */
	public TbRoom getRoom() {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<Map<String, Object>> resultMapList = tbRoomDao.getRoom(paramMap);
		TbRoom tbRoom = new TbRoom();
		tbRoom.setParamMap(paramMap);
		tbRoom.setResultMapList(resultMapList);
		return tbRoom;
	}

	// 根据房间号取信息
	public TbRoom findrooms(String number) {
		TbRoom list = tbRoomDao.findlists(number);
		return list;
	}
}