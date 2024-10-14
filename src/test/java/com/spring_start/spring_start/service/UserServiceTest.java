package com.spring_start.spring_start.service;

import com.spring_start.spring_start.repository.UsersRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UsersRepo userRepo ;

    @Disabled
    @Test
    public void testFindByUsername()
    {
        assertEquals(4 , 2+2);
        assertNotNull(userRepo.findByUsername("neha"));
    }

    @ParameterizedTest
    @CsvSource({
           "neha",
           "praveen",
           "manish",
    })
    public void testFindByUsername( String name)
    {
        assertNotNull(userRepo.findByUsername(name) ," faild by" + name );
    }
}
