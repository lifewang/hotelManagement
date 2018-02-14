/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hotel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.hotel.entity.TbCustomer;
import com.thinkgem.jeesite.modules.hotel.entity.TbRoom;
import com.thinkgem.jeesite.modules.hotel.dao.TbCustomerDao;

/**
 * 客户信息管理Service
 * 
 * @author 王豹垒
 * @version 2017-04-16
 */
@Service
@Transactional(readOnly = true)
public class TbCustomerService extends CrudService<TbCustomerDao, TbCustomer> {

	@Autowired
	TbCustomerDao tbCustomerDao;
	
	public TbCustomer get(String id) {
		return super.get(id);
	}

	public List<TbCustomer> findList(TbCustomer tbCustomer) {
		return super.findList(tbCustomer);
	}

	public Page<TbCustomer> findPage(Page<TbCustomer> page,
			TbCustomer tbCustomer) {
		// 生成数据权限过滤条件(dsf为dataScopeFilter的简写，在xml中使用${sqlMap.dsf}调用权限SQL)
		tbCustomer.getSqlMap().put("dsf",dataScopeFilter(tbCustomer.getCurrentUser(), "o", "u16"));
		// 设置分页参数
		tbCustomer.setPage(page);
		// 执行分页查询
		return page.setList(this.dao.findList(tbCustomer));
	}

	@Transactional(readOnly = false)
	public void save(TbCustomer tbCustomer) {
		super.save(tbCustomer);
	}

	@Transactional(readOnly = false)
	public void delete(TbCustomer tbCustomer) {
		super.delete(tbCustomer);
	}

	// 结账操作
	public TbCustomer getById(String id) {
		return this.dao.getById(id);
	}
}