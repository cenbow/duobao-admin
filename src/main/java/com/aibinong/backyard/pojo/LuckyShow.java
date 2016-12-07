package com.aibinong.backyard.pojo;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;
@Table("lucky_show")
public class LuckyShow {
	@Id
	private Long id;
	@Column
	private Long product_id;
	@Column
	private Long period_id;
	@Column
	private Long spu_id;
	@Column
	private Long user_id;
	@Column
	private String  title;
	@Column
	private String content;
	@Column
	private String imgs;
	@Column
	private Integer good;
	@Column
	private Integer recommend;
	@Column
	private Integer audit_status;
	@Column
	private Integer weight;
	@Column
	private Date gmt_create;
	@Column
	private Date gmt_modified;
	private String[] imglist;
	/**
	 * 是否精华
	 * @author lvfugang
	 *
	 */
	 public enum Good {

	    	NOT(0), GOOD(1);
	        private int v;

	        private Good(int v) {
	            this.v = v;
	        }

	        public int value() {
	            return v;
	        }
	    }
	/**
	 * 是否推荐
	 * @author lvfugang
	 *
	 */
	 public enum Recommend {

	    	NOTRECOMMEND(0), YESRECOMMEND(1);
	        private int v;

	        private Recommend(int v) {
	            this.v = v;
	        }

	        public int value() {
	            return v;
	        }
	    }
	/**
	 * 审核状态	
	 * @author lvfugang
	 *
	 */
	 public enum AuditStatus {

	    	NOTPASS(0), PASS(1);
	        private int v;

	        private AuditStatus(int v) {
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
	public Long getSpu_id() {
		return spu_id;
	}
	public void setSpu_id(Long spu_id) {
		this.spu_id = spu_id;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
		if(StringUtils.isNotEmpty(imgs)){
			this.imglist=imgs.split(",");
		}
		
	}
	public Integer getGood() {
		return good;
	}
	public void setGood(Integer good) {
		this.good = good;
	}
	public Integer getRecommend() {
		return recommend;
	}
	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}
	public Integer getAudit_status() {
		return audit_status;
	}
	public void setAudit_status(Integer audit_status) {
		this.audit_status = audit_status;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
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
	public String[] getImglist() {
		return imglist;
	}
	
	
}
