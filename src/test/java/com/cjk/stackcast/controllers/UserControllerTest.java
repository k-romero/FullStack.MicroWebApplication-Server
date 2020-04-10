package com.cjk.stackcast.controllers;

import com.cjk.stackcast.models.User;

import com.cjk.stackcast.services.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Get /users/show/1 - Found")
    void testGetUserByIdFound() throws Exception{
        //Setup mocked user
        User mockUser = new User(1L, "testUserName", "testPassword");
        doReturn(mockUser).when(userService).create( mockUser);
        doReturn(Optional.of(mockUser)).when(userService).showUser(1L);

        //Execute the Get request
        mockMvc.perform(get("/zc-video-app/users/show/{id}",1))

                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate the returned fields
                .andExpect(jsonPath("$.userName",is("testUserName")))
                .andExpect(jsonPath("$.password",is("testPassword")))
                .andExpect(jsonPath("$.isConnected",is(false)));
    }
}