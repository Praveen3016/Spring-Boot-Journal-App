package com.spring_start.spring_start.repository;

import com.spring_start.spring_start.Entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class UserRepoImpl {

    @Autowired
    private MongoTemplate mongoTemplate ;

    public List<Users> GetUserForSA()
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$"));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));

        List<Users> users = mongoTemplate.find(query , Users.class);
        log.info("praveen Bhai Ke Users : {}" , users);
        return users ;
    }
}
