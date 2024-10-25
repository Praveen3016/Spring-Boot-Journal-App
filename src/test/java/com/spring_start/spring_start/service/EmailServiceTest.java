package com.spring_start.spring_start.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailSevice emailSevice;

    @Test
    public void testMailService()
    {
        emailSevice.SendMail("farzimail3016@gmail.com" , "faltu bate" , "chutiye tu smpt mail sikh gya");
    }
}
