/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hotel.dao;

import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.hotel.entity.TbFinance;

/**
 * 日收入统计DAO接口
 * @author wang
 * @version 2017-04-27
 */
@MyBatisDao
public interface TbFinanceDao extends CrudDao<TbFinance> {
	
	/*月收入*/
	List<Map<String, Object>> getIncomeInfo(Map<String, Object> paramMap);
	
	/*年收入*/
	List<Map<String, Object>> getIncomeInfodate(Map<String, Object> paramMap);
}