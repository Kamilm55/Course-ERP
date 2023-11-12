package com.kamil.courseerpbackend.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println(request.getServletPath());
        System.out.println(request.getRequestURL());


//        if this  filterChain.doFilter calls it forward other filer or if there is no filter it forwards to controller
        filterChain.doFilter(request,response);//if we don't call this method request don't go to the controller we are not able to show result,
        // for ex: i cannot open swagger or i cannot show even static data  from testController
    }
}
