package com.aibinong.backyard.pojo;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("award_plan")
public class AwardPlan {
	@Id
	private Long id;
	@Column
	private Long marketing_id;
	@Column
	private Long discount_id;
	@Column
	private Integer face_value;
	@Column
	private Integer interval_date;
	@Column
	private Integer validity_date;
	@Column
	private String award_data;
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
	public Long getMarketing_id() {
		return marketing_id;
	}
	public void setMarketing_id(Long marketing_id) {
		this.marketing_id = marketing_id;
	}
	public Long getDiscount_id() {
		return discount_id;
	}
	public void setDiscount_id(Long discount_id) {
		this.discount_id = discount_id;
	}
	public Integer getFace_value() {
		return face_value;
	}
	public void setFace_value(Integer face_value) {
		this.face_value = face_value;
	}
	public Integer getInterval_date() {
		return interval_date;
	}
	public void setInterval_date(Integer interval_date) {
		this.interval_date = interval_date;
	}
	public Integer getValidity_date() {
		return validity_date;
	}
	public void setValidity_date(Integer validity_date) {
		this.validity_date = validity_date;
	}
	public String getAward_data() {
		return award_data;
	}
	public void setAward_data(String award_data) {
		this.award_data = award_data;
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
