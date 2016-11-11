package com.major;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import com.major.model.Risk;
import com.major.service.RiskService;
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	@Autowired
	RiskService rs1;
	@Test
	public void contextLoads() {

		Risk risk=rs1.getRisk(1);
assertEquals(1, risk.getId());
	}

}
