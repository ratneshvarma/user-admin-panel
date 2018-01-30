package com.panel.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.panel.dto.UserDto;
import com.panel.entity.User;
import com.panel.repository.IUserRepository;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

	@TestConfiguration
	static class UserServiceImplTestContentConfiguration {
		@Bean
		public IUserService userService() {
			return new UserServiceImpl();
		}
	}

	@Autowired
	private IUserService userService;
	@MockBean
	private IUserRepository userRepository;

//	@Before
//	public void setUp() {
//		User user = new User();
//		user.setUserId(1L);
//		user.setFirstName("Ratnesh");
//		user.setLastName("Varma");
//		user.setUserRole("admin");
//		user.setMobile("1111");
//		when(userRepository.findOne(user.getUserId())).thenReturn(user);
//	}

	@Test
	public void getUserById() {
		User user = new User();
		user.setUserId(2L);
		user.setFirstName("Jay");
		user.setLastName("dev");
		user.setUserRole("admin");
		user.setMobile("5555");
		when(userRepository.findOne(user.getUserId())).thenReturn(user);
		User availableUser = userService.findUserById(user.getUserId());
		// System.out.println("saved====="+availableUser);
		assertThat(availableUser.getFirstName()).isEqualTo(user.getFirstName());

	}
	@Test
	public void getAllUsers() {
		User user = new User();
		user.setUserId(2L);
		user.setFirstName("Jay");
		user.setLastName("dev");
		user.setUserRole("admin");
		user.setMobile("5555");
		List<User> userList = new ArrayList();
		userList.add(user);
		when(userRepository.findAll()).thenReturn(userList);
		List<User> users = userService.findAllUser();		
//		System.out.println("saved====="+userList.size()+"====="+users.size());
		assertEquals(userList.size(), users.size());

	}
	@Test
	public void addUser() {
		UserDto userDto = new UserDto();
		//user.setUserId(2L);
		userDto.setFirstName("Jay");
		userDto.setLastName("dev");
		userDto.setUserRole("admin");
		userDto.setMobile("5555");
		//when(userRepository.findOne(user.getUserId())).thenReturn(user);
		User availableUser = userService.addUser(userDto);
		System.out.println("saved====="+availableUser);
		//assertThat(availableUser.getFirstName()).isEqualTo(userDto.getFirstName());

	}
}
