package com.aibinong.backyard.pojo;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("recharge_record")
public class RechargeRecord {
	@Id
	private Long id;
	@Column
	private Long user_id;
	@Column
	private Long product_id;
	@Column
	private Long period_id;
	@Column
	private String order_express_id;
	@Column
	private String mobile;
	@Column
	private Integer amount;
	@Column
	private Integer status;
	@Column
	private String remark;
	@Column
	private String recharge_order_id;
	@Column
	private String third_order_id;
	@Column
	private Integer times;
	@Column
	private Date gmt_create;
	@Column
	private Date gmt_modified;
	
	public enum Status {

        INIT(0), CREATE_ORDER(1),SUCCESS(2),CAN_RECHARGE(-1),REHARGE_FAILED(-9);
        private int v;

        private Status(int v) {
            this.v = v;
        }

        public int value() {
            return v;
        }
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}
	public Long getPeriod_id() {
		return period_id;
	}
	public void setPeriod_id(Long period_id) {
		this.period_id = period_id;
	}
	public String getOrder_express_id() {
		return order_express_id;
	}
	public void setOrder_express_id(String order_express_id) {
		this.order_express_id = order_express_id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRecharge_order_id() {
		return recharge_order_id;
	}
	public void setRecharge_order_id(String recharge_order_id) {
		this.recharge_order_id = recharge_order_id;
	}
	public String getThird_order_id() {
		return third_order_id;
	}
	public void setThird_order_id(String third_order_id) {
		this.third_order_id = third_order_id;
	}
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
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

