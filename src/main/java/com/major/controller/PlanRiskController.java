package com.major.controller;

import java.util.ArrayList;
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

import com.major.model.Plan;
import com.major.model.PlanRisk;
import com.major.model.Project;
import com.major.model.Risk;
import com.major.model.User;
import com.major.model.ViewObject;
import com.major.service.PlanRiskService;
import com.major.service.PlanService;
import com.major.service.RiskService;
import com.major.service.UserService;

import tools.RequestUtil;

@Controller
public class PlanRiskController {
	@Autowired
	RiskService riskService;
	
	@Autowired
	PlanRiskService planRiskService;
	
	@Autowired
	PlanService planService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = { "/planRiskList" })
	public String planRiskList(Model model, @RequestParam("planId") Integer planId, HttpSession session){
		model.addAttribute("user", (User) session.getAttribute("user"));
		List<PlanRisk> planRiskList = planRiskService.getByPlanId(planId);
		Plan plan = planService.getPlan(planId);
		List<User> userList = userService.getAll();
		List<Risk> riskList = riskService.getAllRisks();
		
		List<ViewObject> vos = new ArrayList<>();
		for(PlanRisk r : planRiskList) {
			ViewObject vo = new ViewObject();
			vo.set("risk", r);
			for(User u : userList) {
				if(u.getId() == r.getSubmitter()) {
					vo.set("submitter", u);
				}
				if(u.getId() == r.getTracer()) {
					vo.set("tracer", u);
				}
			}
			vos.add(vo);
		}
		model.addAttribute("riskListVOs", vos);
		model.addAttribute("plan", plan);
		model.addAttribute("riskList", riskList);
		model.addAttribute("project", (Project) session.getAttribute("project"));
		return "planRiskList";
	}
	
	@RequestMapping(value = { "/getPlanRiskById" })
	@ResponseBody
	public PlanRisk getPlanRiskById(@RequestParam("planRiskId") Integer planRiskId){
		return planRiskService.getPlanRisk(planRiskId);
	}
	
	@RequestMapping(value = { "/deletePlanRiskById" })
	@ResponseBody
	public String deletePlanRiskById(@RequestParam("planRiskId") Integer planRiskId){
		planRiskService.deletePlanRisk(planRiskId);
		return "删除成功！";
	}
	
	@RequestMapping(value = { "/editPlanRisk" })
	@ResponseBody
	public String editPlanRisk(HttpServletRequest request){
		Integer planRiskId = RequestUtil.getPositiveInteger(request, "planRiskId", null);
		Integer planId = RequestUtil.getPositiveInteger(request, "planId", null);
		Integer riskId = RequestUtil.getPositiveInteger(request, "riskId", null);
		String probability = RequestUtil.getString(request, "probability", null);
		String influence = RequestUtil.getString(request, "influence", null);
		String triggerOrThreshold = RequestUtil.getString(request, "triggerOrThreshold", null);
		Integer submitter = RequestUtil.getPositiveInteger(request, "submitter", null);
		Integer tracer = RequestUtil.getPositiveInteger(request, "tracer", null);
		
		PlanRisk planRisk = new PlanRisk();
		planRisk.setPlanId(planId);
		planRisk.setInfluence(influence);
		planRisk.setProbability(probability);
		planRisk.setSubmitter(submitter);
		planRisk.setTracer(tracer);
		planRisk.setTriggerOrThreshold(triggerOrThreshold);
		
		if(riskId != null){
			Risk risk = riskService.getRisk(riskId);
			planRisk.setRiskId(riskId);
			planRisk.setType(risk.getType());
			planRisk.setContent(risk.getContent());
		}
		
		if(planRiskId == null) { 
			Map<String, Object> msg = planRiskService.addPlanRisk(planRisk);
			return (String) msg.get("msg");
			
		} else {
			planRisk.setId(planRiskId);
			Map<String, Object> msg = planRiskService.updatePlanRisk(planRisk);
			return (String) msg.get("msg");
			
		}
		
	}
	
}
