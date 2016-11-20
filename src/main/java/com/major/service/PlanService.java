package com.major.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.major.dao.PlanDAO;
import com.major.model.Plan;

@Service
public class PlanService {

	@Autowired
	private PlanDAO planDAO;
	public Plan getPlan(int id) {
		return planDAO.selectById(id);
	}
	public List<Plan> getByProjectId(int projectId) {
		return planDAO.getByProjectId(projectId);
	}

	public Map<String, Object> addPlan(int  projectId,String name) {
		Map<String, Object> msgMap = new HashMap<>();
		Plan planNew=new Plan();
		planNew.setProjectId(projectId);
		planNew.setName(name);
		planDAO.addPlanRisk(planNew);
		msgMap.put("msg", "添加成功!");
		return msgMap;
		
	}
	
	public List<Plan> getAllPlan() {
		return planDAO.selectAll();
	}
	
	public void deletePlan(int id){
		planDAO.deleteById(id);
	}
	public Map<String, Object> updatePlan(
			Plan plan) {
		Map<String, Object> msgMap = new HashMap<>();
		planDAO.updatePlan(plan);
		msgMap.put("msg", "修改成功!");
		return msgMap;
	}
	

}
