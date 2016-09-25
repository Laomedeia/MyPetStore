package com.mystore.test.helloworld;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author siming
 */
@Controller
@RequestMapping("/helloworld")
public class HelloWorldController {

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String hello(Model model, HttpSession session) {
		model.addAttribute("sessionId", session.getId());
		model.addAttribute("path", this.getClass().getClassLoader().getResource(""));
		return "index";
	}
}
