package com.nnk.springboot.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) ->
                        authorize
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                .requestMatchers("/**").permitAll()
                                .anyRequest().authenticated()

                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .defaultSuccessUrl("/transfers", true)
                                .permitAll()
                ).logout(
                        logout -> logout
                                //.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }


}
