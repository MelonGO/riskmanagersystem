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
	
}
