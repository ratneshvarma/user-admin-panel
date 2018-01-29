package com.panel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panel.dto.UserDto;
import com.panel.entity.User;
import com.panel.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userRepository;

	@Override
	public User findUserById(Long id) {
		User user = userRepository.findOne(id);
		return user;
	}

	@Override
	public List<User> findAllUser() {
		List<User> user = userRepository.findAll();
		return user;
	}

	@Override
	public User addUser(UserDto userDto) {

		User user = new User();
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setMobile(userDto.getMobile());
		user.setUserRole(userDto.getUserRole());

		return userRepository.save(user);
	}

	@Override
	public User updateUser(UserDto userDto) {
		User user = new User();
		user.setUserId(userDto.getUserId());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setMobile(userDto.getMobile());
		user.setUserRole(userDto.getUserRole());
		return userRepository.save(user);
	}

	@Override
	public boolean deleteUser(Long id) {
		userRepository.delete(id);
		return true;
	}

	@Override
	public User userAssembler(UserDto userDto) {
		User user = new User();
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setMobile(userDto.getMobile());
		user.setUserRole(user.getUserRole());
		return user;
	}

	@Override
	public UserDto userDisassembler(User user) {
		UserDto userDto = new UserDto();
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setMobile(user.getMobile());
		userDto.setUserRole(user.getUserRole());
		return userDto;
	}

	@Override
	public Boolean exist(Long id) {
		Boolean isExist = userRepository.exists(id);
		return isExist;
	}

	@Override
	public User userAssemblerWithId(UserDto userDto) {
		User user = new User();
		user.setUserId(userDto.getUserId());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setMobile(userDto.getMobile());
		user.setUserRole(user.getUserRole());
		return user;
	}

	@Override
	public UserDto userDisassemblerWithId(User user) {
		UserDto userDto = new UserDto();
		userDto.setUserId(user.getUserId());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setMobile(user.getMobile());
		userDto.setUserRole(user.getUserRole());
		return userDto;
	}

}