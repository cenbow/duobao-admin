package com.aibinong.backyard.pojo;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("redpack_turn")
public class RedpackTurn {

	@Id
	private Long id;

	@Column("redpack_id")
	private Long redpackId;

	@Column("turn_time")
	private String turnTime;

	@Column("end_time")
	private String endTime;

	@Column("amount_list")
	private String amountList;

	@Column("gmt_create")
	private Date gmtCreate;

	@Column("gmt_modified")
	private Date gmtModified;

	private String timeRange; // 轮次时间范围
	private Integer maxAmount; // 最大金额
	private Integer minAmount; // 最小金额
	private Double avgAmount; // 平均金额
	private Integer totalAmount; // 总金额
	private Integer totalCount; // 总个数
	private Integer remainCount; // 剩余个数

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRedpackId() {
		return redpackId;
	}

	public void setRedpackId(Long redpackId) {
		this.redpackId = redpackId;
	}

	public String getTurnTime() {
		return turnTime;
	}

	public void setTurnTime(String turnTime) {
		this.turnTime = turnTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getAmountList() {
		return amountList;
	}

	public void setAmountList(String amountList) {
		this.amountList = amountList;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Integer getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(Integer maxAmount) {
		this.maxAmount = maxAmount;
	}

	public Integer getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(Integer minAmount) {
		this.minAmount = minAmount;
	}

	public Double getAvgAmount() {
		return avgAmount;
	}

	public void setAvgAmount(Double avgAmount) {
		this.avgAmount = avgAmount;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getRemainCount() {
		return remainCount;
	}

	public void setRemainCount(Integer remainCount) {
		this.remainCount = remainCount;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public String getTimeRange() {
		return timeRange;
	}

	public void setTimeRange(String timeRange) {
		this.timeRange = timeRange;
	}
}
