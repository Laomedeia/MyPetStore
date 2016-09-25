package com.mystore.petstore.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mystore.petstore.entity.Order;
import com.mystore.petstore.service.OrderService;

/**
 * 订单控制器
 * @author siming
 * 
 */
@Controller
@RequestMapping("/order")
public class OrderController extends AbstractController {

	private static final long serialVersionUID = -6171288227470176272L;

	private static final String CONFIRM_ORDER = "order/ConfirmOrder";
	private static final String LIST_ORDERS = "order/ListOrders";
	private static final String NEW_ORDER = "order/NewOrderForm";
	private static final String SHIPPING = "order/ShippingForm";
	private static final String VIEW_ORDER = "order/ViewOrder";
	
	private static final String KEY_ORDER = "order";
	private static final String KEY_ORDERSID = "orderSid";

	private static final List<String> CARD_TYPE_LIST;

	@Autowired
	private transient OrderService orderService;

	static {
		List<String> cardList = new ArrayList<String>();
		cardList.add("Visa");
		cardList.add("MasterCard");
		cardList.add("American Express");
		CARD_TYPE_LIST = Collections.unmodifiableList(cardList);
	}

	@RequestMapping(value = "listOrders", method = RequestMethod.GET)
	public String listOrders(HttpSession session, Model model) {
		AccountController accountBean = (AccountController) session
				.getAttribute(KEY_ACCOUNT_CONTROLLER);
		List<Order> orderList = orderService.getOrdersByUsername(accountBean.getAccount()
				.getUsername());
		model.addAttribute("orderList", orderList);
		return LIST_ORDERS;
	}

	@RequestMapping(value = "newOrderForm", method = RequestMethod.GET)
	public String newOrderForm(HttpSession session, Model model) {
		AccountController accountBean = (AccountController) session
				.getAttribute(KEY_ACCOUNT_CONTROLLER);
		CartController cartBean = (CartController) session
				.getAttribute(KEY_CART_CONTROLLER);

		clear();
		if (accountBean == null || !accountBean.isAuthenticated()) {
			String msg = "生成订单前您应该先登录，请重新登录再试。";
			setMessage(model, msg);
			return "forward:/account/signonForm";
		} else if (cartBean != null) {
			Order order = new Order();
			order.initOrder(accountBean.getAccount(), cartBean.getCart());
			model.addAttribute(KEY_ORDER, order);
			model.addAttribute("creditCardTypes", CARD_TYPE_LIST);
			String orderSid = KEY_ORDERSID + System.currentTimeMillis();
			model.addAttribute(KEY_ORDERSID, orderSid);
			session.setAttribute(orderSid, order);
			return NEW_ORDER;
		} else {
			String msg = "SORRY，订单未能创建，购物车对象未找到。";
			setMessage(model, msg);
			return ERROR;
		}
	}
	
	@RequestMapping(value = "newShipping", method = RequestMethod.POST)
	public String newShipping(boolean shippingAddressRequired, Order orderInfo, 
			String orderSid, HttpSession session, Model model) {
		Order order = (Order) session.getAttribute(orderSid);
		if (order == null) {
			setMessage(model, "处理订单时发生错误 (订单对象为空).");
			return ERROR;
		}
		
		String returnPage = null;
		if (shippingAddressRequired) {
			mergeOrderInfo(order, orderInfo);
			returnPage = SHIPPING;
		} else {
			mergeShippingInfo(order, orderInfo);
			returnPage = CONFIRM_ORDER;
		}
		
		model.addAttribute(KEY_ORDER, order);
		model.addAttribute(KEY_ORDERSID, orderSid);
		session.setAttribute(orderSid, order);
		return returnPage;
	}
	
	@RequestMapping(value = "newOrder", method = {RequestMethod.POST, RequestMethod.GET})
	public String newOrder(boolean confirmed, Order orderInfo, 
			String orderSid, HttpSession session, Model model) {
		Order order = (Order) session.getAttribute(orderSid);
		if(order == null) {
			setMessage(model, "处理订单时发生错误 (订单对象为空).");
			return ERROR;
		} else if (!confirmed) {
			mergeOrderInfo(order, orderInfo);
			model.addAttribute(KEY_ORDER, order);
			model.addAttribute(KEY_ORDERSID, orderSid);
			session.setAttribute(orderSid, order);
			return CONFIRM_ORDER;
		} else {
			orderService.insertOrder(order);
			CartController cartBean = (CartController) session
					.getAttribute(KEY_CART_CONTROLLER);
			cartBean.clear(session);
			model.addAttribute(KEY_ORDER, order);
			session.removeAttribute(orderSid);
			setMessage(model, "谢谢您, 您的订单已经成功提交.");

			return VIEW_ORDER;
		}
	}

	@RequestMapping(value = "viewOrder", method = RequestMethod.GET)
	public String viewOrder(Order order, HttpSession session, Model model) {
		AccountController accountBean = (AccountController) session
				.getAttribute(KEY_ACCOUNT_CONTROLLER);

		order = orderService.getOrder(order.getOrderId());

		if (accountBean.getAccount().getUsername().equals(order.getUsername())) {
			model.addAttribute(KEY_ORDER, order);
			return VIEW_ORDER;
		} else {
			//order = null;
			setMessage(model, "您只能查看您自己的订单.");
			return ERROR;
		}
	}

	public void clear() {
		
	}

	public List<String> getCreditCardTypes() {
		return CARD_TYPE_LIST;
	}
	
	private void mergeOrderInfo(Order order, Order orderInfo) {
		order.setCardType(orderInfo.getCardType());
		order.setCreditCard(orderInfo.getCreditCard());
		order.setExpiryDate(orderInfo.getExpiryDate());
		order.setBillToFirstName(orderInfo.getBillToFirstName());
		order.setBillToLastName(orderInfo.getBillToLastName());
		order.setBillAddress1(orderInfo.getBillAddress1());
		order.setBillAddress2(orderInfo.getBillAddress2());
		order.setBillCity(orderInfo.getBillCity());
		order.setBillState(orderInfo.getBillState());
		order.setBillZip(orderInfo.getBillZip());
		order.setBillCountry(orderInfo.getBillCountry());
	}
	
	private void mergeShippingInfo(Order order, Order shippingInfo) {
		order.setShipToFirstName(shippingInfo.getShipToFirstName());
		order.setShipToLastName(shippingInfo.getShipToLastName());
		order.setShipAddress1(shippingInfo.getShipAddress1());
		order.setShipAddress2(shippingInfo.getShipAddress2());
		order.setShipCity(shippingInfo.getShipCity());
		order.setShipState(shippingInfo.getShipState());
		order.setShipZip(shippingInfo.getShipZip());
		order.setShipCountry(shippingInfo.getShipCountry());
	}
}
