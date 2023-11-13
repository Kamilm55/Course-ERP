package com.kamil.courseerpbackend.service.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
//    The authentication of any request mostly depends on the implementation of the UserDetailsService interface.

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // todo: complete this logic , we must accept email and return UserDetails
        return null;
    }
}
