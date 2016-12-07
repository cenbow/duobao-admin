package com.aibinong.backyard.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * Created by ouwa on 16/6/29.
 */
@Table("user_duobao_record")
public class UserDuobaoRecord extends BaseDO{
    @Id
    private Long id;
    @Column
    private Long product_id;
    @Column
    private Long period_id;
    @Column
    private Long order_id;
    @Column
    private Long user_id;
    @Column
    private String user_nick;
    @Column
    private String user_ip;
    @Column
    private Integer buy_count;
    @Column
    private String buy_code;
    @Column
    private Integer lucky;
    @Column
    private Date gmt_create;

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

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_nick() {
        return user_nick;
    }

    public void setUser_nick(String user_nick) {
        this.user_nick = user_nick;
    }

    public String getUser_ip() {
        return user_ip;
    }

    public void setUser_ip(String user_ip) {
        this.user_ip = user_ip;
    }

    public Integer getBuy_count() {
        return buy_count;
    }

    public void setBuy_count(Integer buy_count) {
        this.buy_count = buy_count;
    }

    public String getBuy_code() {
        return buy_code;
    }

    public void setBuy_code(String buy_code) {
        this.buy_code = buy_code;
    }

    public Integer getLucky() {
        return lucky;
    }

    public void setLucky(Integer lucky) {
        this.lucky = lucky;
    }

    public Date getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(Date gmt_create) {
        this.gmt_create = gmt_create;
    }
}
