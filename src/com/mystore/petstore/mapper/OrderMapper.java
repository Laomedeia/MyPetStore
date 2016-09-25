package com.mystore.petstore.mapper;

import java.util.List;

import com.mystore.petstore.entity.Order;


/**
 * 订单crud接口类
 * @author siming
 *
 */
public interface OrderMapper {

  List<Order> getOrdersByUsername(String username);

  Order getOrder(int orderId);
  
  void insertOrder(Order order);
  
  void insertOrderStatus(Order order);

}
