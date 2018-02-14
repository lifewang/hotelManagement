/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hotel.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.hotel.entity.TbConsumer;

/**
 * 客户入住信息DAO接口
 * @author wang
 * @version 2017-04-25
 */
@MyBatisDao
public interface TbConsumerDao extends CrudDao<TbConsumer> {
	
}