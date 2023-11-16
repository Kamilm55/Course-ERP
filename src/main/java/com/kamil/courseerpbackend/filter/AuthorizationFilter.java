package com.kamil.courseerpbackend.filter;

import com.kamil.courseerpbackend.model.security.LoggedInUserDetails;
import com.kamil.courseerpbackend.service.security.AccessTokenManager;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {
    private final AccessTokenManager accessTokenManager;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // todo: Complete filter logic , now  it accepts all requests


        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(token != null && token.startsWith("Bearer ")){
            String accToken = token.substring(7);
            Claims claims = accessTokenManager.read(accToken);
            String email = claims.get("email", String.class);

            LoggedInUserDetails userDetails = (LoggedInUserDetails) userDetailsService.loadUserByUsername(email);

//     set user details to security context
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities())
            );
            // read this ->https://stackoverflow.com/questions/75643336/basic-username-and-password-check-instead-of-spring-security
            // explore : in spring security email password matching is done implicitly?
        }

        filterChain.doFilter(request,response);//if we don't call this method request don't go to the controller we are not able to show result,
        //  if this  filterChain.doFilter calls it forward other filer or if there is no filter it forwards to controller
        // for ex: i cannot open swagger or i cannot show even static data  from testController
    }
}
