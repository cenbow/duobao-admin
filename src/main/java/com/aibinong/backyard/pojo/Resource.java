package com.aibinong.backyard.pojo;

import java.util.Date;
import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by lvfugang on 16/7/1.
 */
@Table("resource")
public class Resource extends BaseDO {
	@Id
	private Long id;
	@Column
	private String resource_name;
	@Column
	private String url;
	@Column
	private Long parent_id;
	@Column
	private Integer level;
	@Column
	private Date gmt_create;
	@Column
	private Date gmt_modified;

	private List<Resource> childList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}

	public String getResource_name() {
		return resource_name;
	}

	public void setResource_name(String resource_name) {
		this.resource_name = resource_name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
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

	public List<Resource> getChildList() {
		return childList;
	}

	public void setChildList(List<Resource> childList) {
		this.childList = childList;
	}

}
