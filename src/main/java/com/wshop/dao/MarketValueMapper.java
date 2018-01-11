package com.wshop.dao;

import com.wshop.model.MarketValue;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;

@Mapper
public interface MarketValueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MarketValue record);

    int insertSelective(MarketValue record);

    MarketValue selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MarketValue record);

    int updateByPrimaryKey(MarketValue record);

    void  updateMarketValueByUser(long uid,BigDecimal money);

    void updateTeamMarketValueByUser(long uid, BigDecimal team_money);

    MarketValue getNowMarketValueByUser(long uid);
}