package com.wshop.service;

import com.wshop.model.MarketValue;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 16-7-26
 * Time: 上午10:16
 * To change this template use File | Settings | File Templates.
 */
public interface MarketValueService {

    public List<MarketValue> getBeforeMarketValueAll(long year, long month, int status);

    public void updateMarketValueByUser(int uid, BigDecimal money, long year, long month);

    public void updateTeamMarketValueByUser(int uid, BigDecimal team_money, long year, long month);

    public void updatePersonMoneyByUser(long uid, double person_money, long year, long month);

    public void updateTeamMoneyByUser(long uid, double team_money, long year, long month);

    public void updateLeaderMoneyByUser(long uid, double leader_money, long year, long month);

    public void updateMarketValueStatus(long id, int status);

    public void addMarketValueByUser(MarketValue marketValue);

    public MarketValue getMarketValueByUser(long uid, int year, int month);

    public MarketValue getNowMarketValueByUser(int uid);

    public List<MarketValue> getUserMarketValue(long uid, long year, long month);

    public void updatePersonMoneyById(long id, double total_money);

    public MarketValue getMarketValueById(long id);
}
