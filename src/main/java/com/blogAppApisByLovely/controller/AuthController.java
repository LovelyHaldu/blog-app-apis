// file: AuthController.java
package com.blogAppApisByLovely.controller;

import com.blogAppApisByLovely.Security.JwtHelper;
import com.blogAppApisByLovely.payloads.JwtRequest;
import com.blogAppApisByLovely.payloads.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("/login")
    public JwtResponse login(@RequestBody JwtRequest request) {

        // authenticate
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // load user
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        // generate token
        String token = this.jwtHelper.generateToken(userDetails);

        return new JwtResponse(token);
    }
}