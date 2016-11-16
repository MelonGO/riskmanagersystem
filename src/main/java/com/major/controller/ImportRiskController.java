package com.major.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.major.model.Plan;
import com.major.model.Project;
import com.major.model.Risk;
import com.major.model.User;
import com.major.service.PlanRiskService;
import com.major.service.PlanService;
import com.major.service.RiskService;

import tools.RequestUtil;

@Controller
public class ImportRiskController {
	@Autowired
	PlanService planService;
	
	@Autowired
	PlanRiskService planRiskService;
	
	@Autowired
	RiskService riskService;
	
	@RequestMapping(value = { "/importRisk" })
	public String importRisk(Model model, @RequestParam("planId") Integer planId, HttpSession session){
		model.addAttribute("user", (User) session.getAttribute("user"));
		model.addAttribute("project", (Project) session.getAttribute("project"));
		Plan plan = planService.getPlan(planId);
		model.addAttribute("plan", plan);
		
		List<Risk> riskList = new ArrayList<>();
		model.addAttribute("riskList", riskList);
		
		return "importRisk";
		
	}
	
	@RequestMapping(value = { "/checkRisk" })
	@ResponseBody
	public String checkRisk(Model model, HttpServletRequest request){
		String startTime = RequestUtil.getString(request, "startTime", null);
		String endTime = RequestUtil.getString(request, "endTime", null);
		
		List<Risk> riskList = new ArrayList<>();
		Risk r = new Risk();
		r.setType("1");
		r.setContent("bbb");
		riskList.add(r);
		
		model.addAttribute("riskList", riskList);
		
		return "查询成功!";
		
	}
	
	
	
}
