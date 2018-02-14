/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hotel.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 客户信息管理Entity
 * @author 王豹垒
 * @version 2017-04-16
 */
public class TbCustomer extends DataEntity<TbCustomer> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 姓名
	private String sex;		// 性别
	private Date datebirth;		// 出生年月
	private String phone;		// 电话
	private String card;		// 身份证号
	private String remark;		// 备注
	private String cardtype;		// 证件类型
	private String country;		// 国籍
	private String email;		// Email
	private String company;		// 公司
	private String address;		// 地址
	private Date intime;		// 住店时间
	private Date outtime;		// 离店时间
	private String regionCode;		// 地区编码
	private String love;     //房间偏好
	private String roomId;		// 客房价格
	private User user;		// 用户id
	private Area area;
	private TbRoom room;
	private String state;       //是否预定
	
	public TbCustomer() {
		super();
	}

	public TbCustomer(String id){
		super(id);
	}

	@Length(min=0, max=50, message="姓名长度必须介于 0 和 50 之间")
	@ExcelField(title="入住人",type=0,align=2,sort=20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=10, message="性别长度必须介于 0 和 10 之间")
	@ExcelField(title="性别",type=0,align=2,sort=25,dictType="sex")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDatebirth() {
		return datebirth;
	}

	public void setDatebirth(Date datebirth) {
		this.datebirth = datebirth;
	}
	
	@Length(min=0, max=50, message="电话长度必须介于 0 和 50 之间")
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
	
	@Length(min=0, max=100, message="备注长度必须介于 0 和 100 之间")
	@ExcelField(title="备注",type=0,align=1,sort=40)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Length(min=0, max=50, message="证件类型长度必须介于 0 和 50 之间")
	public String getCardtype() {
		return cardtype;
	}

	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}
	
	@Length(min=0, max=50, message="国籍长度必须介于 0 和 50 之间")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	@ExcelField(title="房间偏好",type=0,align=1,sort=40)
	public String getLove() {
		return love;
	}

	public void setLove(String love) {
		this.love = love;
	}

	@Length(min=0, max=50, message="Email长度必须介于 0 和 50 之间")
	@ExcelField(title="Email",type=0,align=1,sort=40)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=255, message="公司长度必须介于 0 和 255 之间")
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	@Length(min=0, max=255, message="地址长度必须介于 0 和 255 之间")
	@ExcelField(title="地址",type=0,align=1,sort=40)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="住店时间",type=0,align=1,sort=50)
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
	
	@Length(min=0, max=64, message="地区编码长度必须介于 0 和 64 之间")
	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=50, message="客房价格长度必须介于 0 和 50 之间")
	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public TbRoom getRoom() {
		return room;
	}

	public void setRoom(TbRoom room) {
		this.room = room;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	
}