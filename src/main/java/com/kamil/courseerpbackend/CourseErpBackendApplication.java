package com.kamil.courseerpbackend;

import com.kamil.courseerpbackend.model.entity.Course;
import com.kamil.courseerpbackend.model.mapper.CourseEntityMapper;
import com.kamil.courseerpbackend.model.payload.auth.register.RegisterPayload;
import com.kamil.courseerpbackend.service.otp.OtpService;
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


	private final OtpService otpService;

	@Override
	public void run(String... args) throws Exception {
//	otpService.send();


//		RegisterPayload payload = new RegisterPayload();
//		payload.setCourseName("new course");
//		payload.setAddress("adress 5");
//
//		Course course = CourseEntityMapper.INSTANCE.fromRegisterPayloadToCourse(payload);
//
//		System.out.println(course);

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