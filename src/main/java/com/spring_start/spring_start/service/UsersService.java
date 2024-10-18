package com.spring_start.spring_start.service;

import com.spring_start.spring_start.Entity.JournalEntry;
import com.spring_start.spring_start.Entity.Users;
import com.spring_start.spring_start.repository.JournalEntryRepo;
import com.spring_start.spring_start.repository.UsersRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UsersService {


    @Autowired
    private UsersRepo userRepo ;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//    private static final Logger logger = LoggerFactory.getLogger(UsersService.class);
//    using simple logback

    public void saveUser(Users user)
    {
        userRepo.save(user);
    }

    public ResponseEntity<?> saveNewUser(Users user)
    {
        try
        {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepo.save(user);
            return new ResponseEntity<>(user , HttpStatus.CREATED);
        }catch (Exception e)
        {
            log.info("hehehe praveen !!!!");
            log.warn("hehehe praveen !!!!");
            log.error("error accured" , e);
            log.debug("hehehe praveen !!!!");
            log.trace("hehehe praveen !!!!");


            return new ResponseEntity<>( e , HttpStatus.BAD_REQUEST);
        }

    }
    public List<Users> getAll()
    {
        return userRepo.findAll();
    }

    public Optional<Users> getById(ObjectId id)
    {
        return userRepo.findById(id);
    }

    public boolean deleteById(ObjectId id)
    {
        userRepo.deleteById(id);
        return true ;
    }

    public Users findByUserName(String username)
    {
       return userRepo.findByUsername(username);
    }

    public Users saveNewAdmin(Users newAdmin) {
        newAdmin.setPassword(passwordEncoder.encode(newAdmin.getPassword()));
        newAdmin.setRoles(Arrays.asList("USER", "ADMIN"));
        userRepo.save(newAdmin);
        return newAdmin;
    }
//    public boolean updateEntry( Users user)
//    {
//
//    }
}
