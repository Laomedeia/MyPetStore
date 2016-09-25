package com.mystore.petstore.mapper;

import java.util.List;

import com.mystore.petstore.entity.Category;


/**
 * 分类相关crud接口
 * @author siming
 *
 */
public interface CategoryMapper {

  List<Category> getCategoryList();

  Category getCategory(String categoryId);

}
