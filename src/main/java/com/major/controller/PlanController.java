package com.major.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.major.model.Project;
import com.major.model.Risk;
import com.major.model.User;
import com.major.service.ProjectService;

import tools.RequestUtil;

public class PlanController {
	@Autowired
	PlanService planService;
	
	@Autowired 
	ProjectService projectService;
	
	@RequestMapping(path = {"/planList" })
	public String planList(Model model, @RequestParam("projectId") Integer projectId, HttpSession session) {
		
		User user = (User) session.getAttribute("user");
		Project project = projectService.getProject(projectId);
		
		
		model.addAttribute("user", user);
		model.addAttribute("project", project);
		return "planList";
	}
	
	@RequestMapping(value = { "/getByPlanId" })
	@ResponseBody
	public Risk getByPlanId(@RequestParam("planId") Integer riskId){
		//return riskService.getRisk(riskId);
	}
	
	@RequestMapping(value = { "/deletePlanById" })
	@ResponseBody
	public String deletePlan(@RequestParam("planId") String riskId){
		//riskService.deleteRisk(Integer.parseInt(riskId));
		
		return "删除成功！";
	}
	
	@RequestMapping(value = { "/editPlan" })
	@ResponseBody
	public String editPlan(HttpServletRequest request){
		Integer projectId = RequestUtil.getPositiveInteger(request, "projectId", null);
		if(projectId == null) {
		
		} else {
			
		}
		
	}
}
