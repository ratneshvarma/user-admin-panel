package com.panel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panel.dto.ProjectDto;
import com.panel.entity.Project;
import com.panel.repository.IProjectRepository;

@Service
public class ProjectServiceImpl implements IProjectService {

	@Autowired
	private IProjectRepository projectRepository;

	@Override
	public Project findProjectById(Long id) {
		return projectRepository.findOne(id);
	}

	@Override
	public List<Project> findAllProject() {
		return projectRepository.findAll();
	}

	@Override
	public Project addProject(ProjectDto projectDto) {
		Project project = new Project();
		project.setProjectTitle(projectDto.getProjectTitle());
		project.setProjectDescription(projectDto.getProjectDescription());
		return projectRepository.save(project);
	}

	@Override
	public Project updateProject(ProjectDto projectDto) {
		Project project = new Project();
		project.setProjectId(projectDto.getProjectId());
		project.setProjectTitle(projectDto.getProjectTitle());
		project.setProjectDescription(projectDto.getProjectDescription());
		return projectRepository.save(project);
	}

	@Override
	public Boolean deleteProject(Long id) {
		projectRepository.delete(id);
		return true;
	}

	@Override
	public Boolean isExistProject(Long id) {
		Boolean isExist = projectRepository.exists(id);
		return isExist;
	}

	@Override
	public Project projectAssembler(ProjectDto projectDto) {
		Project project = new Project();
		project.setProjectTitle(projectDto.getProjectTitle());
		project.setProjectDescription(projectDto.getProjectDescription());
		return project;
	}

	@Override
	public ProjectDto projectDisassembler(Project project) {
		ProjectDto projectDto = new ProjectDto();
		projectDto.setProjectTitle(project.getProjectTitle());
		projectDto.setProjectDescription(project.getProjectDescription());
		return projectDto;
	}

	@Override
	public Project projectAssemblerWithId(ProjectDto projectDto) {
		Project project = new Project();
		project.setProjectId(projectDto.getProjectId());
		project.setProjectTitle(projectDto.getProjectTitle());
		project.setProjectDescription(projectDto.getProjectDescription());
		return project;

	}

	@Override
	public ProjectDto projectDisassemblerWithId(Project project) {
		ProjectDto projectDto = new ProjectDto();
		projectDto.setProjectId(project.getProjectId());
		projectDto.setProjectTitle(project.getProjectTitle());
		projectDto.setProjectDescription(project.getProjectDescription());
		return projectDto;
	}

}
