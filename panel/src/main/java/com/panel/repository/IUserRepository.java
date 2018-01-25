package com.panel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.panel.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

}
