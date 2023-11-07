package com.kamil.courseerpbackend;

import com.kamil.courseerpbackend.model.entity.User;
import com.kamil.courseerpbackend.model.properties.security.SecurityProperties;
import com.kamil.courseerpbackend.service.security.AccessTokenManager;
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



	private final AccessTokenManager accessTokenManager;

	@Override
	public void run(String... args) throws Exception {
//		User user = User.builder()
//				.name("Kamil")
//				.email("Kamil@gmail.commm")
//				.build();
//
//		user.setId(1L);

//		String token = accessTokenManager.generate(user);
//		System.out.println(token);
//
//		//read token
//		String email = accessTokenManager.read(token).get("email", String.class);
//
//		System.out.println(email);
//



//		KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
//		keyGenerator.initialize(2048);
//		KeyPair kp = keyGenerator.genKeyPair();
//		PublicKey publicKey = kp.getPublic();
//		PrivateKey privateKey = kp.getPrivate();
//
//		String encodedPublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
//		String encodedPrivateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());
//
//		System.out.println(convertToPublicKey(encodedPublicKey));
//
//		System.out.println();
//
//		System.out.println(convertToPrivateKey(encodedPrivateKey));

	}

//	private static String convertToPrivateKey(String key) {
//		StringBuilder result = new StringBuilder();
//		result.append("-----BEGIN PRIVATE KEY-----\n");
//		result.append(key);
//		result.append("\n-----END PRIVATE KEY-----");
//		return result.toString();
//	}
//
//	private static String convertToPublicKey(String key) {
//		StringBuilder result = new StringBuilder();
//		result.append("-----BEGIN PUBLIC KEY-----\n");
//		result.append(key);
//		result.append("\n-----END PUBLIC KEY-----");
//		return result.toString();
//

//		Role role1 = Role.builder()
//				.name("NewRole")
//				.description("sdasdkal")
////				.ownerId(1L)
//				.build();
//
//		roleRepository.save(role1);
//
//		User user = User.builder()
//				.name("Kamil")
//				.surname("Memmedov")
//				.phone_number("558397202")
//				.email("kamilmmdov2@gmail.com")
//				.password("sdad")
////				.roleId(1L)
//				.status(UserStatus.ACTIVE)
//				.build();
//
////		user.setId(1L);
//
//		user.setRoleId(role1.getId());
//
//		System.out.println(user);
//
//		System.out.println(user.getId());
//		userRepository.save(user);
//		System.out.println(user.getId());
	}