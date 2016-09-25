package com.mystore.petstore.mapper;

import java.util.List;

import com.mystore.petstore.entity.LineItem;


/**
 * 订单含有物品crud接口类
 * @author siming
 *
 */
public interface LineItemMapper {

  List<LineItem> getLineItemsByOrderId(int orderId);

  void insertLineItem(LineItem lineItem);

}
