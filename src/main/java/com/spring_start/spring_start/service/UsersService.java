package com.spring_start.spring_start.service;

import com.spring_start.spring_start.Entity.JournalEntry;
import com.spring_start.spring_start.Entity.Users;
import com.spring_start.spring_start.repository.JournalEntryRepo;
import com.spring_start.spring_start.repository.UsersRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UsersService {


    @Autowired
    private UsersRepo userRepo ;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveUser(Users user)
    {
        userRepo.save(user);
    }

    public Users saveNewUser(Users user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepo.save(user);
        return user;
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
