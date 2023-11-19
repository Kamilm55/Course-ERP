package com.kamil.courseerpbackend;

import com.kamil.courseerpbackend.model.entity.Role;
import com.kamil.courseerpbackend.model.entity.User;
import com.kamil.courseerpbackend.model.enums.UserStatus;
import com.kamil.courseerpbackend.model.properties.security.SecurityProperties;
import com.kamil.courseerpbackend.repository.RoleRepository;
import com.kamil.courseerpbackend.repository.UserRepository;
import com.kamil.courseerpbackend.service.security.AccessTokenManager;
import com.kamil.courseerpbackend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class CourseErpBackendApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(CourseErpBackendApplication.class, args);
	}


	private final UserService userService;

	@Override
	public void run(String... args) throws Exception {
//		User user = User.builder()
//				.name("Samir")
//				.surname("Memmedov")
//				.phone_number("5556955")
//				.email("kamilmmdov2@gmail.com")
//				.password("sdad") // used password encoder in insert method in service
//				.roleId(8L)
//				.status(UserStatus.ACTIVE)
//				.build();

//		userService.insertUser(user);

//		System.out.println(userService.getUserByEmail("kamilmmdov432@gmail.com"));

//		Role role1 = Role.builder()
//				.name("NewRole")
//				.description("sdasdkal")
//				.build();
//
//
//
//		roleRepository.save(role1);



//
//		user.setId(1L);
//


//		userRepository.save(user);



	}
	}