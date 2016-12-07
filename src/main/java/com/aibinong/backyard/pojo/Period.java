package com.aibinong.backyard.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * Created by ouwa on 16/6/29.
 */
@Table("period")
public class Period extends BaseDO{
    @Id
    private Long id;
    @Column
    private Long spu_id;
    @Column
    private Long product_id;
    @Column
    private Integer type;
    @Column
    private String title;
    @Column
    private String sub_title;
    @Column
    private String list_img;
    @Column
    private String loop_img;
    @Column
    private String remark;
    @Column
    private Integer real_cost;
    @Column
    private Integer numbers;
    @Column
    private Integer is_new;
    @Column
    private Integer is_hot;
    @Column
    private Integer total_count;
    @Column
    private Integer default_count;
    @Column
    private Integer limit_count;
    @Column
    private Integer duobao_type;
    @Column
    private Integer weight;
    @Column
    private Date start_time;
    @Column
    private Date close_time;
    @Column
    private Date award_time;
    @Column
    private Integer status;
    @Column
    private Integer cur_count;
    @Column
    private String lucky_code;
    @Column
    private Long lucky_user_id;
    @Column
    private Integer lucky_buy_count;
    @Column
    private String lucky_order_id;
    @Column
    private Integer ai;
    @Column
    private Date gmt_create;
    @Column
    private Date gmt_modified;

    public enum Status {

    	UNDER_WAY(0), FULL_MARK(1),OPEN_LOTTERY(2),ANNOUNCED(3);
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

    public Long getSpu_id() {
        return spu_id;
    }

    public void setSpu_id(Long spu_id) {
        this.spu_id = spu_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public String getList_img() {
        return list_img;
    }

    public void setList_img(String list_img) {
        this.list_img = list_img;
    }

    public String getLoop_img() {
        return loop_img;
    }

    public void setLoop_img(String loop_img) {
        this.loop_img = loop_img;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getReal_cost() {
        return real_cost;
    }

    public void setReal_cost(Integer real_cost) {
        this.real_cost = real_cost;
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }

    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
    }

    public Integer getDefault_count() {
        return default_count;
    }

    public void setDefault_count(Integer default_count) {
        this.default_count = default_count;
    }

    public Integer getLimit_count() {
        return limit_count;
    }

    public void setLimit_count(Integer limit_count) {
        this.limit_count = limit_count;
    }

    public Integer getDuobao_type() {
        return duobao_type;
    }

    public void setDuobao_type(Integer duobao_type) {
        this.duobao_type = duobao_type;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getClose_time() {
        return close_time;
    }

    public void setClose_time(Date close_time) {
        this.close_time = close_time;
    }

    public Date getAward_time() {
        return award_time;
    }

    public void setAward_time(Date award_time) {
        this.award_time = award_time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCur_count() {
        return cur_count;
    }

    public void setCur_count(Integer cur_count) {
        this.cur_count = cur_count;
    }

    public String getLucky_code() {
        return lucky_code;
    }

    public void setLucky_code(String lucky_code) {
        this.lucky_code = lucky_code;
    }

    public Long getLucky_user_id() {
        return lucky_user_id;
    }

    public void setLucky_user_id(Long lucky_user_id) {
        this.lucky_user_id = lucky_user_id;
    }

    public Integer getAi() {
        return ai;
    }

    public void setAi(Integer ai) {
        this.ai = ai;
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

	public Integer getLucky_buy_count() {
		return lucky_buy_count;
	}

	public void setLucky_buy_count(Integer lucky_buy_count) {
		this.lucky_buy_count = lucky_buy_count;
	}

	public String getLucky_order_id() {
		return lucky_order_id;
	}

	public void setLucky_order_id(String lucky_order_id) {
		this.lucky_order_id = lucky_order_id;
	}

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public Integer getIs_new() {
		return is_new;
	}

	public void setIs_new(Integer is_new) {
		this.is_new = is_new;
	}

	public Integer getIs_hot() {
		return is_hot;
	}

	public void setIs_hot(Integer is_hot) {
		this.is_hot = is_hot;
	}
    
}
