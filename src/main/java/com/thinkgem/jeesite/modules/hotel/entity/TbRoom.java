/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hotel.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 客房信息管理Entity
 * @author 王豹垒
 * @version 2017-04-08
 */
public class TbRoom extends DataEntity<TbRoom> {
	
	private static final long serialVersionUID = 1L;
	private String category;		// 客房类型
	private String status;		// 客房状态
	private String number;		// 房间号
	private String price;		// 价格
	private String state;       //是否结账
	private String facilitie;   //客房设施
	private String remark;		// 备注
	private String reserve; //预定状态
	private TbCustomer customer; 
	
	private Map<String, Object> paramMap;
	private List<Map<String, Object>> resultMapList;
	
	public TbRoom() {
		super();
	}

	public TbRoom(String id){
		super(id);
	}

	@Length(min=0, max=10, message="客房类型长度必须介于 0 和 10 之间")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	@Length(min=0, max=10, message="客房状态长度必须介于 0 和 10 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=100, message="备注长度必须介于 0 和 100 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Length(min=0, max=20, message="房间号长度必须介于 0 和 20 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getFacilitie() {
		return facilitie;
	}

	public void setFacilitie(String facilitie) {
		this.facilitie = facilitie;
	}

	
	public String getReserve() {
		return reserve;
	}

	public void setReserve(String reserve) {
		this.reserve = reserve;
	}

	public TbCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(TbCustomer customer) {
		this.customer = customer;
	}

	
	
}