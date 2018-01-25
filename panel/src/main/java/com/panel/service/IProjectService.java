package com.panel.service;

import java.util.List;

import com.panel.dto.ProjectDto;
import com.panel.entity.Project;

public interface IProjectService {
	Project findProjectById(Long id);
	List<Project> findAllProject();
	Project addProject(ProjectDto projectDto);
	Project updateProject(ProjectDto projectDto);
	Boolean deleteProject(Long id);
	Boolean isExistProject(Long id);
	Project projectAssembler(ProjectDto projectDto);
	ProjectDto projectDisassembler(Project project);
	

}
