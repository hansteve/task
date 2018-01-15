package com.wshop.dao;

import com.wshop.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsersMapper {
   // int deleteByPrimaryKey(Integer id);

  //  int insert(User record);

 //   int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

 //   int updateByPrimaryKeySelective(User record);

 //   int updateByPrimaryKey(User record);

    User getParentUserById(int id);

    void updateUserMoneyById(int id, double money);

    List<User> getTeamUser(int uid);
}