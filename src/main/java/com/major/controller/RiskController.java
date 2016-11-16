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

import com.major.model.Risk;
import com.major.model.User;
import com.major.service.RiskService;

import tools.RequestUtil;

@Controller
public class RiskController {
	
	@Autowired
	RiskService riskService;
	
	@RequestMapping(value = { "/riskList" })
	public String addRisk(Model model, HttpSession session){
		model.addAttribute("user", (User) session.getAttribute("user"));
		List<Risk> riskList = riskService.getAllRisks();
		
		model.addAttribute("riskList", riskList);
		
		return "riskList";	
	}
	
	@RequestMapping(value = { "/getRiskById" })
	@ResponseBody
	public Risk getRiskById(@RequestParam("riskId") Integer riskId){
		return riskService.getRisk(riskId);
	}

	@RequestMapping(value = { "/deleteRiskById" })
	@ResponseBody
	public String deleteRiskById(@RequestParam("riskId") Integer riskId){
		riskService.deleteRisk(riskId);
		return "删除成功！";
	}
	
	@RequestMapping(value = { "/editRisk" })
	@ResponseBody
	public String editPlanRisk(HttpServletRequest request){
		Integer riskId = RequestUtil.getPositiveInteger(request, "riskId", null);
		String type = RequestUtil.getString(request, "type", null);
		String content = RequestUtil.getString(request, "content", null);
		
		Risk risk = new Risk();
		risk.setType(type);
		risk.setContent(content);
		
		if(riskId == null){
			Map<String, Object> msg = riskService.addRisk(risk);
			return (String) msg.get("msg");
		}else{
			risk.setId(riskId);
			Map<String, Object> msg = riskService.updateRisk(risk);
			return (String) msg.get("msg");
		}
		
	}
	
}
