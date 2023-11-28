package com.kamil.courseerpbackend.service.auth;

import com.kamil.courseerpbackend.exception.BaseException;
import com.kamil.courseerpbackend.model.dto.RefreshTokenDto;
import com.kamil.courseerpbackend.model.entity.Branch;
import com.kamil.courseerpbackend.model.entity.Course;
import com.kamil.courseerpbackend.model.entity.User;
import com.kamil.courseerpbackend.model.enums.BranchStatus;
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
    private final AccessTokenManager accessTokenManager;
    private final RefreshTokenManager refreshTokenManager;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder bCryptPasswordEncoder;

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
        User user = UserEntityMapper.INSTANCE.fromRegisterPayloadToUser(
                payload,
                bCryptPasswordEncoder.encode(payload.getPassword()),
                roleService.getDefaultRole().getId()
        );
        userService.insertUser(user);

        // Insert course
        Course course = CourseEntityMapper.INSTANCE.fromRegisterPayloadToCourse(payload);
        courseService.insert(course);

        // Insert default Branch
        // make relation with course fk
        Branch branch = populateDefaultBranch(course,payload);
        branchService.insert(branch);

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
            throw new RuntimeException(ex);
        }

    }

}
