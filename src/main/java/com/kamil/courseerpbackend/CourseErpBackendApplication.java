package com.kamil.courseerpbackend;

import com.kamil.courseerpbackend.model.entity.Role;
import com.kamil.courseerpbackend.model.entity.User;
import com.kamil.courseerpbackend.model.enums.UserStatus;
import com.kamil.courseerpbackend.repository.RoleRepository;
import com.kamil.courseerpbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

@SpringBootApplication
public class CourseErpBackendApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(CourseErpBackendApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Value("${security.jwt.public-key}")
	public String key;

	@Override
	public void run(String... args) throws Exception {
		System.out.println(key);

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

	private static String convertToPrivateKey(String key) {
		StringBuilder result = new StringBuilder();
		result.append("-----BEGIN PRIVATE KEY-----\n");
		result.append(key);
		result.append("\n-----END PRIVATE KEY-----");
		return result.toString();
	}

	private static String convertToPublicKey(String key) {
		StringBuilder result = new StringBuilder();
		result.append("-----BEGIN PUBLIC KEY-----\n");
		result.append(key);
		result.append("\n-----END PUBLIC KEY-----");
		return result.toString();


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
}
