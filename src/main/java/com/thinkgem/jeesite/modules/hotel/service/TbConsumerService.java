/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hotel.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.hotel.entity.TbConsumer;
import com.thinkgem.jeesite.modules.hotel.dao.TbConsumerDao;

/**
 * 客户入住信息Service
 * @author wang
 * @version 2017-04-25
 */
@Service
@Transactional(readOnly = true)
public class TbConsumerService extends CrudService<TbConsumerDao, TbConsumer> {

	public TbConsumer get(String id) {
		return super.get(id);
	}
	
	public List<TbConsumer> findList(TbConsumer tbConsumer) {
		return super.findList(tbConsumer);
	}
	
	public Page<TbConsumer> findPage(Page<TbConsumer> page, TbConsumer tbConsumer) {
		return super.findPage(page, tbConsumer);
	}
	
	@Transactional(readOnly = false)
	public void save(TbConsumer tbConsumer) {
		super.save(tbConsumer);
	}
	
	@Transactional(readOnly = false)
	public void delete(TbConsumer tbConsumer) {
		super.delete(tbConsumer);
	}
	
}