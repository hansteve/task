package com.wshop.dao;

import com.wshop.model.MarketValue;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.Map;

@Mapper
public interface MarketValueMapper {
  //  int deleteByPrimaryKey(Integer id);

    int insert(MarketValue record);

 //   int insertSelective(MarketValue record);

    MarketValue selectByPrimaryKey(Integer id);

  //  int updateByPrimaryKeySelective(MarketValue record);

   // int updateByPrimaryKey(MarketValue record);

    void  updateMarketValueByUser(Map<String, Object> map);

    void updateTeamMarketValueByUser(Map<String, Object> map);

    MarketValue getNowMarketValueByUser(int uid);
}