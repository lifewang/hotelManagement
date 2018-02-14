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
import com.thinkgem.jeesite.modules.hotel.entity.TbFinance;
import com.thinkgem.jeesite.modules.hotel.dao.TbFinanceDao;

/**
 * 日收入统计Service
 * @author wang
 * @version 2017-04-27
 */
@Service
@Transactional(readOnly = true)
public class TbFinanceService extends CrudService<TbFinanceDao, TbFinance> {

	
	@Autowired
	private TbFinanceDao tbFinanceDao;
	
	public TbFinance get(String id) {
		return super.get(id);
	}
	
	public List<TbFinance> findList(TbFinance tbFinance) {
		return super.findList(tbFinance);
	}
	
	public Page<TbFinance> findPage(Page<TbFinance> page, TbFinance tbFinance) {
		return super.findPage(page, tbFinance);
	}
	
	@Transactional(readOnly = false)
	public void save(TbFinance tbFinance) {
		super.save(tbFinance);
	}
	
	@Transactional(readOnly = false)
	public void delete(TbFinance tbFinance) {
		super.delete(tbFinance);
	}
	
	/*月收入*/
	public TbFinance getfinanceInfo() {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<Map<String, Object>> resultMapList = tbFinanceDao.getIncomeInfo(paramMap);
		TbFinance tbFinance = new TbFinance();
		tbFinance.setParamMap(paramMap);
		tbFinance.setResultMapList(resultMapList);
		return tbFinance;
	}
	
	/*年收入*/
	public TbFinance getfinanceInfodate() {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<Map<String, Object>> resultMapList = tbFinanceDao.getIncomeInfodate(paramMap);
		TbFinance tbFinance = new TbFinance();
		tbFinance.setParamMap(paramMap);
		tbFinance.setResultMapList(resultMapList);
		return tbFinance;
	}
	
}