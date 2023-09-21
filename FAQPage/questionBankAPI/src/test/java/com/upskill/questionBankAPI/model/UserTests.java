package com.upskill.questionBankAPI.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserTests {

    User users;

    @BeforeEach
    void setUp() throws Exception {
        users = new User();
    }

    /**
     * test get and set id for user
     */
    @Test
    void testGetAndSetUserID() {
        int expectedQuestionId = 1;
        users.setUserID(expectedQuestionId);
        assertEquals(expectedQuestionId, users.getUserID());
    }
    
    /**
     * test get and set first name for user
     */
    @Test
    void testGetAndSetFirstName() {
        String expectedFirstName = "John";
        users.setFirstName(expectedFirstName);
        assertEquals(expectedFirstName, users.getFirstName());
    }

    /**
     * test get and set last name for user
     */
    @Test
    void testGetAndSetLastName(){
        String expectedLastName = "Doe";
        users.setFirstName(expectedLastName);
        assertEquals(expectedLastName, users.getFirstName());
    }

    /**
     * test get and set email for user
     */
    @Test
    void testGetAndSetEmail() {
        String expectedEmail = "johndoe@gmail.com";
        users.setEmail(expectedEmail);
        assertEquals(expectedEmail, users.getEmail());
    } 

    /**
     * test get and set username for user
     */
    @Test
    void testGetAndSetUsername(){
        String expectedUsername = "johnd123";
        users.setUsername(expectedUsername);
        assertEquals(expectedUsername, users.getUsername());
    }

    /**
     * test get and set password for user
     */
    @Test
    void testGetAndSetPassword() {
        String expectedPassword = "password1";
        users.setPassword(expectedPassword);
        assertEquals(expectedPassword, users.getPassword());
    }

    /**
     * test get and set user type for user
     */
    @Test
    void testGetAndSetUserType() {
        String expectedUserType = "admin";
        users.setUserType(expectedUserType);
        assertEquals(expectedUserType, users.getUserType());
    }

}


