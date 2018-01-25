package com.panel.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.gson.Gson;
import com.panel.dto.UserDto;
import com.panel.entity.User;
import com.panel.response.ResponseData;
import com.panel.service.UserServiceImpl;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
	@Autowired
	private MockMvc mvc;
	@MockBean
	private UserServiceImpl userService;

//	@Test
//	public void allUsers() throws Exception {
//		String content_sc = "application/json";
//		List<User> users = new ArrayList<>();
//		users.add(new User());
////		HttpHeaders httpHeaders = new HttpHeaders();
////		httpHeaders.add("Authorization", "Token eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBYmMiLCJ1c2VySWQiOiIxIiwidXNlclJvbGUiOiJ1c2VyIn0.BHsI6LkCqJ7b2x4WNjUa4s0f4uTcAxkcOWcxV5I1RMARHEhTG04UbFgBuDCY9OU3W6peiVkbBRYdxC-f4C44qg");
//
//		when(userService.findAllUser()).thenReturn(users);
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users")
//				//.headers(httpHeaders)
//				.accept(MediaType.APPLICATION_JSON)
//				.content(content_sc);
//		
//		MvcResult result = mvc.perform(requestBuilder).andReturn();
//		assertEquals(result.getResponse().getStatus(), 200);
//		assertEquals(result.getResponse().getContentType(), "application/json;charset=UTF-8");
//		
//	when(userService.findAllUser()).thenReturn(new ArrayList<User>());
//	 requestBuilder = MockMvcRequestBuilders.get("/api/users")
//				.accept(MediaType.APPLICATION_JSON)
//				.content(content_sc);
//	result = mvc.perform(requestBuilder).andReturn();
//	Gson gson = new Gson();
//	ResponseData responseData = gson.fromJson(result.getResponse().getContentAsString(), ResponseData.class);
//	assertEquals(responseData.getCode().intValue(), 404);
//	assertEquals(result.getResponse().getContentType(), "application/json;charset=UTF-8");
//
//	}
//	
//	@Test
//	public void getUserById() throws Exception{
//		String content_sc = "application/json";
//		User user = new User();
//		String id="2";
//		
//		when(userService.findUserById(anyLong())).thenReturn(user);
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users/"+id)
//				.accept(MediaType.APPLICATION_JSON)
//				.content(content_sc);
//		MvcResult result = mvc.perform(requestBuilder).andReturn();
//		assertEquals(result.getResponse().getStatus(), 200);
//		assertEquals(result.getResponse().getContentType(), "application/json;charset=UTF-8");
//		
//		when(userService.findUserById(anyLong())).thenReturn(null);
//		 requestBuilder = MockMvcRequestBuilders.get("/api/users/"+id)
//				.accept(MediaType.APPLICATION_JSON)
//				.content(content_sc);
//		 result = mvc.perform(requestBuilder).andReturn();
//		Gson gson = new Gson();
//		ResponseData responseData = gson.fromJson(result.getResponse().getContentAsString(), ResponseData.class);
//		assertEquals(responseData.getCode().intValue(), 404);
//		assertEquals(result.getResponse().getContentType(), "application/json;charset=UTF-8");
//
//	}
//	
	@Test
	public void addUser() throws Exception{
		
//		String content_sc = "{\r\n" + 
//				"\"firstName\":\"Abc\"," + 
//				"\"userId\":1," + 
//				"\"userRole\":\"Admin\"" + 
//				"}";
	    String content_sc="{}";
		UserDto userDto = new UserDto();
		User user = userService.userAssembler(userDto);
	
		when(userService.addUser(userDto)).thenReturn(user);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/users/")
				.accept(MediaType.APPLICATION_JSON)
				.content(content_sc)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		
		Gson gson = new Gson();
		ResponseData responseData = gson.fromJson(result.getResponse().getContentAsString(), ResponseData.class);
		System.out.println("Test========="+result.getResponse().getStatus()+"======"+isJSONValid(content_sc));
		UserDto userObjet = gson.fromJson(content_sc, UserDto.class);
		System.out.println("Obbb====="+userObjet);
		if(isJSONValid(content_sc)) {
		assertEquals(responseData.getCode().intValue(), 201);
		assertEquals(result.getResponse().getContentType(), "application/json;charset=UTF-8");
		}
		else {
			assertEquals(result.getResponse().getStatus(), 400);
		}
		
//		when(userService.addUser(userDto)).thenReturn(user);
//		 requestBuilder = MockMvcRequestBuilders.post("/api/users/")
//				.accept(MediaType.APPLICATION_JSON)
//				.content(content_sc)
//				.contentType(MediaType.APPLICATION_JSON);
//		 result = mvc.perform(requestBuilder).andReturn();
//		 if(!isJSONValid(content_sc))
//		 assertEquals(result.getResponse().getStatus(), 400);
//		 
//		assertEquals(result.getResponse().getContentType(), "application/json;charset=UTF-8");
		
	}
	
