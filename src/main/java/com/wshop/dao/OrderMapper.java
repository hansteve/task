package com.wshop.dao;

import com.wshop.model.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
   // int deleteByPrimaryKey(Integer id);

   // int insert(Order record);

  //  int insertSelective(Order record);

  //  Order selectByPrimaryKey(Integer id);

  //  int updateByPrimaryKeySelective(Order record);

  //  int updateByPrimaryKeyWithBLOBs(Order record);

   // int updateByPrimaryKey(Order record);

    List<Order>  getOrderByMonth();

    int updateOrderStatus(Map<String, Object> map);
}