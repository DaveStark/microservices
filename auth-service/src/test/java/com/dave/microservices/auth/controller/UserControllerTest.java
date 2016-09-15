package com.dave.microservices.auth.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dave.microservices.auth.AuthApp;
import com.dave.microservices.auth.domain.Role;
import com.dave.microservices.auth.domain.User;
import com.dave.microservices.auth.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.security.Principal;

import javax.inject.Inject;

//@SpringApplicationConfiguration(classes = AuthApp.class)  deprecated, see https://spring.io/blog/2016/04/15/testing-improvements-in-spring-boot-1-4
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes=AuthApp.class)
public class UserControllerTest {
	
	private static final ObjectMapper mapper = new ObjectMapper();
	
	@InjectMocks
	private UserController accountController;
	
	@Mock
	private UserService userService;
	
	//TODO improve this test, creating a role repository class, inject it and use it
	
	private MockMvc mockMvc;
	
	@Before
	public void setup(){
		initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
	}
	
	@Test
	public void shouldCreateNewUser() throws Exception{
		final Role role = new Role();//see TODO to improve this test
		role.setId(1);
		role.setRole("");
		final User user = new User();
		user.setUserName("user");
		user.setPassword("test");
		user.setFirstName("User");
		user.setLastName("");
		user.setRole(role);
		String json = mapper.writeValueAsString(user);
		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(json))
			.andExpect(status().isOk());
	}
	
	
	
	@Test
	public void shouldReturnCurrentUser() throws Exception {
		mockMvc.perform(get("/users/current").principal(new PrincipalImpl("test")))
				.andExpect(jsonPath("$.name").value("test"))
				.andExpect(status().isOk());
	}
	
	protected class PrincipalImpl implements Principal{
		
		String name;
		
		PrincipalImpl(String name){
			this.name = name;
		}
		
		@Override
		public String getName() {
			return name;
		}
		
	}
}
