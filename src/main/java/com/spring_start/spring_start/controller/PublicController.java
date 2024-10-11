package com.spring_start.spring_start.controller;

import com.spring_start.spring_start.Entity.Users;
import com.spring_start.spring_start.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.stereotype.Controller
@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private  UsersService usersService;

    @PostMapping("/create-user")
    public Users createUser(@RequestBody Users newUser)
    {
        usersService.saveNewUser(newUser);
        return newUser ;
    }




}
