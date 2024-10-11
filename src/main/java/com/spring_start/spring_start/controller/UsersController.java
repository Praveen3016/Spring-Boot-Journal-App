package com.spring_start.spring_start.controller;

import com.spring_start.spring_start.Entity.JournalEntry;
import com.spring_start.spring_start.Entity.Users;
import com.spring_start.spring_start.repository.UsersRepo;
import com.spring_start.spring_start.service.JournalEntryService;
import com.spring_start.spring_start.service.UsersService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private UsersRepo userRepo;


    @PutMapping
    public ResponseEntity<Users> updateUser(@RequestBody Users user )
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Users userInDb = usersService.findByUserName(username);
            userInDb.setUsername(user.getUsername());
            userInDb.setPassword(user.getPassword());
            usersService.saveNewUser(userInDb);
            return new ResponseEntity<>(userInDb , HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        userRepo.deleteByUsername(username);
        return new ResponseEntity<>("userDeleted" , HttpStatus.OK);
    }



}
