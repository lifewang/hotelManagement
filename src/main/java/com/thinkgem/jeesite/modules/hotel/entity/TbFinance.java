/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hotel.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 日收入统计Entity
 * @author wang
 * @version 2017-04-27
 */
public class TbFinance extends DataEntity<TbFinance> {
	
	private static final long serialVersionUID = 1L;
	private String income;		// 收入
	private Date updates;		// 更新日期
	private String remark;		// 备注
	
	private Map<String, Object> paramMap;
	private List<Map<String, Object>> resultMapList;
	
	public TbFinance() {
		super();
	}

	public TbFinance(String id){
		super(id);
	}
	
	@ExcelField(title="收入",type=0,align=2,sort=20)
	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="更新日期",type=0,align=1,sort=50)
	public Date getUpdates() {
		return updates;
	}

	public void setUpdates(Date updates) {
		this.updates = updates;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	@ExcelField(title="备注",type=0,align=1,sort=40)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	public List<Map<String, Object>> getResultMapList() {
		return resultMapList;
	}

	public void setResultMapList(List<Map<String, Object>> resultMapList) {
		this.resultMapList = resultMapList;
	}
	
	
	
}