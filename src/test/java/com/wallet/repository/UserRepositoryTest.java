package com.wallet.repository;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.entities.User;



@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	UserRepository repository;
	
	@Test
	public void testSave() {
		User u = new User();
		u.setName("Keylo");
		u.setPassword("123456");
		u.SetEmail("test@teste.com");
		
		User response = repository.save(u);
		
		assertNotNull(response);
	}
}
