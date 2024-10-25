package com.spring_start.spring_start.service;


import com.spring_start.spring_start.schedular.UserSchedular;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class sentimenttest {

    @Autowired
    private UserSchedular userSchedular ;

    @Test
    public void testMailSentiment()
    {
    userSchedular.fetchUsersAndSendMail();
    }
}
