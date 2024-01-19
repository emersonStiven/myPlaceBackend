package com.myplace.authorizationserver;

import com.myplace.authorizationserver.entity.Role;
import com.myplace.authorizationserver.entity.UserCredentials;
import com.myplace.authorizationserver.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootTest
class AuthorizationServerApplicationTests {
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Test
	void contextLoads() {
		UserCredentials userCredentials = UserCredentials.builder()
				.email("emersonstiven1@gmail.com")
				.roles(Set.of(Role.USER))
				.password(passwordEncoder.encode("123"))
				.build();
		userRepository.save(userCredentials);
	}

}
