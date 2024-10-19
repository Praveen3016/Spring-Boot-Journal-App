package com.spring_start.spring_start.controller;

import com.spring_start.spring_start.Entity.Users;
import com.spring_start.spring_start.cache.AppCache;
import com.spring_start.spring_start.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.stereotype.Controller
@RestController
@RequestMapping("/admin")

public class AdminController {
    @Autowired
    private UsersService usersService ;

    @Autowired
    private AppCache appCache ;

    @GetMapping("/all-user")
    public ResponseEntity<?> createUser()
    {
        List<Users> users = usersService.getAll();
        if(users != null &&  !users.isEmpty()){
            return new ResponseEntity<>(users , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin-user")
    public Users createUser(@RequestBody Users newUser)
    {
        usersService.saveNewAdmin(newUser);
        return newUser ;
    }

    @GetMapping("/clear-app-cache")
    public void clearAppCache()
    {
        appCache.init();
    }
}
