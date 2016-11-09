package com.major;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.major.model.Project;
import com.major.model.Risk;
import com.major.model.RiskStateTrace;
import com.major.service.ProjectService;
import com.major.service.RiskService;
import com.major.service.RiskStateTraceService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	@Autowired
	//ProjectService ps;
//	RiskStateTraceService rs;
	RiskService rs1;
	@Test
	public void contextLoads() {
	//ps.addProject("121");
//		Project project =ps.getProject(1);
//		project.setName("2b");
//		ps.updateProject(project);
//		rs.addRiskStateTrace(12, "sd", "dd");
//		RiskStateTrace rst=rs.getRiskStateTrace(1);
//		rst.setName("sdsd");
//		rs.updateRiskStateTrace(rst);
		rs1.addRisk(122,"类型", "nei", "ke", "ying", "chufa", 1, 2);
//		Risk risk=rs1.getRisk(1);
//		risk.setProjectId(12);
//		risk.setContent("binan");
//		rs1.updateRisk(risk);
	}

}
