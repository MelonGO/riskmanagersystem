package com.major.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.major.model.Risk;
import com.major.model.User;
import com.major.service.RiskService;
import com.major.service.UserService;

@Controller
public class RegisterController {

	@Autowired
	UserService userService;
	
	@Autowired
	RiskService riskService;
	
	private HttpSession session = null;
	
	@RequestMapping(path = { "/register" })
	public String registerShow(){
		return "register";
	}

	@RequestMapping(value = { "/register_user_home" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String register(Model model, 
			@RequestParam("username") String username,
			@RequestParam("password") String password, 
			@RequestParam("role") String role,
			HttpServletRequest request,
            HttpServletResponse response) {
		
		Map<String, Object> map = userService.register(username, password, role);
		String msg = (String)map.get("msg");
		if(msg.equals("该用户已经被注册!")){
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
