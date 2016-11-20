package com.major.controller;

import java.util.List;

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
		
		List<Risk> riskList = riskService.getAllRisks();
		model.addAttribute("riskList", riskList);
		
		
		return "importRisk";
		
	}
	
	@RequestMapping(value = { "/searchRisk/{filter}" })
	@ResponseBody
	public List<RiskNum> searchRisk(
				@PathVariable("filter") String filter, 
				HttpServletRequest request){
		String startTime = RequestUtil.getString(request, "startTime", null);
		String endTime = RequestUtil.getString(request, "endTime", null);
		
		if ("rec".equals(filter)) {
			return planRiskService.getMostRecognized(startTime, endTime);
			
		} else {
			return planRiskService.getMostProblems(startTime, endTime);
		}
		
	}
	
	@RequestMapping(value = { "/importExistRisk" })
	@ResponseBody
	public String importExistRisk(Model model,HttpServletRequest request, HttpSession session){
		Integer planId = RequestUtil.getPositiveInteger(request, "planId", null);
		String riskIdList = RequestUtil.getString(request, "riskIdList", null);
		
		model.addAttribute("user", (User) session.getAttribute("user"));
		model.addAttribute("project", (Project) session.getAttribute("project"));
		User user = (User) session.getAttribute("user");
		
		if(riskIdList != null){
			String[] riskIdArray = riskIdList.split(":");
			for (int i = 0; i < riskIdArray.length; i++) {
				
				Risk risk = riskService.getRisk(Integer.parseInt(riskIdArray[i]));
				
				PlanRisk planRisk = new PlanRisk();
				planRisk.setPlanId(planId);
				planRisk.setRiskId(risk.getId());
				planRisk.setType(risk.getType());
				planRisk.setContent(risk.getContent());
				planRisk.setInfluence("high");
				planRisk.setProbability("high");
				planRisk.setTriggerOrThreshold("");
				planRisk.setSubmitter(user.getId());
				planRisk.setTracer(user.getId());
				
				planRiskService.addPlanRisk(planRisk);
			}
			return "导入成功!";
		}
		
		return "请选择风险条目!";
		
	}
	
	
	
}
