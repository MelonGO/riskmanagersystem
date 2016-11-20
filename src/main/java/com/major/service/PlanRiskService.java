package com.major.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.major.dao.PlanRiskDAO;
import com.major.model.PlanRisk;
import com.major.model.RiskNum;
@Service
public class PlanRiskService {
		@Autowired
		private PlanRiskDAO planRiskDAO;
		public PlanRisk getPlanRisk(int id) {
			return planRiskDAO.selectById(id);
		}
		public List<PlanRisk> getByPlanId(int planId) {
			return planRiskDAO.getByPlanId(planId);
		}

		public Map<String, Object> addPlanRisk(PlanRisk planRiskNew) {
			Map<String, Object> msgMap = new HashMap<>();
			
			planRiskDAO.addPlanRisk(planRiskNew);
			msgMap.put("msg", "添加成功!");
			return msgMap;
			
		}
		
		public List<PlanRisk> getAllPlanRisks() {
			return planRiskDAO.selectAll();
		}
		
		public void deletePlanRisk(int id){
			planRiskDAO.deleteById(id);
		}
		public Map<String, Object> updatePlanRisk(
				PlanRisk planRisk) {
			Map<String, Object> msgMap = new HashMap<>();
			planRiskDAO.updatePlanRisk(planRisk);
			msgMap.put("msg", "修改成功!");
			return msgMap;
		}
		public List<RiskNum> getMostRecognized(String startTime,String endTime){
			return planRiskDAO.getMostRecognized( startTime, endTime);
		}
		public List<RiskNum> getMostProblems(String startTime,String endTime){
			return planRiskDAO.getMostProblems( startTime, endTime);
		}
		
}
