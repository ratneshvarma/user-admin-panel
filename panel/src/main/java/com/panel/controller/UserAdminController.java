package com.panel.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.panel.dto.ProjectDto;
import com.panel.entity.Project;
import com.panel.response.ResponseData;
import com.panel.service.ProjectServiceImpl;

@RestController
@RequestMapping("/admin")
public class UserAdminController {
	@Autowired
	private ProjectServiceImpl projectService;

	@RequestMapping(value = "/projects", method = RequestMethod.GET)
	public ResponseData allProjects() {
		List<Project> list = (List<Project>) projectService.findAllProject();
		if (!list.isEmpty()) {
			ResponseData responseData = new ResponseData(HttpStatus.OK.value(), "Success", list);
			return responseData;
		} else {
			ResponseData responseData = new ResponseData(HttpStatus.NOT_FOUND.value(), "Not Found", list);
			return responseData;
		}
	}

	@RequestMapping(value = "/projects/{id}", method = RequestMethod.GET)
	public ResponseData findProjectById(@PathVariable("id") Long id) {
		Project project = projectService.findProjectById(id);
		System.out.println("Project ====: " + project);
		if (project != null) {
			ResponseData responseData = new ResponseData(HttpStatus.OK.value(), "Success", project);
			return responseData;
		} else {
			ResponseData responseData = new ResponseData(HttpStatus.NOT_FOUND.value(), "Not Found", null);
			return responseData;
		}
	}

	@RequestMapping(value = "/projects", method = RequestMethod.POST)
	public ResponseData addProject(@Valid @RequestBody ProjectDto projectDto) {
		System.out.println("test1===========");
		Project project = projectService.addProject(projectDto);
		ResponseData responseData = new ResponseData(HttpStatus.CREATED.value(), "Project added", project);
		return responseData;

	}

	@RequestMapping(value = "/projects", method = RequestMethod.PUT)
	public ResponseData updateProject(@Valid @RequestBody ProjectDto projectDto) {
		System.out.println("test4===========");
		Project project = projectService.updateProject(projectDto);
		ResponseData responseData = new ResponseData(HttpStatus.ACCEPTED.value(), "Project Updated", project);
		return responseData;
	}

	@RequestMapping(value = "/projects/{id}", method = RequestMethod.DELETE)
	public ResponseData deleteProject(@PathVariable("id") Long id) {
		Project project = projectService.findProjectById(id);
		Boolean isExist = projectService.isExistProject(id);
		ResponseData responseData = null;
		if (isExist) {
			projectService.deleteProject(id);
			responseData = new ResponseData(HttpStatus.ACCEPTED.value(), "Project Deleted", project);
		} else {
			responseData = new ResponseData(HttpStatus.NOT_FOUND.value(), "Not Found", null);
		}

		return responseData;

	}

}
