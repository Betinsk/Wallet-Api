package com.wallet.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.dto.UserDTO;
import com.wallet.entities.User;
import com.wallet.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {
	
	@MockBean
	UserService service;	
	
	@Autowired
	MockMvc mvc;
	
	private static final String EMAIL = "gmdust@hotmail.com";
	private static final String NAME = "Carlos";
	private static final String PASSWORD = "123456";

	private static final String URL = "/user";
	

	public void testSave() throws Exception {
		
		mvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayLoad())
		.contentType(MediaType.APPLICATION_JSON)		
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated());
	}	
	
	private String getJsonPayLoad() throws JsonProcessingException {
		UserDTO dto = new UserDTO();
		dto.setEmail(EMAIL);
		dto.setName(NAME);
		dto.setPassword(PASSWORD);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValueAsString(dto);
		
		return null;
	}

	@Before
	public User getMockeUser() {
		User u = new User();
		u.setEmail(EMAIL);
		u.setName(NAME);
		u.setPassword(PASSWORD);

		return u;
	}
	
	
	
}
