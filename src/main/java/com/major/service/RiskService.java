package com.major.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.major.dao.RiskDAO;
import com.major.model.Risk;

@Service
public class RiskService {
	@Autowired
	private RiskDAO riskDao;
	
	
	public Risk getRisk(int id) {
		return riskDao.selectById(id);
	}

	public Map<String, Object> addRisk(String name, String content, String probability,
			String influence, String triggerOrThreshold, int submitter,
			Integer tracer) {
		Map<String, Object> msgMap = new HashMap<>();
		Risk riskNew = new Risk();
		riskNew.setName(name);
		riskNew.setContent(content);
		riskNew.setProbability(probability);
		riskNew.setInfluence(influence);
		riskNew.setTriggerOrThreshold(triggerOrThreshold);
		riskNew.setSubmitter(submitter);
		riskNew.setTracer(tracer);
		riskDao.addRisk(riskNew);
		msgMap.put("msg", "添加成功!");
		return msgMap;
	}
	
	public List<Risk> getAllRisks() {
		return riskDao.selectAll();
	}
	
	public void deleteRisk(int id){
		riskDao.deleteById(id);
	}
	
	
}