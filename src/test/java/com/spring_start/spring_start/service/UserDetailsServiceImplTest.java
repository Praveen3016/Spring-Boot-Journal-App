//package com.spring_start.spring_start.service;
//
//import com.spring_start.spring_start.Entity.Users;
//import com.spring_start.spring_start.repository.UsersRepo;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentMatchers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//
//import static org.mockito.Mockito.when;
//
//public class UserDetailsServiceImplTest {
//
//    @InjectMocks
//    private UserDetailsServiceImpl userDetailsService;
//
//    @Mock
//    private UsersRepo usersRepo;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    void loadUserByUsernameTest() {
//        // Mock the Users entity
//        Users mockUser = new Users();
//        mockUser.setUsername("neha");
//        mockUser.setPassword("dijfsdiffisaj");
//        mockUser.setRoles(new ArrayList<>());  // Set roles as an empty list for now
//
//        // Mock the repository call to return the Users entity
//        when(usersRepo.findByUsername(ArgumentMatchers.anyString())).thenReturn(mockUser);
//
//        // Call the method to be tested
//        UserDetails user = userDetailsService.loadUserByUsername("neha");
//
//        // Assert that the returned UserDetails is not null
//        Assertions.assertNotNull(user);
//        Assertions.assertEquals("neha", user.getUsername());
//    }
//}
