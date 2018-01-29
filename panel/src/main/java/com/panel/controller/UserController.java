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

import com.panel.dto.UserDto;
import com.panel.entity.Project;
import com.panel.entity.User;
import com.panel.response.ResponseData;
import com.panel.service.UserServiceImpl;

@RestController
@RequestMapping("/panel")
public class UserController {
	@Autowired
	private UserServiceImpl userService;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseData allUsers() {
		ResponseData responseData = null;
		try {
			List<User> list = (List<User>) userService.findAllUser();
			if (!list.isEmpty()) {
				responseData = new ResponseData(HttpStatus.OK.value(), "Success", list);

			} else {
				List<String> errorList = new ArrayList<>();
				errorList.add("Currently, there is no user available");
				responseData = new ResponseData(HttpStatus.NOT_FOUND.value(), "Not Found", errorList);

			}

		} catch (Exception e) {
			List<String> errorList = new ArrayList<>();
			errorList.add("Some temporary error occured " + e.getMessage().toString());
			return new ResponseData(HttpStatus.NOT_FOUND.value(), "Not Found", errorList);
		}
		return responseData;
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public ResponseData getUserById(@PathVariable("id") Long id) {
		ResponseData responseData = null;
		try {
			User user = userService.findUserById(id);
			if (user != null) {
				responseData = new ResponseData(HttpStatus.OK.value(), "Success", user);
				return responseData;
			} else {
				responseData = new ResponseData(HttpStatus.NOT_FOUND.value(), "Not Found",
						"Requested user not available");
			}
		} catch (Exception e) {
			return new ResponseData(HttpStatus.NOT_FOUND.value(), "Not Found",
					"Requested user not available " + e.getMessage().toString());
		}
		return responseData;
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseData addUser(@Valid @RequestBody UserDto userDto) {
		ResponseData responseData = null;
		try {
			User user = userService.addUser(userDto);
			responseData = new ResponseData(HttpStatus.CREATED.value(), "User added", user);
		} catch (Exception e) {
			return new ResponseData(HttpStatus.BAD_REQUEST.value(), "Invalid User",
					"You have used invalid user " + e.getMessage().toString());
		}
		return responseData;
	}

	@RequestMapping(value = "/users", method = RequestMethod.PUT)
	public ResponseData updateUser(@Valid @RequestBody UserDto userDto) {

		ResponseData responseData = null;
		try {
			Boolean exist = userService.exist(userDto.getUserId());
			User user = userService.findUserById(userDto.getUserId());
			if (exist) {
				user = userService.updateUser(userDto);
				responseData = new ResponseData(HttpStatus.ACCEPTED.value(), "User Updated", user);

			} else {
				user = userService.addUser(userDto);
				responseData = new ResponseData(HttpStatus.CREATED.value(), "User added", user);

			}
		} catch (Exception e) {
			return new ResponseData(HttpStatus.BAD_REQUEST.value(), "Invalid User",
					"You have used invalid user " + e.getMessage().toString());
		}
		return responseData;
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public ResponseData deleteUser(@PathVariable("id") Long id) {
		ResponseData responseData = null;
		try {
			Boolean exist = userService.exist(id);
			User user = userService.findUserById(id);
			if (exist) {

				userService.deleteUser(id);
				responseData = new ResponseData(HttpStatus.ACCEPTED.value(), "User deleted", user);

			} else {

				responseData = new ResponseData(HttpStatus.NOT_FOUND.value(), "Not Found",
						"Requested user not available");

			}

		} catch (Exception e) {
			return new ResponseData(HttpStatus.NOT_FOUND.value(), "Not Found",
					"Requested user not available " + e.getMessage().toString());
		}
		return responseData;
	}

}