//	@Test
//	public void updateUser() throws Exception{
//		
//		String content_sc = "{\r\n" + 
//				"\"firstName\":\"Abc\"," + 
//				"\"userId\":1," + 
//				"\"userRole\":\"Admin\"" + 
//				"}";
//		UserDto userDto = new UserDto();
//		User user = userService.userAssemblerWithId(userDto);
//		when(userService.updateUser(userDto) ).thenReturn(user);
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/users/")
//				.accept(MediaType.APPLICATION_JSON)
//				.content(content_sc)
//				.contentType(MediaType.APPLICATION_JSON);
//		MvcResult result = mvc.perform(requestBuilder).andReturn();
//		
//		Gson gson = new Gson();
//		ResponseData responseData = gson.fromJson(result.getResponse().getContentAsString(), ResponseData.class);
//		assertEquals(responseData.getCode().intValue(), 202);
//		assertEquals(result.getResponse().getContentType(), "application/json;charset=UTF-8");
//
//		
//		when(userService.addUser(userDto)).thenReturn(user);
//		 requestBuilder = MockMvcRequestBuilders.put("/api/users/")
//				.accept(MediaType.APPLICATION_JSON)
//				.content(content_sc)
//				.contentType(MediaType.APPLICATION_JSON);
//		 result = mvc.perform(requestBuilder).andReturn();
//		
//		responseData = gson.fromJson(result.getResponse().getContentAsString(), ResponseData.class);
//		assertEquals(responseData.getCode().intValue(), 201);
//		assertEquals(result.getResponse().getContentType(), "application/json;charset=UTF-8");
//		
//		
//	}

//	@Test
//	public void deleteUser() throws Exception{
//		String id="9";
//		
//		when(( userService.exist(anyLong())) ).thenReturn(true);
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/users/"+id)
//				.accept(MediaType.APPLICATION_JSON);
//				
//		MvcResult result = mvc.perform(requestBuilder).andReturn();
//		Gson gson = new Gson();
//		ResponseData responseData = gson.fromJson(result.getResponse().getContentAsString(), ResponseData.class);
//		assertEquals(responseData.getCode().intValue(), 202);
//		assertEquals(result.getResponse().getContentType(), "application/json;charset=UTF-8");
////		assertEquals(result.getResponse().getStatus(), 202);
////		assertEquals(result.getResponse().getContentType(), "application/json;charset=UTF-8");
////		
//		when(userService.exist(anyLong())).thenReturn(false);
//		 requestBuilder = MockMvcRequestBuilders.delete("/api/users/"+id)
//				.accept(MediaType.APPLICATION_JSON);
//		 result = mvc.perform(requestBuilder).andReturn();
//		responseData = gson.fromJson(result.getResponse().getContentAsString(), ResponseData.class);
//		assertEquals(responseData.getCode().intValue(), 404);
//		assertEquals(result.getResponse().getContentType(), "application/json;charset=UTF-8");
//
//	}
	
	public boolean isJSONValid(String test) {
	    try {
	        new JSONObject(test);
	    } catch (JSONException ex) {
	        // edited, to include @Arthur's comment
	        // e.g. in case JSONArray is valid as well...
	        try {
	            new JSONArray(test);
	        } catch (JSONException ex1) {
	            return false;
	        }
	    }
	    return true;
	}
}
