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

import com.major.model.RiskStateTrace;
import com.major.model.User;
import com.major.service.RiskStateTraceService;

import tools.RequestUtil;

@Controller
public class RiskStateTraceController {
	@Autowired
	RiskStateTraceService riskStateTraceService;
	
	@RequestMapping(path = {"/riskStateTraceList" })
	public String projectList(Model model, HttpSession session) {
		List<RiskStateTrace> riskStateTraceList = riskStateTraceService.getAllRiskStateTraces();
		model.addAttribute("riskStateTraceList", riskStateTraceList);
		model.addAttribute("user", (User) session.getAttribute("user"));
		
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
		Integer riskStateTraceId = RequestUtil.GetPositiveInteger(request, "riskStateTraceId", null);
		Integer riskId = RequestUtil.GetPositiveInteger(request, "riskId", null);
		String name = RequestUtil.GetString(request, "name", null);
		String description = RequestUtil.GetString(request, "description", null);
		
		if(riskStateTraceId == null) {
			Map<String, Object> msgMap = riskStateTraceService.addRiskStateTrace(riskId, name, description);
			return (String) msgMap.get("msg");
		} else {
			return null;
		}
	}
	
	@RequestMapping(path = {"/deleteRiskStateTraceById" })
	@ResponseBody
	public String deleteProjectById(@RequestParam("riskStateTraceId") Integer riskStateTraceId) {
		riskStateTraceService.deleteRiskStateTrace(riskStateTraceId);
		return "删除成功！";
	}
}
