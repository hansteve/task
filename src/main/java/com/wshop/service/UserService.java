package com.wshop.service;

import com.wshop.model.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 16-7-25
 * Time: 下午10:16
 * To change this template use File | Settings | File Templates.
 */
public interface UserService {

    public User getUserById(Integer id);

    public User getParentUserById(long id);

    public void updateUserMoneyById(long id, double money);

    public List<User> getTeamUser(long uid);


}
