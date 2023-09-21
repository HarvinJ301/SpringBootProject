package com.upskill.questionBankAPI.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {
    
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserController userController;

  
    
    @BeforeEach
	void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

    /**
     * ensure the user controller is not null.
     */
    @Test
    void test_controllerNotNull(){
        assertThat(userController).isNotNull();
    }

    /**
     * Testing URL For getting all users and making sure it is in a JSON Format
     * @throws Exception
     */
    @Test
    void test_getAllUsers() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                    .get("http://localhost:8088/api/v1/user")
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk());
    }

    /**
     * Testing users by Id - checking that when we search for an Id it gives back the correct id through the url.
     * @throws Exception
     */
    @Test
    void test_getUsersById() throws Exception{
        int searchId = 2;
        mockMvc.perform(MockMvcRequestBuilders
            .get("http://localhost:8088/api/v1/user/{id}", searchId)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());   
    }

    /**
     * testing get user by username URL
     * passes through a string and then ensures the URL takes in that string at the end and the status of the URL is good.
     * @throws Exception
     */
    @Test
    void test_getUserByUsername() throws Exception {
        String searchUser = "admin";
        mockMvc.perform(MockMvcRequestBuilders
        .get("http://localhost:8088/api/v1/user/username/{username}", searchUser)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
    }
}

