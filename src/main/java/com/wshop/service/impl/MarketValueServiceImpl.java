package com.wshop.service.impl;

import com.wshop.model.MarketValue;
import com.wshop.dao.MarketValueMapper;
import com.wshop.service.MarketValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: han
 * Date: 16-7-26
 * Time: 上午10:15
 */
@Service
public class MarketValueServiceImpl implements MarketValueService{

    @Autowired
    MarketValueMapper marketValueDAO;

  /*  @Override
    public List<MarketValue> getBeforeMarketValueAll(long year,long month,int status) {
       return   marketValueDAO.getBeforeMarketValueAll(year,month,status);
    }*/

    @Override
    public void updateMarketValueByUser(int uid,BigDecimal market_money,long year,long month) {
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("uid", uid);
         map.put("market_money", market_money);
         map.put("year", year);
         map.put("month", month);
         marketValueDAO.updateMarketValueByUser(map);
    }

    @Override
    public void updateTeamMarketValueByUser(int uid, BigDecimal team_money, long year, long month) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("uid", uid);
        map.put("team_money", team_money);
        map.put("year", year);
        map.put("month", month);
        marketValueDAO.updateTeamMarketValueByUser(map);
    }

   /* @Override
    public void updatePersonMoneyByUser(long uid,double person_money,long year,long month) {
        marketValueDAO.updatePersonMoneyByUser(uid, person_money, year, month);
    }*/

 /*   @Override
    public void updateTeamMoneyByUser(long uid,double team_money,long year,long month) {
        marketValueDAO.updateTeamMoneyByUser(uid, team_money, year, month);
    }

    @Override
    public void updateLeaderMoneyByUser(long uid,double leader_money,long year,long month) {
        marketValueDAO.updateLeaderMoneyByUser(uid, leader_money, year, month);
    }

    @Override
    public void updateMarketValueStatus(long id, int status) {
        marketValueDAO.updateMarketValueStatus(id,status);
    }
*/
    @Override
    public void addMarketValueByUser(MarketValue marketValue) {
      marketValueDAO.insert(marketValue);
    }

/*    @Override
    public MarketValue getMarketValueByUser(long uid, int year, int month) {
        return marketValueDAO.getMarketValueByUser(uid,year,month);
    }*/

    @Override
    public MarketValue getNowMarketValueByUser(int uid) {
        return marketValueDAO.getNowMarketValueByUser(uid);
    }

   /* @Override
    public List<MarketValue> getUserMarketValue(long uid,long year,long month) {
        return marketValueDAO.getUserMarketValue(uid,year,month);
    }

    @Override
    public void updatePersonMoneyById(long id,double total_money){
          marketValueDAO.updatePersonMoneyById(id,total_money);
    }

    @Override
    public MarketValue getMarketValueById(long id){
        return marketValueDAO.selectByPrimaryKey(id);
    }*/
}
