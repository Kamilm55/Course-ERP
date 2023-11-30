## Documentation ( What i learn => with examples )
## 1. What is mapstruct?
- There are 2 classes (pojo class usually).And there can be similarities and differences in properties , we change these properties to one class property that is similar for two or more classes.
- MapStruct is a Java-based code generation library that simplifies the implementation of mappings between Java bean types. It is particularly useful for situations where you need to transform data between objects with different structures, such as DTOs (Data Transfer Objects), entities, and other domain objects.
- Example:   
// Source class


    public class Source {
      private String name;
      private int age;

    // getters and setters
    }

// Target class

    public class Target {
      private String fullName;
      private int age;

    // getters and setters
    }

// Mapper interface

    @Mapper
    public interface MyMapper {
      MyMapper INSTANCE = Mappers.getMapper(MyMapper.class); 
  
      @Mapping(source = "name", target = "fullName")
      Target sourceToTarget(Source source);
    }

// Usage

    Source source = new Source("John Doe", 25);
    Target target = MyMapper.INSTANCE.sourceToTarget(source);

// In this example age is similar it is converted automatically , other properties should be define in @Mapping annotation 

###  Real example

    @Mapper
    public interface UserEntityMapper {
    UserEntityMapper INSTANCE = Mappers.getMapper(UserEntityMapper.class);
    
        // Learn:
        //  source is param target is User's field name
        //  similar values are not defined in @Mapping
        //  we must use only properties which is present in the User entity
        //  in User we have 7 fields but in registerPayload we have 5 similar fields , we should define additionally

        @Mapping(source = "encryptedPassword" , target = "password")
        @Mapping(source = "roleId" , target = "roleId")
        @Mapping( target = "status" , constant = "ACTIVE")// Regardless of the actual value of the source property or how the mapping logic is generated for other properties, the "status" property in the target type will always be set to the constant value "ACTIVE".
        User fromRegisterPayloadToUser(RegisterPayload registerPayload,String encryptedPassword,Long roleId);
    }

## 2. Register logic
- Check this email or phone_number ( unique identifier ) is exists in db or not , if exists throw exception _USER_ALREADY_REGISTERED_
- Accept **RegisterPayload** and ( it can be mapstruct )
- Use payload and create new user in db when creating use password encoder
- Insert this user into db
## 3. Password Encoder in Spring Security
    // Create an instance of the PasswordEncoder (using BCrypt in this case)
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		// User registration: Encode the password and store it in the database
		String rawPassword = "user_password";
		String encodedPassword1 = passwordEncoder.encode(rawPassword);
		String encodedPassword2 = passwordEncoder.encode(rawPassword);

		System.out.println("Encoded Password 1: " + encodedPassword1);
		System.out.println("Encoded Password 2: " + encodedPassword2);

		// User authentication: Check if the entered password matches the stored (encoded) password
		boolean passwordMatches1 = passwordEncoder.matches("user_password", encodedPassword1);
		boolean passwordMatches2 = passwordEncoder.matches("user_password", encodedPassword2);

		System.out.println("Password Matches 1: " + passwordMatches1);
		System.out.println("Password Matches 2: " + passwordMatches2);
