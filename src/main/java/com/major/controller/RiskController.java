package com.major.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.major.model.Plan;
import com.major.model.Project;
import com.major.model.Risk;
import com.major.model.User;
import com.major.service.PlanService;
import com.major.service.RiskService;
import com.major.service.UserService;

@Controller
public class RiskController {
	@Autowired
	UserService userService;
	
	@Autowired
	RiskService riskService;
	
	@Autowired
	PlanService planService;
	
	@RequestMapping(value = { "/importRisk" })
	public String importRisk(Model model, @RequestParam("planId") Integer planId, HttpSession session){
		model.addAttribute("user", (User) session.getAttribute("user"));
		model.addAttribute("project", (Project) session.getAttribute("project"));
		Plan plan = planService.getPlan(planId);
		model.addAttribute("plan", plan);
		
		
		return "importRisk";
		
	}
	
	@RequestMapping(value = { "/riskList" })
	public String addRisk(Model model, HttpSession session){
		model.addAttribute("user", (User) session.getAttribute("user"));
		List<Risk> riskList = riskService.getAllRisks();
		
		model.addAttribute("riskList", riskList);
		
		return "riskList";	
	}

}
