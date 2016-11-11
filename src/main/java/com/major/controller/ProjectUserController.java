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
import com.major.service.UserService;

import tools.RequestUtil;

@Controller
public class ProjectUserController {
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(path = {"/projectUserList" })
	public String projectUserList(Model model, HttpSession session, HttpServletRequest request) {
		Integer projectId = RequestUtil.getPositiveInteger(request, "projectId", null);
		if(projectId == null) {
			model.addAttribute("error", "invalid parameter");
			return "error";
		}
		Project project = projectService.getProject(projectId);
		model.addAttribute("project", project);
		
		List<User> userList = userService.getByProjectId(projectId);
		model.addAttribute("userList", userList);
		model.addAttribute("user", (User) session.getAttribute("user"));
		
		return "projectUserList";
	}
	
	@RequestMapping(path = {"/getUsersExcludedProject" })
	@ResponseBody
	public List<User> getUsersExcludedProject(HttpServletRequest request) {
		Integer projectId = RequestUtil.getPositiveInteger(request, "projectId", null);
		return projectService.getUserNotIn(projectId);
	}
	
	@RequestMapping(path = {"/deleteUserByProjectUserId" })
	@ResponseBody
	public String deleteProjectById(@RequestParam("projectId") Integer projectId, 
			@RequestParam("userId") Integer userId) {
		projectService.deleteProjectUser(projectId, userId);
		return "删除成功！";
	}
	
	@RequestMapping(path = {"/addProjectUser" })
	@ResponseBody
	public String addProjectUser(@RequestParam("projectId") Integer projectId, 
			@RequestParam("userId") Integer userId) {
		Map<String, Object> msgMap = projectService.addProjectUser(projectId, userId);
		
		return (String) msgMap.get("msg");
	}
}
