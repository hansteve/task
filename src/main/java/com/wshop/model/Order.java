package com.wshop.model;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private Integer id;

    private Integer mid;

    private String orderid;

    private Integer userId;

    private BigDecimal orderprice;

    private Integer addressid;

    private Integer status;

    private Byte supportmethod;

    private Date supportTime;

    private String closemsg;

    private String userfree;

    private String freecode;

    private BigDecimal freeprice;

    private Date fahuoTime;

    private Date addTime;

    private Integer flag;

    private String sellerremark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(BigDecimal orderprice) {
        this.orderprice = orderprice;
    }

    public Integer getAddressid() {
        return addressid;
    }

    public void setAddressid(Integer addressid) {
        this.addressid = addressid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Byte getSupportmethod() {
        return supportmethod;
    }

    public void setSupportmethod(Byte supportmethod) {
        this.supportmethod = supportmethod;
    }

    public Date getSupportTime() {
        return supportTime;
    }

    public void setSupportTime(Date supportTime) {
        this.supportTime = supportTime;
    }

    public String getClosemsg() {
        return closemsg;
    }

    public void setClosemsg(String closemsg) {
        this.closemsg = closemsg == null ? null : closemsg.trim();
    }

    public String getUserfree() {
        return userfree;
    }

    public void setUserfree(String userfree) {
        this.userfree = userfree == null ? null : userfree.trim();
    }

    public String getFreecode() {
        return freecode;
    }

    public void setFreecode(String freecode) {
        this.freecode = freecode == null ? null : freecode.trim();
    }

    public BigDecimal getFreeprice() {
        return freeprice;
    }

    public void setFreeprice(BigDecimal freeprice) {
        this.freeprice = freeprice;
    }

    public Date getFahuoTime() {
        return fahuoTime;
    }

    public void setFahuoTime(Date fahuoTime) {
        this.fahuoTime = fahuoTime;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getSellerremark() {
        return sellerremark;
    }

    public void setSellerremark(String sellerremark) {
        this.sellerremark = sellerremark == null ? null : sellerremark.trim();
    }
}