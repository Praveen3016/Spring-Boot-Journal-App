package com.spring_start.spring_start.controller;

import com.spring_start.spring_start.Entity.Users;
import com.spring_start.spring_start.service.UserDetailsServiceImpl;
import com.spring_start.spring_start.service.UsersService;
import com.spring_start.spring_start.utility.JwtUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.stereotype.Controller
@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {

    @Autowired
    private AuthenticationManager authenticationManager ;

    @Autowired
    private UserDetailsServiceImpl userDetailsService ;

    @Autowired
    private JwtUtility jwtUtility ;

    @Autowired
    private  UsersService usersService;

    @PostMapping("/signup")
    public Users signup(@RequestBody Users newUser)
    {
        usersService.saveNewUser(newUser);
        return newUser ;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users newUser)
    {
        try{
            Authentication authentication = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(newUser.getUsername() , newUser.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(newUser.getUsername());
            String jwt = jwtUtility.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt , HttpStatus.OK);
        } catch (Exception e) {
             log.error("exepting accure while create authentication token" , e);
             return new ResponseEntity<>("Incorreact username and password" , HttpStatus.BAD_REQUEST);
        }
    }




}