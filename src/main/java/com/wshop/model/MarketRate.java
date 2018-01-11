package com.wshop.model;

import java.math.BigDecimal;

public class MarketRate {
    private Integer id;

    private Integer mid;

    private BigDecimal performanceFrom;

    private BigDecimal performanceTo;

    private BigDecimal percent;

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

    public BigDecimal getPerformanceFrom() {
        return performanceFrom;
    }

    public void setPerformanceFrom(BigDecimal performanceFrom) {
        this.performanceFrom = performanceFrom;
    }

    public BigDecimal getPerformanceTo() {
        return performanceTo;
    }

    public void setPerformanceTo(BigDecimal performanceTo) {
        this.performanceTo = performanceTo;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }
}