package com.aibinong.backyard.pojo;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("marketing_activity")
public class MarketingActivity {
	@Id
	private Long id;
	@Column
	private String name;
	@Column
	private Date gmt_validity_start;
	@Column
	private Date gmt_validity_end;
	@Column
	private Integer status;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getGmt_validity_start() {
		return gmt_validity_start;
	}
	public void setGmt_validity_start(Date gmt_validity_start) {
		this.gmt_validity_start = gmt_validity_start;
	}
	public Date getGmt_validity_end() {
		return gmt_validity_end;
	}
	public void setGmt_validity_end(Date gmt_validity_end) {
		this.gmt_validity_end = gmt_validity_end;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	
	
	
}
