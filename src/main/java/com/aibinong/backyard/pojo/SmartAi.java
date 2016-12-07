package com.aibinong.backyard.pojo;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("smart_ai")
public class SmartAi {
	@Id
	private Long id;
	@Column
	private Long product_id;
	@Column
	private Integer hit_rate;
	@Column
	private String ai_args;
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
	public Long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}
	public Integer getHit_rate() {
		return hit_rate;
	}
	public void setHit_rate(Integer hit_rate) {
		this.hit_rate = hit_rate;
	}
	public String getAi_args() {
		return ai_args;
	}
	public void setAi_args(String ai_args) {
		this.ai_args = ai_args;
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
