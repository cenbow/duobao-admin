package com.aibinong.backyard.pojo;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("ladder_discount")
public class LadderDiscount {
	@Id
	private Long id;
	@Column
	private Long marketing_id;
	@Column
	private Integer limit_amount;
	@Column
	private Integer reduce_amount;
	@Column
	private Double discount;
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

	public Integer getLimit_amount() {
		return limit_amount;
	}

	public void setLimit_amount(Integer limit_amount) {
		this.limit_amount = limit_amount;
	}

	public Integer getReduce_amount() {
		return reduce_amount;
	}

	public void setReduce_amount(Integer reduce_amount) {
		this.reduce_amount = reduce_amount;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
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
