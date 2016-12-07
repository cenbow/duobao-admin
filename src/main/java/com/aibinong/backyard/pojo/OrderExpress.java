package com.aibinong.backyard.pojo;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("order_express")
public class OrderExpress {
	@Id
	private Long id;
	@Column
	private Long user_id;
	@Column
	private String order_id;
	@Column
	private Long product_id;
	@Column
	private Long period_id;
	@Column
	private String name;
	@Column
	private String mobile;
	@Column
	private String province;
	@Column
	private String city;
	@Column
	private String district;
	@Column
	private String address;
	@Column
	private String zip;
	@Column
	private String id_number;
	@Column
	private Integer status;
	@Column
	private String remark;
	@Column
	private Long express_id;
	@Column
	private String express_code;
	@Column
	private Date commit_address_time;
	@Column
	private Long recharge_record_id;
	@Column
	private Date gmt_create;
	@Column
	private Date gmt_modified;
	@Column
	private Date express_time;
	
	private Integer rechargeStatus;
	
	public enum Status {

        GETPRIZ(0), ADDRESS(1),DELIVERED(2),SIGN(3);
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
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getId_number() {
		return id_number;
	}
	public void setId_number(String id_number) {
		this.id_number = id_number;
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
	public Long getExpress_id() {
		return express_id;
	}
	public void setExpress_id(Long express_id) {
		this.express_id = express_id;
	}
	public String getExpress_code() {
		return express_code;
	}
	public void setExpress_code(String express_code) {
		this.express_code = express_code;
	}
	public Date getCommit_address_time() {
		return commit_address_time;
	}
	public void setCommit_address_time(Date commit_address_time) {
		this.commit_address_time = commit_address_time;
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
	public Date getExpress_time() {
		return express_time;
	}
	public void setExpress_time(Date express_time) {
		this.express_time = express_time;
	}
	public Long getRecharge_record_id() {
		return recharge_record_id;
	}
	public void setRecharge_record_id(Long recharge_record_id) {
		this.recharge_record_id = recharge_record_id;
	}
	public Integer getRechargeStatus() {
		return rechargeStatus;
	}
	public void setRechargeStatus(Integer rechargeStatus) {
		this.rechargeStatus = rechargeStatus;
	}
	
	
}
