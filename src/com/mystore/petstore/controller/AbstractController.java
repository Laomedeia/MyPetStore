package com.mystore.petstore.controller;

import java.io.Serializable;

import org.springframework.ui.Model;

/**
 * 控制器基类
 * @author siming
 *
 */
public abstract class AbstractController implements Serializable {

  private static final long serialVersionUID = -1767714708233127983L;

  protected static final String MAIN = "catalog/Main";
  protected static final String ERROR = "common/Error";
  protected static final String KEY_MSG = "msg";
  protected static final String KEY_ACCOUNT_CONTROLLER = "accountBean";
  protected static final String KEY_CART_CONTROLLER = "cartBean";

  
  protected void setMessage(Model model, String msg) {
	  model.addAttribute(KEY_MSG, msg);
  }
}
