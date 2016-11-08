package com.major.controller;

import com.major.model.Risk;
import com.major.model.User;
import com.major.service.RiskService;
import com.major.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

	@Autowired
	UserService userService;
	
	@Autowired
	RiskService riskService;

	private HttpSession session = null;
	
	@RequestMapping(path = { "/", "/login" })
	public String loginShow(){
		return "login";
	}

	@RequestMapping(path = { "//loginUserHome" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String login(Model model, 
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			HttpServletRequest request,
            HttpServletResponse response) {
		Map<String, Object> map = userService.login(username, password);
		String msg = (String)map.get("msg");
		if(msg.equals("该用户不存在!") || msg.equals("密码错误!")){
			model.addAttribute("error", msg);
			return "Error";
		}
		
		model.addAttribute("user", (User)map.get("user"));
		session = request.getSession(true);
		session.setAttribute("user", (User)map.get("user"));
		
		List<Risk> riskList = riskService.getAllRisks();
		model.addAttribute("riskList", riskList);
		
		return "user";
	}

}
