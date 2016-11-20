package com.major.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.major.dao.ProjectDAO;
import com.major.dao.ProjectUserDAO;
import com.major.model.Project;
import com.major.model.ProjectUser;

@Service
public class ProjectService {
	@Autowired
	private ProjectDAO projectDao;
	@Autowired
	private ProjectUserDAO projectUserDAO;
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
	public List<Project> getByUserId(int userId) {
		return projectUserDAO.getByUserId(userId);
	}
	public Map<String, Object> addProjectUser(
			int  projectId,int userId) {
		Map<String, Object> msgMap = new HashMap<>();
		ProjectUser projectUserNew = new ProjectUser();
		projectUserNew.setUserId(userId);
		projectUserNew.setProjectId(projectId);
		projectUserDAO.addProjectUser(projectUserNew);
		msgMap.put("msg", "添加成功!");
		return msgMap;
	}
	public void deleteProjectUser(int projectId,int userId){
		projectUserDAO.deleteById(projectId,userId);
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
