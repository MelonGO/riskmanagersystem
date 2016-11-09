package com.major.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.major.model.Risk;
import com.major.model.User;
import com.major.service.RiskService;

@Controller
public class RiskController {
	@Autowired
	RiskService riskService;
	
	@RequestMapping(value = { "/riskList" })
	public String riskList(Model model, @RequestParam("projectId") String projectId, HttpSession session){
		model.addAttribute("user", (User) session.getAttribute("user"));
		List<Risk> riskList = riskService.getAllRisks();
		model.addAttribute("riskList", riskList);
		return "riskList";
	}
	
	@RequestMapping(value = { "/getByRiskId" })
	@ResponseBody
	public Risk getByRiskId(@RequestParam("riskId") Integer riskId){
		return riskService.getRisk(riskId);
	}
	
	@RequestMapping(value = { "/deleteRisk" })
	@ResponseBody
	public String deleteRisk(Model model, @RequestParam("riskId") String riskId){
		riskService.deleteRisk(Integer.parseInt(riskId));
		
		return "删除成功！";
	}
	
	@RequestMapping(value = { "/addRisk" })
	@ResponseBody
	public String addRisk(Model model,
			@RequestParam("projectId") Integer projectId,
			@RequestParam("type") String type,
			@RequestParam("content") String content,
			@RequestParam("probability") String probability,
			@RequestParam("influence") String influence,
			@RequestParam("triggerOrThreshold") String triggerOrThreshold,
			@RequestParam("submitter") Integer submitter,
			@RequestParam("tracer") Integer tracer
			){
		Map<String, Object> msg = riskService.addRisk(projectId, type, content, probability, influence,
				triggerOrThreshold, submitter, tracer);
		
		return (String) msg.get("msg");
	}
	
}
