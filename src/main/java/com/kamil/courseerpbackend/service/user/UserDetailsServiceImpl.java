package com.kamil.courseerpbackend.service.user;

import com.kamil.courseerpbackend.model.entity.User;
import com.kamil.courseerpbackend.model.security.LoggedInUserDetails;
import com.kamil.courseerpbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
//    The authentication of any request mostly depends on the implementation of the UserDetailsService interface.

    private final UserService userService;
    // todo: currently our system supports only email login, but in the future
    //  we have to implement phone number version
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // todo: authorities is empty list , we must change this authorities(roles) list
        User user = userService.getUserByEmail(username); // fetch details of user from db

        if(user!=null){
            return new LoggedInUserDetails(user.getEmail(),user.getPassword(),new ArrayList<>());
        }else {
            throw new UsernameNotFoundException("There is no authenticated user with this email");
        }


    }
}
