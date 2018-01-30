package com.panel.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertArrayEquals;
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

import com.panel.entity.User;

@RunWith(SpringRunner.class)
@DataJpaTest
// @AutoConfigureTestDatabase(replace = NONE) // when MYSQL used but for test
// using in memory db h2database
public class IUserRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private IUserRepository userRepository;

	@Test
	public void findUserById() {
		User user = new User();
		user.setFirstName("Ratnesh");
		user.setLastName("Varma");
		user.setUserRole("admin");
		user.setMobile("999999");
		entityManager.persistAndFlush(user);

		// System.out.println("Id======"+user.getUserId());
		User availableUser = userRepository.findOne(user.getUserId());
		assertThat(availableUser.getFirstName()).isEqualTo(user.getFirstName());
	}

	@Test
	public void findAllUser() {
		User user = new User();
		user.setFirstName("Ratnesh");
		user.setLastName("Varma");
		user.setUserRole("admin");
		user.setMobile("999999");
		entityManager.persistAndFlush(user);

		List<User> userList = new ArrayList();
		userList.add(user);

		List<User> users = userRepository.findAll();
		// System.out.println("Size======"+userList.size()+"======"+users.size());
		assertEquals(userList.size(), users.size());
	}

	@Test
	public void saveUser() {
		User user = new User();
		user.setFirstName("Ratnesh");
		user.setLastName("Varma");
		user.setUserRole("admin");
		user.setMobile("999999");

		User savedUser = userRepository.save(user);
		// System.out.println("saved======"+savedUser.getFirstName()+"===id==="+savedUser.getUserId());
		assertThat(savedUser.getFirstName()).isEqualTo(user.getFirstName());
	}

	@Test
	public void deleteUser() {
		User user = new User();
		user.setFirstName("Ratnesh");
		user.setLastName("Varma");
		user.setUserRole("admin");
		user.setMobile("999999");
		entityManager.persistAndFlush(user);
		// System.out.println("saved======"+ entityManager.getId(user));
		Long id = (Long) entityManager.getId(user);
		userRepository.delete((Long) entityManager.getId(user));
		User deletedUser = userRepository.findOne(id);
		// System.out.println("deleted======"+deletedUser);
		assertNull(deletedUser);
	}
}
