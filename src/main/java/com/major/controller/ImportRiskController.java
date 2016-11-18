package com.major.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.major.model.Plan;
import com.major.model.PlanRisk;
import com.major.model.Project;
import com.major.model.Risk;
import com.major.model.RiskNum;
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
		
		return "importRisk";
		
	}
	
	@RequestMapping(value = { "/searchRisk/{filter}" })
	@ResponseBody
	public List<RiskNum> searchRisk(Model model, 
				@PathVariable("filter") String filter, 
				HttpServletRequest request){
		String startTime = RequestUtil.getString(request, "startTime", null);
		String endTime = RequestUtil.getString(request, "endTime", null);
		
		if (filter.equals("rec")) {
			List<RiskNum> mostRecRiskList = planRiskService.getMostRecognized(startTime, endTime);
			return mostRecRiskList;
			
		} else {
			List<RiskNum> mostProRiskList = planRiskService.getMostProblems(startTime, endTime);
			return mostProRiskList;
		}
		
	}
	
	@RequestMapping(value = { "/importExistRisk" })
	@ResponseBody
	public String importExistRisk(Model model,HttpServletRequest request, HttpSession session){
		Integer planId = RequestUtil.getPositiveInteger(request, "planId", null);
		Integer riskId = RequestUtil.getPositiveInteger(request, "riskId", null);
		
		model.addAttribute("user", (User) session.getAttribute("user"));
		model.addAttribute("project", (Project) session.getAttribute("project"));
		User user = (User) session.getAttribute("user");
		
		Risk risk = riskService.getRisk(riskId);
		
		PlanRisk planRisk = new PlanRisk();
		
		planRisk.setPlanId(planId);
		planRisk.setRiskId(riskId);
		planRisk.setType(risk.getType());
		planRisk.setContent(risk.getContent());
		planRisk.setInfluence("high");
		planRisk.setProbability("high");
		planRisk.setTriggerOrThreshold("");
		planRisk.setSubmitter(user.getId());
		planRisk.setTracer(user.getId());
		
		Map<String, Object> msg = planRiskService.addPlanRisk(planRisk);
		
		return (String) msg.get("msg");
		
	}
	
	
	
}
