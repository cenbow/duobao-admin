package com.aibinong.backyard.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("spu")
public class Spu extends BaseDO{
	@Id
	private Long id;
	@Column
	private String title;
	@Column
	private Integer deleted;
	
	public enum Deleted {

        DEL(1), NORMAL(0);
        private int v;

        private Deleted(int v) {
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	
	
	
}
