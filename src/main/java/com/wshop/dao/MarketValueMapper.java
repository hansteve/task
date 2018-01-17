package com.wshop.dao;

import com.wshop.model.MarketValue;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
public interface MarketValueMapper {
  //  int deleteByPrimaryKey(Integer id);

    int insert(MarketValue record);

 //   int insertSelective(MarketValue record);

    MarketValue selectByPrimaryKey(long id);

    List<MarketValue> getBeforeMarketValueAll(Map<String, Object> map);

    void  updateMarketValueByUser(Map<String, Object> map);

    void updateTeamMarketValueByUser(Map<String, Object> map);

    MarketValue getNowMarketValueByUser(int uid);
//--------------------------- todo

    void updatePersonMoneyByUser(Map<String, Object> map);

    void updateTeamMoneyByUser(Map<String, Object> map);

    void updateLeaderMoneyByUser(Map<String, Object> map);

    void updateMarketValueStatus(Map<String, Object> map);

    MarketValue getMarketValueByUser(Map<String, Object> map);

    List<MarketValue> getUserMarketValue(Map<String, Object> map);

    void updatePersonMoneyById(Map<String, Object> map);

}