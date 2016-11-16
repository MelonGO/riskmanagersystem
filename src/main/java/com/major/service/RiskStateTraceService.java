package com.major.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.major.dao.PlanRiskDAO;
import com.major.dao.RiskStateTraceDAO;
import com.major.model.PlanRisk;
import com.major.model.RiskStateTrace;

@Service
public class RiskStateTraceService {
	@Autowired
	private RiskStateTraceDAO riskStateTraceDao;
	@Autowired
	private PlanRiskDAO  planRiskDao;
	public RiskStateTrace getRiskStateTrace(int id) {
		return riskStateTraceDao.selectById(id);
	}
	public List<RiskStateTrace> getByPlanRiskId(int planRiskId) {
		return riskStateTraceDao.getByPlanRiskId(planRiskId);
	}
	public Map<String, Object> addRiskStateTrace(int planRiskId, int state, String description) {
		Map<String, Object> msgMap = new HashMap<>();
		RiskStateTrace riskStateTraceNew = new RiskStateTrace();
		riskStateTraceNew.setPlanRiskId(planRiskId);
		riskStateTraceNew.setState(state);
		riskStateTraceNew.setDescription(description);
		PlanRisk pr=planRiskDao.selectById(planRiskId);
		pr.setState(state);
		planRiskDao.updatePlanRisk(pr);
	
		riskStateTraceDao.addRiskStateTrace(riskStateTraceNew);
		msgMap.put("msg", "添加成功!");
		return msgMap;
	}
	
	public List<RiskStateTrace> getAllRiskStateTraces() {
		return riskStateTraceDao.selectAll();
	}
	
	public void deleteRiskStateTrace(int id){
		riskStateTraceDao.deleteById(id);
	}
	public Map<String, Object> updateRiskStateTrace(
			RiskStateTrace riskStateTrace) {
		Map<String, Object> msgMap = new HashMap<>();
		riskStateTraceDao.updateRiskStateTrace(riskStateTrace);
		msgMap.put("msg", "修改成功!");
		return msgMap;
	}
}
