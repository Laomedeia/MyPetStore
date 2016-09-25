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

import com.mystore.petstore.entity.Account;
import com.mystore.petstore.entity.Product;
import com.mystore.petstore.service.AccountService;
import com.mystore.petstore.service.CatalogService;

/**
 * 账户及登录控制器
 * @author siming
 * 
 */
@Controller
@RequestMapping("/account")
public class AccountController extends AbstractController {

	private static final long serialVersionUID = 5499663666155758178L;

	private static final String NEW_ACCOUNT = "account/NewAccountForm";
	private static final String EDIT_ACCOUNT = "account/EditAccountForm";
	private static final String SIGNON = "account/SignonForm";

	private static final List<String> LANGUAGE_LIST;
	private static final List<String> CATEGORY_LIST;

	@Autowired
	private transient AccountService accountService;
	@Autowired
	private transient CatalogService catalogService;

	private Account account = new Account();
	private List<Product> myList;
	private boolean authenticated;

	//初始化基础数据
	static {
		List<String> langList = new ArrayList<String>();
		langList.add("english");
		langList.add("中文");
		LANGUAGE_LIST = Collections.unmodifiableList(langList);

		List<String> catList = new ArrayList<String>();
		catList.add("FISH");
		catList.add("DOGS");
		catList.add("REPTILES");
		catList.add("CATS");
		catList.add("BIRDS");
		CATEGORY_LIST = Collections.unmodifiableList(catList);
	}

	@RequestMapping(value = "newAccountForm", method = RequestMethod.GET)
	public String newAccountForm(Model model) {
		model.addAttribute(KEY_ACCOUNT_CONTROLLER, this);
		return NEW_ACCOUNT;
	}

	@RequestMapping(value = "newAccount", method = RequestMethod.POST)
	public String newAccount(Account account, HttpSession session) {
		this.accountService.insertAccount(account);
		this.account = this.accountService.getAccount(account.getUsername());
		this.myList = this.catalogService.getProductListByCategory(account
				.getFavouriteCategoryId());
		this.authenticated = true;
		this.account.setPassword(null);
		session.setAttribute(KEY_ACCOUNT_CONTROLLER, this);
		// return new RedirectResolution(CatalogActionBean.class);
		return "redirect:/catalog/viewMain";
	}

	@RequestMapping(value = "editAccountForm", method = RequestMethod.GET)
	public String editAccountForm() {
		return EDIT_ACCOUNT;
	}

	@RequestMapping(value = "editAccount", method = RequestMethod.POST)
	public String editAccount(Account account, HttpSession session, Model model) {
		//只允许修改本帐号
		AccountController accountBean = (AccountController) session.getAttribute(KEY_ACCOUNT_CONTROLLER);
		if (accountBean == null || !accountBean.isAuthenticated() 
				|| !accountBean.getUsername().equals(account.getUsername())) {
			setMessage(model, "Invalid Request!");
			return ERROR;
		}
		
		this.accountService.updateAccount(account);
		this.account = this.accountService.getAccount(account.getUsername());
		this.account.setPassword(null);
		this.myList = this.catalogService.getProductListByCategory(account
				.getFavouriteCategoryId());
		session.setAttribute(KEY_ACCOUNT_CONTROLLER, this);
		return "redirect:/catalog/viewMain";
	}

	// @DefaultHandler
	@RequestMapping(value = "signonForm", method = RequestMethod.GET)
	public String signonForm() {
		return SIGNON;
	}

	@RequestMapping(value = "signon", method = RequestMethod.POST)
	public String signon(Model model, HttpSession session, String username,
			String password) {
		this.account = this.accountService.getAccount(username, password);

		if (this.account == null) {
			String msg = "Invalid username or password.  Signon failed.";
			setMessage(model, msg);
			clear(session);
			return SIGNON;
		} else {
			this.account.setPassword(null);
			this.myList = this.catalogService
					.getProductListByCategory(this.account
							.getFavouriteCategoryId());
			this.authenticated = true;
			
			session.setAttribute(KEY_ACCOUNT_CONTROLLER, this);
			return "redirect:/catalog/viewMain";
		}
	}

	@RequestMapping(value = "signoff", method = RequestMethod.GET)
	public String signoff(HttpSession session) {
		session.invalidate();
		//clear();
		return "redirect:/catalog/viewMain";
	}

	public boolean isAuthenticated() {
		return authenticated && account != null
				&& account.getUsername() != null;
	}

	public void clear(HttpSession session) {
		account = new Account();
		myList = null;
		authenticated = false;
		session.setAttribute(KEY_ACCOUNT_CONTROLLER, this);
	}

	public Account getAccount() {
		return this.account;
	}

	public String getUsername() {
		return account.getUsername();
	}

	// @Validate(required=true, on={"signon", "newAccount", "editAccount"})
	public void setUsername(String username) {
		account.setUsername(username);
	}

	public String getPassword() {
		return account.getPassword();
	}

	// @Validate(required=true, on={"signon", "newAccount", "editAccount"})
	public void setPassword(String password) {
		account.setPassword(password);
	}

	public List<Product> getMyList() {
		return myList;
	}

	public void setMyList(List<Product> myList) {
		this.myList = myList;
	}

	public List<String> getLanguages() {
		return LANGUAGE_LIST;
	}

	public List<String> getCategories() {
		return CATEGORY_LIST;
	}
}
