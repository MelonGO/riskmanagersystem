package com.major.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.major.dao.ProjectDAO;
import com.major.model.Project;

@Service
public class ProjectService {
	@Autowired
	private ProjectDAO projectDao;
	public Project getProject(int id) {
		return projectDao.selectById(id);
	}

	public Map<String, Object> addProject(
			String name) {
		Map<String, Object> msgMap = new HashMap<>();
		Project projectNew = new Project();
		projectNew.setName(name);
	
		projectDao.addProject(projectNew);
		msgMap.put("msg", "添加成功!");
		return msgMap;
	}
	
	public List<Project> getAllProjects() {
		return projectDao.selectAll();
	}
	
	public void deleteProject(int id){
		projectDao.deleteById(id);
	}
	public Map<String, Object> updateProject(
			Project project) {
		Map<String, Object> msgMap = new HashMap<>();
		projectDao.updateProject(project);
		msgMap.put("msg", "修改成功!");
		return msgMap;
	}
}
