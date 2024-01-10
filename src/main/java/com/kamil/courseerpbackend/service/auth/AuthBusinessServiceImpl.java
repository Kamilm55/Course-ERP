package com.kamil.courseerpbackend.service.auth;

import com.kamil.courseerpbackend.exception.BaseException;
import com.kamil.courseerpbackend.model.dto.RefreshTokenDto;
import com.kamil.courseerpbackend.model.entity.Branch;
import com.kamil.courseerpbackend.model.entity.Course;
import com.kamil.courseerpbackend.model.entity.Employee;
import com.kamil.courseerpbackend.model.entity.User;
import com.kamil.courseerpbackend.model.enums.BranchStatus;
import com.kamil.courseerpbackend.model.enums.UserStatus;
import com.kamil.courseerpbackend.model.enums.response.ExceptionResponseMessages;
import com.kamil.courseerpbackend.model.mapper.CourseEntityMapper;
import com.kamil.courseerpbackend.model.mapper.UserEntityMapper;
import com.kamil.courseerpbackend.model.payload.auth.LoginPayload;
import com.kamil.courseerpbackend.model.payload.auth.RefreshTokenPayload;
import com.kamil.courseerpbackend.model.payload.auth.register.RegisterPayload;
import com.kamil.courseerpbackend.model.response.auth.LoginResponse;
import com.kamil.courseerpbackend.model.security.LoggedInUserDetails;
import com.kamil.courseerpbackend.service.branch.BranchService;
import com.kamil.courseerpbackend.service.course.CourseService;
import com.kamil.courseerpbackend.service.employee.EmployeeService;
import com.kamil.courseerpbackend.service.role.RoleService;
import com.kamil.courseerpbackend.service.security.AccessTokenManager;
import com.kamil.courseerpbackend.service.security.RefreshTokenManager;
import com.kamil.courseerpbackend.service.user.UserService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.kamil.courseerpbackend.model.enums.response.ExceptionResponseMessages.USER_ALREADY_REGISTERED;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthBusinessServiceImpl implements AuthBusinessService{
    private static final String BRANCH_NAME_DEFAULT_PATTERN = "%s Default Branch";

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final RoleService roleService;
    private final CourseService courseService;
    private final BranchService branchService;
    private final EmployeeService employeeService;
    private final AccessTokenManager accessTokenManager;
    private final RefreshTokenManager refreshTokenManager;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginResponse login(LoginPayload payload) {
        authenticate(payload); // if success continue else throw error

        User user = userService.getUserByEmail(payload.getEmail());

        return prepareLoginResponse(user,payload.isRememberMe());

    }

    @Override
    public LoginResponse refresh(RefreshTokenPayload payload) {
        //Learn:
        // i should return "valid" access token and refresh token because you need access token for authorization
        // you give me refresh token and i give these
            Claims claims = refreshTokenManager.read(payload.getRefreshToken());
            String email = claims.get("email", String.class);

            User user = userService.getUserByEmail(email);

        return prepareLoginResponse(user,payload.isRememberMe());

    }

    @Override
    public void register(RegisterPayload payload) {

        boolean existsUser = userService.existsUserByEmail(payload.getEmail());
        boolean existsUserByPhoneNumber = userService.existsUserByPhoneNumber(payload.getPhoneNumber());

        if(existsUser){
         throw  BaseException.userAlreadyRegistered("email",payload.getEmail());
        } else if (existsUserByPhoneNumber) {
            throw BaseException.userAlreadyRegistered("phone_number",payload.getPhoneNumber());
        }

        // Insert user
//        User user = UserEntityMapper.INSTANCE.fromRegisterPayloadToUser(
//                payload,
//                passwordEncoder.encode(payload.getPassword()),
//                roleService.getDefaultRole().getId()
//        );
//        {
//            "email": "k4@gmail.com",
//                "name": "kamil1",
//                "address": "string",
//                "password": "pass",
//                "courseName": "str",
//                "phoneNumber": "452132",
//                "surname": "Memmedov"
//        }
        //refactorThis: for debug
    log.info("REGISTER password comparisons:");
        System.out.println(payload.getPassword());
        String encodedPass = passwordEncoder.encode(payload.getPassword());
        System.out.println(encodedPass);
        // $2a$10$NseIhsTuM.mf/DUItmudy.6qGQo4Jo5hEr8cJOu/P8RubyRCh74BC
        // $2a$10$RYF8aWjMQtaw0CJbLYY1LO0syxyyzxZEJgqkfup13rVzlcWctS6.W

        System.out.println(passwordEncoder.matches(payload.getPassword(),encodedPass));

        User user = User.builder()
                .email(payload.getEmail())
                .name(payload.getName())
                .password(passwordEncoder.encode(payload.getPassword()))
                .phoneNumber(payload.getPhoneNumber())
                .surname(payload.getSurname())
                .roleId(roleService.getDefaultRole().getId())
                .status(UserStatus.ACTIVE)
                .build();
        userService.insertUser(user);

        // Insert course
        Course course = CourseEntityMapper.INSTANCE.fromRegisterPayloadToCourse(payload);
        courseService.insert(course);

        // Insert default Branch
        // make relation with course fk
        branchService.insert(populateDefaultBranch(course,payload));

        // Insert employee
        employeeService.insert(Employee.builder().userId(user.getId()).build());
    }


    @Override
    public void setAuthentication(String email) {
        LoggedInUserDetails userDetails = (LoggedInUserDetails) userDetailsService.loadUserByUsername(email);


//     set user details to security context
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities())
        );
    }

    // refactorThis:
    //  private util methods
    private Branch populateDefaultBranch(Course course,RegisterPayload payload) {
        // refactorThis: param should be only payload

        // todo: customize fields
        return Branch.builder()
                .status(BranchStatus.ACTIVE)
                .name(String.format(BRANCH_NAME_DEFAULT_PATTERN, payload.getCourseName()))
                .courseId(course.getId())
                .address(payload.getAddress())
                .build();
    }
    //refactorThis: use email instead of user ,then get user from userService via email
    private LoginResponse prepareLoginResponse(User user , boolean rememberMe){
        return     LoginResponse.builder()
                .accessToken(accessTokenManager.generate(user))
                .refreshToken(refreshTokenManager.generate(
                        RefreshTokenDto.builder().rememberMe(rememberMe).user(user).build()
                ))
                //.userInfo()
                .build();
        //todo: add user info
    }

    private void authenticate(LoginPayload request){
        // debug:
        System.out.println(request.getEmail() + " " + request.getPassword());
      User user =  userService.getUserByEmail(request.getEmail());

//      String prevEncodedPass = "$2a$10$.QDobm/znPXZ2Te8VLhxv.zzGSjZOZecEVwqznYwxUOOPeQp.6dvC";
//        System.out.println(user.getPassword());
//        String newEncodedPass = passwordEncoder.encode(request.getPassword());
//        System.out.println(passwordEncoder.encode(request.getPassword()));

        log.info("INFO 1:\nPassword encoder works?");
        System.out.println(passwordEncoder.matches("smth", passwordEncoder.encode("smth")));
        log.info("INFO 2:\nPassword encoder matches with db password?");
        System.out.println(passwordEncoder.matches("pass", user.getPassword()));
        System.out.println(passwordEncoder.matches(request.getPassword(),user.getPassword()));
        try{
            authenticationManager.authenticate(
                    //todo: add authorities(selahiyet) to constructor as 3rd parameter

                    //Learn:
                    // This check is there any user with this credentials or not
                    // this is my payload password:request.getPassword()
                    // and in config we set UserDetails which is fetched from db ,
                    // there is also my (userDetails) password associated with this username(email)
                    new UsernamePasswordAuthenticationToken(request.getEmail() ,request.getPassword())
            );
        }
        catch (AuthenticationException ex){
            // todo: add custom exception
            // todo: fix this
            log.error(ex.getClass().getSimpleName());
            log.error(ex.getMessage());

            if(ex.getCause() instanceof BaseException)
                throw (BaseException) ex.getCause();
            else
                throw BaseException.unexpected();

        }

    }

}
