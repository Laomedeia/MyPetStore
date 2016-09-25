package com.mystore.petstore.mapper;

import java.util.List;
import java.util.Map;

import com.mystore.petstore.entity.Item;


/**
 * 宠物item相关crud接口
 * @author siming
 *
 */
public interface ItemMapper {

  void updateInventoryQuantity(Map<String, Object> param);

  int getInventoryQuantity(String itemId);

  List<Item> getItemListByProduct(String productId);

  Item getItem(String itemId);

}
