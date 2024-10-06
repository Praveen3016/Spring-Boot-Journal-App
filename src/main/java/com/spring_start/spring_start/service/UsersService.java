package com.spring_start.spring_start.service;

import com.spring_start.spring_start.Entity.JournalEntry;
import com.spring_start.spring_start.Entity.Users;
import com.spring_start.spring_start.repository.JournalEntryRepo;
import com.spring_start.spring_start.repository.UsersRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UsersService {


    @Autowired
    private UsersRepo userRepo ;

    public Users saveUser(Users user)
    {
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
//    public boolean updateEntry( Users user)
//    {
//
//    }
}
