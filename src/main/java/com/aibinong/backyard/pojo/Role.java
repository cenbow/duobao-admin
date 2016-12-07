package com.aibinong.backyard.pojo;

import java.util.Date;
import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Table;
/**
 * Created by lvfugang on 16/6/30.
 */
@Table("role")
public class Role extends BaseDO{

	 	@Id
	    private Integer id;
	    @Column
	    private String role_name;
	    @Column
	    private Integer type;
	    @Column
	    private Integer status;
	    @Column
	    private Date gmt_create;
	    @Column
	    private Date gmt_modified;

	    @ManyMany(target = Resource.class, relation = "resource_role", from = "role_id", to = "resource_id")
	    List<Resource> resources;
	    

	    public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getRole_name() {
			return role_name;
		}

		public void setRole_name(String role_name) {
			this.role_name = role_name;
		}

		public Integer getType() {
	        return type;
	    }

	    public void setType(Integer type) {
	        this.type = type;
	    }

	    public Integer getStatus() {
	        return status;
	    }

	    public void setStatus(Integer status) {
	        this.status = status;
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

	public List<Resource> getResources() {
			return resources;
		}

		public void setResources(List<Resource> resources) {
			this.resources = resources;
		}
	    
}
