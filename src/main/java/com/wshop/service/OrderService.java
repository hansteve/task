package com.wshop.service;

import com.wshop.model.Order;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: han
 * Date: 16-7-25
 * Time: 下午9:29
 * To change this template use File | Settings | File Templates.
 */
public interface OrderService {

   public int updateOrderStatus(String orderId, int flag);

   public List<Order> getOrderByMonth();

/*   public List<Order> getOrderByStatus(int status);

   public List<Order> getOrderBeforeMonth();*/

}
