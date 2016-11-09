package com.major.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.major.dao.RiskStateTraceDAO;
import com.major.model.RiskStateTrace;

@Service
public class RiskStateTraceService {
	@Autowired
	private RiskStateTraceDAO riskStateTraceDao;

	public RiskStateTrace getRiskStateTrace(int id) {
		return riskStateTraceDao.selectById(id);
	}
	public List<RiskStateTrace> getByRiskId(int riskId) {
		return riskStateTraceDao.getByRiskId(riskId);
	}
	public Map<String, Object> addRiskStateTrace(int riskId, String name, String description) {
		Map<String, Object> msgMap = new HashMap<>();
		RiskStateTrace riskStateTraceNew = new RiskStateTrace();
		riskStateTraceNew.setriskId(riskId);
		riskStateTraceNew.setName(name);
		riskStateTraceNew.setDescription(description);
	
	
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
