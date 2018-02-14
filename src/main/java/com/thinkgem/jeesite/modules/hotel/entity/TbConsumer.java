/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hotel.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 客户入住信息Entity
 * @author wang
 * @version 2017-04-25
 */
public class TbConsumer extends DataEntity<TbConsumer> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 姓名
	private String phone;		// 手机号
	private String card;		// 身份证号
	private String email;		// Email
	private Date intime;		// 入住时间
	private Date outtime;		// 离店时间
	private String room_id;		// 房间号
	private String remark;		// 备注
	private TbRoom room;
	
	public TbConsumer() {
		super();
	}

	public TbConsumer(String id){
		super(id);
	}

	@Length(min=0, max=50, message="姓名长度必须介于 0 和 50 之间")
	@ExcelField(title="姓名",type=0,align=2,sort=20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=50, message="手机号长度必须介于 0 和 50 之间")
	@ExcelField(title="手机号",type=0,align=1,sort=40)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=50, message="身份证号长度必须介于 0 和 50 之间")
	@ExcelField(title="身份证号",type=0,align=1,sort=40)
	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}
	
	@Length(min=0, max=50, message="Email长度必须介于 0 和 50 之间")
	@ExcelField(title="Email",type=0,align=1,sort=40)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="入住时间",type=0,align=1,sort=50)
	public Date getIntime() {
		return intime;
	}

	public void setIntime(Date intime) {
		this.intime = intime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="离店时间",type=0,align=1,sort=50)
	public Date getOuttime() {
		return outtime;
	}

	public void setOuttime(Date outtime) {
		this.outtime = outtime;
	}
	
	@Length(min=0, max=50, message="房间号长度必须介于 0 和 50 之间")
	public String getRoom_id() {
		return room_id;
	}

	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}

	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	@ExcelField(title="备注",type=0,align=1,sort=40)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public TbRoom getRoom() {
		return room;
	}

	public void setRoom(TbRoom room) {
		this.room = room;
	}
	
	
}