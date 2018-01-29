package com.panel.controller;

import java.util.ArrayList;
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
		ResponseData responseData = null;
		try {
			List<Project> list = (List<Project>) projectService.findAllProject();
			if (!list.isEmpty()) {
				responseData = new ResponseData(HttpStatus.OK.value(), "Success", list);
			} else {
				List<String> errorList = new ArrayList<>();
				errorList.add("Currently, there is no project available");
				responseData = new ResponseData(HttpStatus.NOT_FOUND.value(), "Not Found", errorList);

			}
		} catch (Exception e) {
			List<String> errorList = new ArrayList<>();
			errorList.add("Some temporary error occured " + e.getMessage().toString());
			return new ResponseData(HttpStatus.NOT_FOUND.value(), "Not Found", errorList);
		}

		return responseData;

	}

	@RequestMapping(value = "/projects/{id}", method = RequestMethod.GET)
	public ResponseData findProjectById(@PathVariable("id") Long id) {
		ResponseData responseData = null;
		try {
			Project project = projectService.findProjectById(id);
			if (project != null) {
				responseData = new ResponseData(HttpStatus.OK.value(), "Success", project);

			} else {
				responseData = new ResponseData(HttpStatus.NOT_FOUND.value(), "Not Found",
						"Requested project not available");

			}

		} catch (Exception e) {
			return new ResponseData(HttpStatus.NOT_FOUND.value(), "Not Found",
					"Requested project not available " + e.getMessage().toString());
		}
		return responseData;

	}

	@RequestMapping(value = "/projects", method = RequestMethod.POST)
	public ResponseData addProject(@Valid @RequestBody ProjectDto projectDto) {
		ResponseData responseData = null;
		try {
			Project project = projectService.addProject(projectDto);
			responseData = new ResponseData(HttpStatus.CREATED.value(), "Project added", project);
		} catch (Exception e) {
			return new ResponseData(HttpStatus.BAD_REQUEST.value(), "Invalid Project",
					"You have used invalid project " + e.getMessage().toString());
		}
		return responseData;

	}

	@RequestMapping(value = "/projects", method = RequestMethod.PUT)
	public ResponseData updateProject(@Valid @RequestBody ProjectDto projectDto) {
		ResponseData responseData = null;
		try {
			Project project = projectService.updateProject(projectDto);
			responseData = new ResponseData(HttpStatus.ACCEPTED.value(), "Project Updated", project);
		} catch (Exception e) {
			return new ResponseData(HttpStatus.BAD_REQUEST.value(), "Invalid Project",
					"You have used invalid project " + e.getMessage().toString());
		}
		return responseData;
	}

	@RequestMapping(value = "/projects/{id}", method = RequestMethod.DELETE)
	public ResponseData deleteProject(@PathVariable("id") Long id) {
		ResponseData responseData = null;
		try {
			Project project = projectService.findProjectById(id);
			Boolean isExist = projectService.isExistProject(id);
			if (isExist) {
				projectService.deleteProject(id);
				responseData = new ResponseData(HttpStatus.ACCEPTED.value(), "Project Deleted", project);
			} else {
				responseData = new ResponseData(HttpStatus.NOT_FOUND.value(), "Not Found",
						"Requested project not available");
			}

		} catch (Exception e) {
			return new ResponseData(HttpStatus.NOT_FOUND.value(), "Not Found",
					"Requested project not available " + e.getMessage().toString());
		}
		return responseData;

	}

}
