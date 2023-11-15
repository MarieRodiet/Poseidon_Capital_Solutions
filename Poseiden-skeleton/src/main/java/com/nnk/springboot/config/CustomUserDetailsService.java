package com.nnk.springboot.config;

import com.nnk.springboot.domain.DBUser;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The CustomUserDetailsService class implements the UserDetailsService interface
 * to provide custom user details retrieval during Spring Security authentication.
 * It interacts with the UserRepository to load user information from the database.
 *
 * @author Marie Moore
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * The user repository for accessing user data from the database.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Automatically retrieves user details based on the provided username during user authentification.
     *
     * @param username the username for which user details are to be retrieved
     * @return UserDetails containing user information and authorities
     * @throws UsernameNotFoundException if no user is found for the given username
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DBUser u = userRepository.findByUsername(username);
        if(u != null){
            return new User(u.getUsername(), u.getPassword(), getGrantedAuthorities(u.getRole()));
        }
        else{
            throw new UsernameNotFoundException("No user found for "+ username + ".");
        }
    }


    /**
     * Retrieves the granted authorities for a given user role.
     *
     * @param role the role of the user
     * @return a list of GrantedAuthority objects for the user
     */
    private List<GrantedAuthority> getGrantedAuthorities(String role){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }
}
