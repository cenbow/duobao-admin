package com.aibinong.backyard.pojo;

import java.util.Date;
import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("redpack")
public class Redpack extends BaseDO {

	@Id
	private Long id;

	@Column
	private String name;

	@Column("bg_img")
	private String bgImg;

	@Column("range_min")
	private Integer rangeMin;

	@Column("range_avg")
	private Integer rangeAvg;

	@Column("range_max")
	private Integer rangeMax;

	@Column("participate_count")
	private Integer participateCount;

	@Column("start_date")
	private String startDate;

	@Column("end_date")
	private String endDate;

	@Column("turn_conf")
	private String turnConf;

	@Column("end_minute")
	private Integer endMinute;

	@Column
	private Integer status;

	@Column("gmt_create")
	private Date gmtCreate;

	@Column("gmt_modified")
	private Date gmtModified;

	private List<TurnConf> turnList;

	public Integer getParticipateCount() {
		return participateCount;
	}

	public void setParticipateCount(Integer participateCount) {
		this.participateCount = participateCount;
	}

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

	public String getBgImg() {
		return bgImg;
	}

	public void setBgImg(String bgImg) {
		this.bgImg = bgImg;
	}

	public Integer getRangeMin() {
		return rangeMin;
	}

	public void setRangeMin(Integer rangeMin) {
		this.rangeMin = rangeMin;
	}

	public Integer getRangeMax() {
		return rangeMax;
	}

	public void setRangeMax(Integer rangeMax) {
		this.rangeMax = rangeMax;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getTurnConf() {
		return turnConf;
	}

	public void setTurnConf(String turnConf) {
		this.turnConf = turnConf;
	}

	public Integer getEndMinute() {
		return endMinute;
	}

	public void setEndMinute(Integer endMinute) {
		this.endMinute = endMinute;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Integer getRangeAvg() {
		return rangeAvg;
	}

	public void setRangeAvg(Integer rangeAvg) {
		this.rangeAvg = rangeAvg;
	}

	public List<TurnConf> getTurnList() {
		return turnList;
	}

	public void setTurnList(List<TurnConf> turnList) {
		this.turnList = turnList;
	}
}
