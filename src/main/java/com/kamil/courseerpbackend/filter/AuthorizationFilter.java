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

    //Learn:
    // Token Authorization Logic:
    // The important thing to note here is that your authentication mechanism is based solely on the presence and validity of the JWT. The actual "authentication" (matching a password, checking credentials against a database, etc.) is not explicitly happening in this filter because the token itself is considered the proof of authentication.
    // I understand that we create unique access token with private and public key and we can only read with these keys , therefore anyone who don't know our keys cannot create valid token .That's why we check only if it is valid (via accecTokenManager.read()) if exception does not occur token is valid and we can load user with userDetails
    // The private key is used to sign the token, and the public key is used to verify the token's signature.
    // if we send tampered(fake) token exception occurs-> io.jsonwebtoken.security.SignatureException: JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted.
    // The security of this approach relies on keeping the private key secure. Since only someone with the private key can sign a token, anyone who can validate the token with the corresponding public key can trust that the token hasn't been tampered with.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // This approach assumes that the token itself is a valid proof of authentication!
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
        }

        filterChain.doFilter(request,response);
        //Learn:
        // if we don't call this method request don't go to the controller we are not able to show result,
        //  if this  filterChain.doFilter calls it forward other filter or if there is no filter it forwards to controller
        // for ex: i cannot open swagger or i cannot show even static data  from testController
    }
}
