package com.wallet.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
public class UserControllerTest {
	
	@MockBean
	UserService service;	
	
	@Autowired
	MockMvc mvc;
	
	private static final Long ID = 1L;
	private static final String EMAIL = "gmdust@hotmail.com";
	private static final String NAME = "Carlos";
	private static final String PASSWORD = "123456";

	private static final String URL = "/user";
	
	@Test
	public void testSave() throws Exception {
		BDDMockito.given(service.save(Mockito.any(User.class))).willReturn(getMockeUser());
		mvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayLoad(ID, EMAIL, NAME, PASSWORD))
		.contentType(MediaType.APPLICATION_JSON)		
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.data.id").value(ID))
		.andExpect(jsonPath("$.data.email").value(EMAIL))
		.andExpect(jsonPath("$.data.name").value(NAME))
		.andExpect(jsonPath("$.data.password").value(PASSWORD));

	}	

	@Test
	public void testSaveInvalidUser() throws JsonProcessingException, Exception {
		
		mvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayLoad(ID, "Email", NAME, PASSWORD))
				.contentType(MediaType.APPLICATION_JSON)		
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.erro[0]").value("Email invalido"));
			

	}
	
	private String getJsonPayLoad(Long id, String email, String name, String password) throws JsonProcessingException {
		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setEmail(email);
		dto.setName(name);
		dto.setPassword(password);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValueAsString(dto);
		
		return null;
	}

	@Before
	public User getMockeUser() {
		User u = new User();
		u.setId(ID);
		u.setEmail(EMAIL);
		u.setName(NAME);
		u.setPassword(PASSWORD);

		return u;
	}
	
	
	
}
