package com.upskill.questionBankAPI.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.upskill.questionBankAPI.repository.UserRepo;


@SpringBootTest
public class UserServiceTests {
    
    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;

    /**
     * test that there is atleast 2 things being returned when find all users is run.
     */
    @Test
    void testFindAllUsersCallsFindAllMethodOnRepo() {
        assertTrue(userService.findAllUsers().size() >= 2);
    }

    /**
     * test the data is actually correct by ensuring it fails when we intend it to.
     */
    @Test
    void testFindAllUsersFailsWhenThereIsAGreaterNumberThanInTheDatabase(){
        assertFalse(userService.findAllUsers().size() >= 6);
    }

    /**
     * testing search by user id functionality
    */
    @Test
    void testFindUserByIDFunctionality(){
        int searchUserId = 1;

        assertNotNull(userService.findUserByID(searchUserId));
    }

    /**
     * test find user by username functionality
     */
    @Test
    void testfindUserByUsername(){
        String searchUser = "admin";

        assertNotNull(userService.findUserByUsername(searchUser));
    }

    
    
}

