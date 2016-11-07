package com.major.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.major.model.Risk;
import com.major.model.User;
import com.major.service.RiskService;

@Controller
public class RiskController {
	@Autowired
	RiskService riskService;
	
	private HttpSession session = null;
	
	@RequestMapping(value = { "/deleteRisk/{riskId}" },  method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteRisk(Model model, @PathVariable("riskId") String riskId,
			HttpServletRequest request,
            HttpServletResponse response){
		riskService.deleteRisk(Integer.parseInt(riskId));
		
		session = request.getSession(false);
		User user = (User)session.getAttribute("user");
		model.addAttribute("user", user);
		
		List<Risk> riskList = riskService.getAllRisks();
		model.addAttribute("riskList", riskList);
		
		return "/user";
	}
	
	@RequestMapping(value = { "/addRisk" },  method = { RequestMethod.GET, RequestMethod.POST })
	public String addRisk(Model model,
			@RequestParam("name") String name,
			@RequestParam("content") String content,
			@RequestParam("probability") String probability,
			@RequestParam("influence") String influence,
			@RequestParam("triggerOrThreshold") String triggerOrThreshold,
			@RequestParam("submitter") String submitter,
			@RequestParam("stalker") String stalker,
			HttpServletRequest request,
            HttpServletResponse response){
		riskService.addRisk(name, content, probability, influence,
				triggerOrThreshold, submitter, stalker);
		
		session = request.getSession(false);
		User user = (User)session.getAttribute("user");
		model.addAttribute("user", user);
		
		List<Risk> riskList = riskService.getAllRisks();
		model.addAttribute("riskList", riskList);
		
		return "/user";
	}
	
}
