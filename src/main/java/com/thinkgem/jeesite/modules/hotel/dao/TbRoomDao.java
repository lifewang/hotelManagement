/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hotel.dao;


import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.hotel.entity.TbRoom;

/**
 * 客房信息管理DAO接口
 * @author 王豹垒
 * @version 2017-04-08
 */
@MyBatisDao
public interface TbRoomDao extends CrudDao<TbRoom> {

	//结账操作
	TbRoom getById(String id);
	/*未预定房间*/
	List<Map<String, Object>> getRoom(Map<String, Object> paramMap);
	
	//根据房间号查询房间信息
	TbRoom findlists(String number);
}