package com.kamil.courseerpbackend;

import com.kamil.courseerpbackend.model.entity.User;
import com.kamil.courseerpbackend.model.enums.UserStatus;
import com.kamil.courseerpbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseErpBackendApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(CourseErpBackendApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;
	@Override
	public void run(String... args) throws Exception {
		User user = User.builder()
				.name("Kamil")
				.surname("Memmedov")
				.phone_number("558397202")
				.email("kamilmmdov2@gmail.com")
				.password("sdad")
				.roleId(5L)
				.status(UserStatus.ACTIVE)
				.build();

		user.setId(1L);


		System.out.println(user);

		System.out.println(user.getId());
		userRepository.save(user);
		System.out.println(user.getId());
	}
}
