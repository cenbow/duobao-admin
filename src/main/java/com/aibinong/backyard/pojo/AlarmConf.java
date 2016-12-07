package com.aibinong.backyard.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("alarm_conf")
public class AlarmConf {
	@Id
	private Long id;
	@Column
	private Integer type;
	@Column
	private String name;
	@Column
	private String conf;
	@Column
	private String alarm_list;
	@Column
	private Integer onoff;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConf() {
		return conf;
	}

	public void setConf(String conf) {
		this.conf = conf;
	}

	public String getAlarm_list() {
		return alarm_list;
	}

	public void setAlarm_list(String alarm_list) {
		this.alarm_list = alarm_list;
	}

	public Integer getOnoff() {
		return onoff;
	}

	public void setOnoff(Integer onoff) {
		this.onoff = onoff;
	}
}
