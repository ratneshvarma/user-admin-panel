package com.panel.service;

import java.util.List;
import com.panel.dto.UserDto;
import com.panel.entity.User;

public interface IUserService {
	
	User findUserById(Long id);
	List<User> findAllUser();
	
	User addUser(UserDto userDto);
	boolean deleteUser(Long id);
	User updateUser(UserDto userDto);
	User userAssembler(UserDto userDto);
	UserDto userDisassembler(User user);
	Boolean exist(Long id);
	User userAssemblerWithId(UserDto userDto);
	UserDto userDisassemblerWithId(User user);
}
