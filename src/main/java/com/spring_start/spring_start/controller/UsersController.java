package com.spring_start.spring_start.controller;

import com.spring_start.spring_start.Entity.JournalEntry;
import com.spring_start.spring_start.Entity.Users;
import com.spring_start.spring_start.service.JournalEntryService;
import com.spring_start.spring_start.service.UsersService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@org.springframework.stereotype.Controller
@RestController
@RequestMapping("/user")
public class UsersController {

    @Autowired
private UsersService usersService;

    @GetMapping
    public List<Users> getAll()
    {
       return usersService.getAll();
    }


    @PostMapping
    public Users createUser(@RequestBody Users newuser)
    {
         usersService.saveUser(newuser);
         return newuser ;
    }

    @PutMapping("/{username}")
    public ResponseEntity<Users> updateUser(@RequestBody Users user , @PathVariable String username)
    {
        Users userInDb = usersService.findByUserName(username);
        if(userInDb != null)
        {
            userInDb.setUsername(user.getUsername());
            userInDb.setPassword(user.getPassword());
            usersService.saveUser(userInDb);
            return new ResponseEntity<>(userInDb , HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }



}
