package com.mystore.petstore.controller;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mystore.petstore.entity.Cart;
import com.mystore.petstore.entity.CartItem;
import com.mystore.petstore.entity.Item;
import com.mystore.petstore.service.CatalogService;

/**
 * 购物车控制器
 * @author siming
 * 
 */
@Controller
@RequestMapping("/cart")
public class CartController extends AbstractController {

	private static final long serialVersionUID = -4038684592582714235L;

	private static final String VIEW_CART = "cart/Cart";
	private static final String CHECK_OUT = "cart/Checkout";

	@Autowired
	private transient CatalogService catalogService;

	private Cart cart;
	//private String workingItemId;

	@RequestMapping(value = "addItemToCart", method = RequestMethod.GET)
	public String addItemToCart(String workingItemId, HttpSession session) {
		this.cart = getCart(session);
		if (cart.containsItemId(workingItemId)) {
			cart.incrementQuantityByItemId(workingItemId);
		} else {
			// 新加入物品到购物车，isInStock应该每次都更新值
			boolean isInStock = catalogService.isItemInStock(workingItemId);
			Item item = catalogService.getItem(workingItemId);
			cart.addItem(item, isInStock);
		}

		session.setAttribute(KEY_CART_CONTROLLER, this);
		return VIEW_CART;
	}

	@RequestMapping(value = "removeItemFromCart", method = RequestMethod.GET)
	public String removeItemFromCart(Model model, HttpSession session, String workingItemId) {
		this.cart = getCart(session);
		Item item = cart.removeItemById(workingItemId);
		if (item == null) {
			String msg = "Attempted to remove null CartItem from Cart.";
			setMessage(model, msg);
			return ERROR;
		} else {
			session.setAttribute(KEY_CART_CONTROLLER, this);
			return VIEW_CART;
		}
	}

	@RequestMapping(value = "updateCartQuantities", method = RequestMethod.POST)
	public String updateCartQuantities(HttpServletRequest request, HttpSession session) {
		this.cart = getCart(session);
		Iterator<CartItem> cartItems = getCart().getAllCartItems();
		while (cartItems.hasNext()) {
			CartItem cartItem = (CartItem) cartItems.next();
			String itemId = cartItem.getItem().getItemId();
			try {
				int quantity = Integer.parseInt((String) request
						.getParameter(itemId));
				getCart().setQuantityByItemId(itemId, quantity);
				if (quantity < 1) {
					cartItems.remove();
				}
			} catch (Exception e) {
				// ignore parse exceptions on purpose
			}
		}

		session.setAttribute(KEY_CART_CONTROLLER, this);
		return VIEW_CART;
	}

	@RequestMapping(value = "viewCart", method = RequestMethod.GET)
	public String viewCart() {
		return VIEW_CART;
	}

	@RequestMapping(value = "checkOut", method = RequestMethod.GET)
	public String checkOut() {
		return CHECK_OUT;
	}

	public void clear(HttpSession session) {
		cart = new Cart();
		//workingItemId = null;
		session.setAttribute(KEY_CART_CONTROLLER, this);
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	private Cart getCart(HttpSession session) {
		Cart cart = null;
		CartController cartBean = (CartController) session.getAttribute(KEY_CART_CONTROLLER);
		if (cartBean != null) {
			cart = cartBean.getCart();
		}
		else {
			cart = new Cart();
		}
		return cart;
	}
}
