package com.major.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.major.model.Project;
import com.major.model.User;
import com.major.service.ProjectService;

import tools.RequestUtil;

@Controller
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	@RequestMapping(path = {"/projectList" })
	public String projectList(Model model, HttpSession session) {
		List<Project> projectList = projectService.getAllProjects();
		model.addAttribute("projectList", projectList);
		model.addAttribute("user", (User) session.getAttribute("user"));
		
		return "projectList";
	}
	
	@RequestMapping(path = {"/getProjectById" })
	@ResponseBody
	public Project getProjectById(@RequestParam("projectId") Integer projectId) {
		return projectService.getProject(projectId);
	}
	
	@RequestMapping(path = {"/editProject" })
	@ResponseBody
	public String editProject(@RequestParam("name") String name, HttpServletRequest request) {
		Integer projectId = RequestUtil.getPositiveInteger(request, "projectId", null);
		if(projectId == null) {
			Map<String, Object> msgMap = projectService.addProject(name);
			return (String) msgMap.get("msg");
		} else {
			Project newProject = new Project();
			newProject.setId(projectId);
			newProject.setName(name);
			Map<String, Object> msgMap = projectService.updateProject(newProject);
			return (String) msgMap.get("msg");
		}
	}
	
	@RequestMapping(path = {"/deleteProjectById" })
	@ResponseBody
	public String deleteProjectById(@RequestParam("projectId") Integer projectId) {
		projectService.deleteProject(projectId);
		return "删除成功！";
	}
}
