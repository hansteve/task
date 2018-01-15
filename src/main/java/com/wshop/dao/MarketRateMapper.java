package com.wshop.dao;

import com.wshop.model.MarketRate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MarketRateMapper {
  //  int deleteByPrimaryKey(Integer id);

   // int insert(MarketRate record);

  //  int insertSelective(MarketRate record);

  //  MarketRate selectByPrimaryKey(Integer id);

   // int updateByPrimaryKeySelective(MarketRate record);

  //  int updateByPrimaryKey(MarketRate record);

    List<MarketRate> getMarketRateByUser(Integer mid);

}