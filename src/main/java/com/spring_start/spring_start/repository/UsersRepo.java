package com.spring_start.spring_start.repository;

import com.spring_start.spring_start.Entity.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepo extends MongoRepository<Users , ObjectId> {

    Users findByUsername(String username);

    void deleteByUsername(String username);
}
