package com.panel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.panel.entity.Project;

@Repository
public interface IProjectRepository extends JpaRepository<Project, Long> {

}
