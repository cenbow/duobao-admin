package com.aibinong.backyard.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;
import java.util.List;

/**
 * Created by lvfugang on 16/7/5.
 */
@Table("ads")
public class Ads extends BaseDO{
    @Id
    private Long id;
    @Column
    private String pid;
    @Column
    private String img;
    @Column
    private Integer weight;
    @Column
    private Integer message_type;
    @Column
    private Integer deleted;
    @Column
    private String target_id;
    @Column
    private String target_url;
    @Column
    private Integer target_need_login;
    @Column
    private Date gmt_create;
    @Column
    private Date gmt_modified;
    private String[] clientIds;
    private String client_id;
    @ManyMany(target = Ads.class, relation = "ad_app", from = "ad_id", to = "client_id")
    private List<AdApp> adApps;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }


    public Integer getMessage_type() {
		return message_type;
	}

	public void setMessage_type(Integer message_type) {
		this.message_type = message_type;
	}

	public String getTarget_id() {
        return target_id;
    }

    public void setTarget_id(String target_id) {
        this.target_id = target_id;
    }

    public String getTarget_url() {
        return target_url;
    }

    public void setTarget_url(String target_url) {
        this.target_url = target_url;
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


	public List<AdApp> getAdApps() {
		return adApps;
	}

	public void setAdApps(List<AdApp> adApps) {
		this.adApps = adApps;
	}

	public String[] getClientIds() {
		return clientIds;
	}

	public void setClientIds(String[] clientIds) {
		this.clientIds = clientIds;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public Integer getTarget_need_login() {
		return target_need_login;
	}

	public void setTarget_need_login(Integer target_need_login) {
		this.target_need_login = target_need_login;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
    
}
