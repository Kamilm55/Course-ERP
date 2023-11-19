package com.kamil.courseerpbackend.config;

import com.kamil.courseerpbackend.filter.AuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}

    // it includes userDetailsService,and passwordEncoder
    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService
                                                            , PasswordEncoder passwordEncoder)
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http , AuthorizationFilter authorizationFilter) throws Exception {

        return http
                .authorizeHttpRequests(request -> {
                    // Learn:
                    //  .antMatchers("/secured-endpoint").authenticated()
                    //  .antMatchers("/public-endpoint").permitAll()
                    //  In Spring Security, the anonymous() configuration is used to specify that
                    //  certain paths are accessible to unauthenticated (anonymous) users.
                    //  If a user is authenticated, by default, they would not have access to paths configured with anonymous().

                   // Swagger UI -> permit all swagger sub-path , for ex: "/swagger-ui/" ,"/swagger-ui/index.html" ,"/swagger-ui/api-docs" etc.
                    request.requestMatchers("/v3/api-docs/**","/swagger-ui/**").permitAll();

                    // todo: Secure http requests !

                    //   Auth URLs
                    request.requestMatchers("/v1/auth/logout").authenticated();
                    request.requestMatchers("/v1/auth/**").anonymous();//accessible to only unauthenticated users

                    // test url
                    request.requestMatchers("/test/auth").authenticated();
                    request.requestMatchers("/test/no-auth").permitAll();
                })
                .addFilterBefore(authorizationFilter , UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }


}
