package com.wshop.dao;

import com.wshop.model.MoneyRecord;

public interface MoneyRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MoneyRecord record);

    int insertSelective(MoneyRecord record);

    MoneyRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MoneyRecord record);

    int updateByPrimaryKey(MoneyRecord record);
}