package com.aibinong.backyard.pojo;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("app_download")
public class AppDownload {

	public static final int DELETE = 1;
	public static final int NOT_DELETE = 0;

	@Id
	private Long id;
	@Column
	private String client_id;
	@Column
	private String os;
	@Column
	private String channel;
	@Column
	private String version_code;
	@Column
	private String version;
	@Column
	private String url;
	@Column
	private Integer status;
	@Column
	private String reason;
	@Column
	private Integer deleted;
	@Column
	private Date gmt_create;
	@Column
	private Date gmt_modified;

	private App app;

	public enum Status {
		INIT(1), // 初始化
		WAIT(2), // 待打包
		PENDING(3), // 审核中
		PASSED(4), // 审核通过
		NOT_PASS(5), // 审核拒绝
		ONLINE(6), // 上线（上架）
		OFFLINE(7), // 下线（下架）
		PACKAGED(8); // 已打包
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

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getVersion_code() {
		return version_code;
	}

	public void setVersion_code(String version_code) {
		this.version_code = version_code;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
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

	public App getApp() {
		return app;
	}

	public void setApp(App app) {
		this.app = app;
	}
}
