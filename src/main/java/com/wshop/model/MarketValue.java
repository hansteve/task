package com.wshop.model;

import java.math.BigDecimal;
import java.util.Date;

public class MarketValue {
    private Integer id;

    private Integer mid;

    private Integer uid;

    private Integer year;

    private Integer month;

    private BigDecimal market;

    private BigDecimal teamMarket;

    private BigDecimal personMoney;

    private BigDecimal teamMoney;

    private BigDecimal leaderMoney;

    private BigDecimal totalMoney;

    private Integer status;

    private Date addTime;

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

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public BigDecimal getMarket() {
        return market;
    }

    public void setMarket(BigDecimal market) {
        this.market = market;
    }

    public BigDecimal getTeamMarket() {
        return teamMarket;
    }

    public void setTeamMarket(BigDecimal teamMarket) {
        this.teamMarket = teamMarket;
    }

    public BigDecimal getPersonMoney() {
        return personMoney;
    }

    public void setPersonMoney(BigDecimal personMoney) {
        this.personMoney = personMoney;
    }

    public BigDecimal getTeamMoney() {
        return teamMoney;
    }

    public void setTeamMoney(BigDecimal teamMoney) {
        this.teamMoney = teamMoney;
    }

    public BigDecimal getLeaderMoney() {
        return leaderMoney;
    }

    public void setLeaderMoney(BigDecimal leaderMoney) {
        this.leaderMoney = leaderMoney;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}