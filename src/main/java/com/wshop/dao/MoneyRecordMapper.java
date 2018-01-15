package com.wshop.dao;

import com.wshop.model.MoneyRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MoneyRecordMapper {
  //  int deleteByPrimaryKey(Integer id);

   // int insert(MoneyRecord record);

 //   int insertSelective(MoneyRecord record);

   // MoneyRecord selectByPrimaryKey(Integer id);

    void addMoneyRecord(MoneyRecord record);

  //  int updateByPrimaryKeySelective(MoneyRecord record);

//    int updateByPrimaryKey(MoneyRecord record);
}