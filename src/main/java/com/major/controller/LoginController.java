package com.major.controller;

import com.major.model.User;
import com.major.service.RiskService;
import com.major.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	UserService userService;
	
	@Autowired
	RiskService riskService;

	@RequestMapping(path = { "/", "/login" })
	public String loginShow(){
		return "login";
	}

	@RequestMapping(path = { "/loginUserHome" })
	public String login(Model model, 
			@RequestParam("username") String username,
			@RequestParam("password") String password,
            HttpSession session) {
		Map<String, Object> map = userService.login(username, password);
		String msg = (String)map.get("msg");
		if(map.get("user") == null) {
			model.addAttribute("error", msg);
			return "Error";
		}
		
		model.addAttribute("user", (User)map.get("user"));
		session.setAttribute("user", (User)map.get("user"));
		
		return "redirect:projectList";
	}

	@RequestMapping(value = {"/logout"})
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
}
