package com.major.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.major.model.Plan;
import com.major.model.Project;
import com.major.model.User;
import com.major.service.PlanService;
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
	public Plan getByPlanId(@RequestParam("planId") Integer planId){
		return planService.getPlan(planId);
	}
	
	@RequestMapping(value = { "/deletePlanById" })
	@ResponseBody
	public String deletePlan(@RequestParam("planId") Integer planId){
		planService.deletePlan(planId);
		return "删除成功！";
	}
	
	@RequestMapping(value = { "/editPlan" })
	@ResponseBody
	public String editPlan(HttpServletRequest request){
		Integer projectId = RequestUtil.getPositiveInteger(request, "projectId", null);
		Integer planId  = RequestUtil.getPositiveInteger(request, "planId", null);
		String name = RequestUtil.getString(request, "name", null);
		if(planId == null) {
			Map<String, Object> msgMap = planService.addPlan(projectId, name);
			return (String) msgMap.get("msg");
		} else {
			Plan plan = new Plan();
			plan.setId(planId);
			plan.setProjectId(projectId);
			plan.setName(name);
			Map<String, Object> msgMap = planService.updatePlan(plan);
			return (String) msgMap.get("msg");
		}
	}
}
