/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hotel.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.hotel.entity.TbCustomer;

/**
 * 客户信息管理DAO接口
 * 
 * @author 王豹垒
 * @version 2017-04-16
 */
@MyBatisDao
public interface TbCustomerDao extends CrudDao<TbCustomer> {
	// 结账操作
	TbCustomer getById(String id);
}