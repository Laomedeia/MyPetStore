package com.mystore.petstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mystore.petstore.entity.Category;
import com.mystore.petstore.entity.Item;
import com.mystore.petstore.entity.Product;
import com.mystore.petstore.service.CatalogService;

/**
 * 宠物分类控制器
 * @author siming
 * 
 */
@Controller
@RequestMapping("/catalog")
public class CatalogController extends AbstractController {

	private static final long serialVersionUID = 5849523372175050635L;

	private static final String MAIN = "catalog/Main";
	private static final String VIEW_CATEGORY = "catalog/Category";
	private static final String VIEW_PRODUCT = "catalog/Product";
	private static final String VIEW_ITEM = "catalog/Item";
	private static final String SEARCH_PRODUCTS = "catalog/SearchProducts";

	@Autowired
	private transient CatalogService catalogService;

	// @DefaultHandler
	@RequestMapping(value = "viewMain", method = RequestMethod.GET)
	public String viewMain() {
		return MAIN;
	}

	@RequestMapping(value = "viewCategory", method = RequestMethod.GET)
	public String viewCategory(String categoryId, Model model) {
		if (categoryId != null) {
			List<Product> productList = catalogService.getProductListByCategory(categoryId);
			Category category = catalogService.getCategory(categoryId);
			model.addAttribute("productList", productList);
			model.addAttribute("category", category);
		}
		return VIEW_CATEGORY;
	}

	@RequestMapping(value = "viewProduct", method = RequestMethod.GET)
	public String viewProduct(String productId, Model model) {
		if (productId != null) {
			List<Item> itemList = catalogService.getItemListByProduct(productId);
			Product product = catalogService.getProduct(productId);
			model.addAttribute("itemList", itemList);
			model.addAttribute("product", product);
		}
		return VIEW_PRODUCT;
	}

	@RequestMapping(value = "viewItem", method = RequestMethod.GET)
	public String viewItem(String itemId, Model model) {
		Item item = catalogService.getItem(itemId);
		Product product = item.getProduct();
		model.addAttribute("item", item);
		model.addAttribute("product", product);
		return VIEW_ITEM;
	}

	@RequestMapping(value = "searchProducts", method = RequestMethod.POST)
	public String searchProducts(String keyword, Model model) {
		if (keyword == null || keyword.length() < 1) {
			String msg = "请先输入关键字，然后点击查询按钮.";
			setMessage(model, msg);
			return ERROR;
		} else {
			List<Product> productList = catalogService.searchProductList(keyword
					.toLowerCase());
			model.addAttribute("productList", productList);
			return SEARCH_PRODUCTS;
		}
	}

	public void clear() {
		
	}
}
