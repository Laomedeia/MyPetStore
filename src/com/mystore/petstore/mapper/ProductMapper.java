package com.mystore.petstore.mapper;

import java.util.List;

import com.mystore.petstore.entity.Product;


/**
 * 商品相关crud接口类
 * @author siming
 *
 */
public interface ProductMapper {

  List<Product> getProductListByCategory(String categoryId);

  Product getProduct(String productId);

  List<Product> searchProductList(String keywords);

}
