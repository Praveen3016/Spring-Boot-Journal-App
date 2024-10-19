package com.spring_start.spring_start.repository;

import com.spring_start.spring_start.Entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class UserRepoImplTest {

    @Autowired
    private UserRepoImpl userRepoImpl ;

    @Test
    public void TestGetUserForSA()
    {
       List<Users> users = userRepoImpl.GetUserForSA();
       log.info("testing bhai jan : {} " , users);
    }

}
