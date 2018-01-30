package com.panel.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.panel.entity.Project;

@RunWith(SpringRunner.class)
@DataJpaTest
public class IProjectRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private IProjectRepository projectRepository;
	
	@Test
	public void findProjectById() {
		Project project = new Project();
		project.setProjectTitle("Test Project");
		project.setProjectDescription("Test project description");
		
		entityManager.persistAndFlush(project);

		// System.out.println("Id======"+project.getProjectId());
		Project availableProject = projectRepository.findOne(project.getProjectId());
		assertThat(availableProject.getProjectTitle()).isEqualTo(project.getProjectTitle());
	}

	@Test
	public void findAllProjects() {
		Project project = new Project();
		project.setProjectTitle("Test Project");
		project.setProjectDescription("Test project description");
		
		entityManager.persistAndFlush(project);

		List<Project> userProject = new ArrayList();
		userProject.add(project);

		List<Project> projects = projectRepository.findAll();
		// System.out.println("Size======"+userProject.size()+"======"+projects.size());
		assertEquals(userProject.size(), projects.size());
	}

	@Test
	public void saveProject() {
		Project project = new Project();
		project.setProjectTitle("Test Project");
		project.setProjectDescription("Test project description");

		Project savedProject = projectRepository.save(project);
		// System.out.println("saved======"+savedProject.getProjectTitle()+"===id==="+project.getProjectTitle());
		assertThat(savedProject.getProjectTitle()).isEqualTo(project.getProjectTitle());
	}

	@Test
	public void deleteProject() {
		Project project = new Project();
		project.setProjectTitle("Test Project");
		project.setProjectDescription("Test project description");
		
		entityManager.persistAndFlush(project);
		// System.out.println("saved======"+ entityManager.getId(user));
		Long id = (Long) entityManager.getId(project);
		projectRepository.delete((Long) entityManager.getId(project));
		Project deletedProject = projectRepository.findOne(id);
		// System.out.println("deleted======"+deletedProject);
		assertNull(deletedProject);
	}

}
