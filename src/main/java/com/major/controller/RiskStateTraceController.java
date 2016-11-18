package com.major.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.major.model.PlanRisk;
import com.major.model.Risk;
import com.major.model.RiskStateTrace;
import com.major.model.User;
import com.major.service.PlanRiskService;
import com.major.service.RiskStateTraceService;

import tools.RequestUtil;

@Controller
public class RiskStateTraceController {
	@Autowired
	RiskStateTraceService riskStateTraceService;
	
	@Autowired
	PlanRiskService planRiskService;
	
	@RequestMapping(path = {"/riskStateTraceList" })
	public String projectList(Model model, 
			@RequestParam("planRiskId") Integer planRiskId, 
			HttpSession session) {
		
		List<RiskStateTrace> riskStateTraceList = riskStateTraceService.getByPlanRiskId(planRiskId);
		
		PlanRisk planRisk = planRiskService.getPlanRisk(planRiskId);
		model.addAttribute("riskStateTraceList", riskStateTraceList);
		model.addAttribute("user", (User) session.getAttribute("user"));
		model.addAttribute("planRisk", planRisk);
		
		return "riskStateTraceList";
	}
	
	@RequestMapping(path = {"/getRiskStateTraceById" })
	@ResponseBody
	public RiskStateTrace getProjectById(@RequestParam("riskStateTraceId") Integer riskStateTraceId) {
		return riskStateTraceService.getRiskStateTrace(riskStateTraceId);
	}
	
	@RequestMapping(path = {"/editRiskStateTrace" })
	@ResponseBody
	public String editProject(HttpServletRequest request) {
		Integer riskStateTraceId = RequestUtil.getPositiveInteger(request, "riskStateTraceId", null);
		Integer planRiskId = RequestUtil.getPositiveInteger(request, "planRiskId", null);
		Integer state = RequestUtil.getPositiveInteger(request, "state", null);
		String description = RequestUtil.getString(request, "description", null);
		
		if(riskStateTraceId == null) {
			Map<String, Object> msgMap = riskStateTraceService.addRiskStateTrace(planRiskId, state, description);
			return (String) msgMap.get("msg");
		} else {
			RiskStateTrace riskStateTrace = new RiskStateTrace();
			riskStateTrace.setId(riskStateTraceId);
			riskStateTrace.setPlanRiskId(planRiskId);
			riskStateTrace.setState(state);
			riskStateTrace.setDescription(description);
			Map<String, Object> msgMap = riskStateTraceService.updateRiskStateTrace(riskStateTrace);
			return (String) msgMap.get("msg");
		}
	}
	
	@RequestMapping(path = {"/deleteRiskStateTraceById" })
	@ResponseBody
	public String deleteProjectById(@RequestParam("riskStateTraceId") Integer riskStateTraceId) {
		riskStateTraceService.deleteRiskStateTrace(riskStateTraceId);
		return "删除成功！";
	}
}
