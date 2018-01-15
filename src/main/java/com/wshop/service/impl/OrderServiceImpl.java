package com.wshop.service.impl;

import com.wshop.model.Order;
import com.wshop.dao.OrderMapper;
import com.wshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: han
 * Date: 16-7-25
 * Time: 下午9:30
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderMapper orderDAO;

    @Override
    public   int updateOrderStatus(String orderId,int flag){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderId", orderId);
        map.put("flag", flag);
        return orderDAO.updateOrderStatus(map);
    }

    @Override
    public List<Order> getOrderByMonth() {
        return orderDAO.getOrderByMonth();
    }

 /*   @Override
    public List<Order> getOrderByStatus(int status) {
        return orderDAO.getOrderByStatus(status);
    }

    @Override
    public List<Order> getOrderBeforeMonth() {
        return orderDAO.getOrderBeforeMonth();
    }*/
}
