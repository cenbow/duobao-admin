package com.aibinong.backyard.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;
import java.util.Iterator;

/**
 * Created by ouwa on 16/6/29.
 */
@Table("user_account_record${cid}")
public class UserAccountRecord extends BaseDO{
    @Id
    private Long id;
    @Column
    private Integer amount;
    private Float amount_y;
    @Column
    private Integer balance;
    private Float balance_y;
    @Column
    private Date gmt_create;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
        this.amount_y = Float.valueOf(amount / 100);
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
        this.balance_y = Float.valueOf(balance / 100);
    }

    public Date getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(Date gmt_create) {
        this.gmt_create = gmt_create;
    }

    public Float getAmount_y() {
        return amount_y;
    }

    public Float getBalance_y() {
        return balance_y;
    }
}
