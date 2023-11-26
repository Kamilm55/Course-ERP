package com.kamil.courseerpbackend.service.auth;

import com.kamil.courseerpbackend.exception.BaseException;
import com.kamil.courseerpbackend.model.dto.RefreshTokenDto;
import com.kamil.courseerpbackend.model.entity.User;
import com.kamil.courseerpbackend.model.enums.response.ExceptionResponseMessages;
import com.kamil.courseerpbackend.model.payload.auth.LoginPayload;
import com.kamil.courseerpbackend.model.payload.auth.RefreshTokenPayload;
import com.kamil.courseerpbackend.model.payload.auth.register.RegisterPayload;
import com.kamil.courseerpbackend.model.response.auth.LoginResponse;
import com.kamil.courseerpbackend.model.security.LoggedInUserDetails;
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
import org.springframework.stereotype.Service;

import static com.kamil.courseerpbackend.model.enums.response.ExceptionResponseMessages.USER_ALREADY_REGISTERED;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthBusinessServiceImpl implements AuthBusinessService{
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final AccessTokenManager accessTokenManager;
    private final RefreshTokenManager refreshTokenManager;
    private final UserDetailsService userDetailsService;

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

        if(existsUser){
         throw  BaseException.of(USER_ALREADY_REGISTERED);
        }



        log.info("You can register , email does not exist before");
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
