package com.aibinong.backyard.pojo;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;
@Table("customer_operate_log")
public class CustomerOperateLog {
	@Id
	private Long id;
	@Column
	private Long operate_id;
	@Column
	private String mobile;
	@Column
	private Long user_id;
	@Column
	private Integer type;
	@Column
	private Long period_id;
	@Column
	private String remark;
	@Column
	private Date gmt_create;
	@Column
	private Date gmt_modified;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOperate_id() {
		return operate_id;
	}
	public void setOperate_id(Long operate_id) {
		this.operate_id = operate_id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Long getPeriod_id() {
		return period_id;
	}
	public void setPeriod_id(Long period_id) {
		this.period_id = period_id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getGmt_create() {
		return gmt_create;
	}
	public void setGmt_create(Date gmt_create) {
		this.gmt_create = gmt_create;
	}
	public Date getGmt_modified() {
		return gmt_modified;
	}
	public void setGmt_modified(Date gmt_modified) {
		this.gmt_modified = gmt_modified;
	}
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	
}
