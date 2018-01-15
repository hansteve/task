package com.wshop.service.impl;

import com.wshop.model.User;
import com.wshop.dao.UsersMapper;
import com.wshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: han
 * Date: 16-7-25
 * Time: 下午10:16
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UsersMapper   usersMapper ;

    @Override
    public User getUserById(Integer id) {
        return usersMapper.selectByPrimaryKey(id);
    }
/*
    @Override
    public User getParentUserById(long id) {
        return usersMapper.getParentUserById(id);
    }

    @Override
    public void updateUserMoneyById(long id, double money) {
        usersMapper.updateUserMoneyById(id,money);
    }

    @Override
    public List<User> getTeamUser(long uid) {
        return usersMapper.getTeamUser(uid);
    }*/

}
