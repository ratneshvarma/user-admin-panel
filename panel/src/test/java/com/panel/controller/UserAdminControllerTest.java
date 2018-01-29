package com.panel.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
import com.panel.dto.ProjectDto;
import com.panel.entity.Project;
import com.panel.response.ResponseData;
import com.panel.service.ProjectServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(UserAdminController.class)
public class UserAdminControllerTest {
	@Autowired
	private MockMvc mvc;
	@MockBean
	private ProjectServiceImpl projectService;

	@Test
	public void allProjects() throws Exception {
		List<Project> projects = new ArrayList<>();
		projects.add(new Project());
		String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSYXRuZXNoIiwidXNlcklkIjoiMSIsInVzZXJSb2xlIjoiQWRtaW4ifQ.U271rualhmWdNtliTcE7jIQCKfOa0Hxxlm_ypjIAuNeHZ2USKwLStGuKcqsmYrJFBXXeEIRKsL0w5Iz1Jev2RA";
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", "Token " + token);
		when(projectService.findAllProject()).thenReturn(projects);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/projects").headers(httpHeaders)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		System.out.println("=================code=" + result.getResponse().getStatus());
		assertEquals(result.getResponse().getStatus(), 200);
		assertEquals(result.getResponse().getContentType(), "application/json;charset=UTF-8");

		when(projectService.findAllProject()).thenReturn(new ArrayList<>());
		requestBuilder = MockMvcRequestBuilders.get("/admin/projects").headers(httpHeaders)
				.accept(MediaType.APPLICATION_JSON);
		result = mvc.perform(requestBuilder).andReturn();
		Gson gson = new Gson();
		ResponseData responseData = gson.fromJson(result.getResponse().getContentAsString(), ResponseData.class);
		assertEquals(responseData.getCode().intValue(), 404);
		System.out.println("=================code2=" + result.getResponse().getStatus());
		assertEquals(result.getResponse().getContentType(), "application/json;charset=UTF-8");

	}

	@Test
	public void findProjectById() throws Exception {
		String id = "1";
		String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSYXRuZXNoIiwidXNlcklkIjoiMSIsInVzZXJSb2xlIjoiQWRtaW4ifQ.U271rualhmWdNtliTcE7jIQCKfOa0Hxxlm_ypjIAuNeHZ2USKwLStGuKcqsmYrJFBXXeEIRKsL0w5Iz1Jev2RA";
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", "Token " + token);
		when(projectService.findProjectById(anyLong())).thenReturn(new Project());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/projects/" + id).headers(httpHeaders)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		System.out.println("=================code3=" + result.getResponse().getStatus());
		assertEquals(result.getResponse().getStatus(), 200);
		assertEquals(result.getResponse().getContentType(), "application/json;charset=UTF-8");

		when(projectService.findProjectById(anyLong())).thenReturn(null);
		requestBuilder = MockMvcRequestBuilders.get("/admin/projects/" + id).headers(httpHeaders)
				.accept(MediaType.APPLICATION_JSON);
		result = mvc.perform(requestBuilder).andReturn();
		Gson gson = new Gson();
		ResponseData responseData = gson.fromJson(result.getResponse().getContentAsString(), ResponseData.class);
		assertEquals(responseData.getCode().intValue(), 404);
		System.out.println("=================code4=" + result.getResponse().getStatus());
		assertEquals(result.getResponse().getContentType(), "application/json;charset=UTF-8");

	}

	@Test
	public void addProject() throws Exception {
		String sourceContent = "{" + "\"projectTitle\":\"YYZ project\"," + "\"projectDescription\":\"YYZ desc\"" + "}";
		String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSYXRuZXNoIiwidXNlcklkIjoiMSIsInVzZXJSb2xlIjoiQWRtaW4ifQ.U271rualhmWdNtliTcE7jIQCKfOa0Hxxlm_ypjIAuNeHZ2USKwLStGuKcqsmYrJFBXXeEIRKsL0w5Iz1Jev2RA";
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", "Token " + token);
		when(projectService.addProject(new ProjectDto())).thenReturn(new Project());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/projects").headers(httpHeaders)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(sourceContent);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		Gson gson = new Gson();
		ResponseData responseData = gson.fromJson(result.getResponse().getContentAsString(), ResponseData.class);
		assertEquals(responseData.getCode().intValue(), 201);
		assertEquals(result.getResponse().getContentType(), "application/json;charset=UTF-8");

	}

	@Test
	public void updateProject() throws Exception {
		String sourceContent = "{" + "            \"projectId\": 4," + "            \"projectTitle\": \"KKK project\","
				+ "            \"projectDescription\": \"KK desc\"" + "        }";
		String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSYXRuZXNoIiwidXNlcklkIjoiMSIsInVzZXJSb2xlIjoiQWRtaW4ifQ.U271rualhmWdNtliTcE7jIQCKfOa0Hxxlm_ypjIAuNeHZ2USKwLStGuKcqsmYrJFBXXeEIRKsL0w5Iz1Jev2RA";
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", "Token " + token);
		when(projectService.updateProject(new ProjectDto())).thenReturn(new Project());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/admin/projects").headers(httpHeaders)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(sourceContent);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		Gson gson = new Gson();
		ResponseData responseData = gson.fromJson(result.getResponse().getContentAsString(), ResponseData.class);
		System.out.println("=================code4=" + result.getClass());
		assertEquals(responseData.getCode().intValue(), 202);
		assertEquals(result.getResponse().getContentType(), "application/json;charset=UTF-8");

	}

	@Test
	public void deleteProject() throws Exception {
		String id = "2";
		String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSYXRuZXNoIiwidXNlcklkIjoiMSIsInVzZXJSb2xlIjoiQWRtaW4ifQ.U271rualhmWdNtliTcE7jIQCKfOa0Hxxlm_ypjIAuNeHZ2USKwLStGuKcqsmYrJFBXXeEIRKsL0w5Iz1Jev2RA";
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", "Token " + token);
		when(projectService.isExistProject(anyLong())).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/admin/projects/" + id).headers(httpHeaders)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mvc.perform(requestBuilder).andReturn();
		Gson gson = new Gson();
		ResponseData responseData = gson.fromJson(result.getResponse().getContentAsString(), ResponseData.class);
		assertEquals(responseData.getCode().intValue(), 202);
		System.out.println("=================code6=" + responseData.getCode().intValue());
		assertEquals(result.getResponse().getContentType(), "application/json;charset=UTF-8");

		when(projectService.isExistProject(anyLong())).thenReturn(false);
		requestBuilder = MockMvcRequestBuilders.delete("/admin/projects/" + id).headers(httpHeaders)
				.accept(MediaType.APPLICATION_JSON);

		result = mvc.perform(requestBuilder).andReturn();
		responseData = gson.fromJson(result.getResponse().getContentAsString(), ResponseData.class);
		assertEquals(responseData.getCode().intValue(), 404);
		System.out.println("=================code7=" + responseData.getCode().intValue());
		assertEquals(result.getResponse().getContentType(), "application/json;charset=UTF-8");

	}

}
