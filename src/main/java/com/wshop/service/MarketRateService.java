package com.wshop.service;

import com.wshop.model.MarketRate;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 16-8-5
 * Time: 上午8:43
 * To change this template use File | Settings | File Templates.
 */
public interface MarketRateService {

    public List<MarketRate> getMarketRateByUser(long mid);

     public List<MarketRate> getDefaultMarketRate();
}
