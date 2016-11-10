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

import com.major.model.Project;
import com.major.model.Risk;
import com.major.model.User;
import com.major.model.ViewObject;
import com.major.service.ProjectService;
import com.major.service.RiskService;
import com.major.service.UserService;

import tools.RequestUtil;

@Controller
public class RiskController {
	@Autowired
	RiskService riskService;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = { "/riskList" })
	public String riskList(Model model, @RequestParam("projectId") Integer projectId, HttpSession session){
		model.addAttribute("user", (User) session.getAttribute("user"));
		List<Risk> riskList = riskService.getByProjectId(projectId);
		Project project = projectService.getProject(projectId);
		List<User> userList = userService.getAll();
		
		List<ViewObject> vos = new ArrayList<>();
		for(Risk r : riskList) {
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
		model.addAttribute("project", project);
		return "riskList";
	}
	
	@RequestMapping(value = { "/getByRiskId" })
	@ResponseBody
	public Risk getByRiskId(@RequestParam("riskId") Integer riskId){
		return riskService.getRisk(riskId);
	}
	
	@RequestMapping(value = { "/deleteRiskById" })
	@ResponseBody
	public String deleteRisk(@RequestParam("riskId") String riskId){
		riskService.deleteRisk(Integer.parseInt(riskId));
		
		return "删除成功！";
	}
	
	@RequestMapping(value = { "/editRisk" })
	@ResponseBody
	public String addRisk(@RequestParam("projectId") Integer projectId,
			@RequestParam("type") String type,
			@RequestParam("content") String content,
			@RequestParam("probability") String probability,
			@RequestParam("influence") String influence,
			@RequestParam("triggerOrThreshold") String triggerOrThreshold,
			@RequestParam("submitter") Integer submitter,
			@RequestParam("tracer") Integer tracer,
			HttpServletRequest request
			){
		Integer riskId = RequestUtil.getPositiveInteger(request, "riskId", null);
		if(riskId == null) {
			Map<String, Object> msg = riskService.addRisk(projectId, type, content, probability, influence,
				triggerOrThreshold, submitter, tracer);
			return (String) msg.get("msg");
		} else {
			Risk risk = new Risk();
			risk.setId(riskId);
			risk.setContent(content);
			risk.setInfluence(influence);
			risk.setProbability(probability);
			risk.setProjectId(projectId);
			risk.setSubmitter(submitter);
			risk.setTracer(tracer);
			risk.setTriggerOrThreshold(triggerOrThreshold);
			risk.setType(type);
			Map<String, Object> msg = riskService.updateRisk(risk);
			return (String) msg.get("msg");
		}
		
	}
	
}
