package com.cjk.stackcast.controllers;

import com.cjk.stackcast.models.User;

import com.cjk.stackcast.models.Video;
import com.cjk.stackcast.services.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @MockBean
    private UserService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Get /users/show/1 - Found")
    void testGetUserByIdFound() throws Exception{
        //Setup mocked user
        User mockUser = new User(1L, "testUserName", "testPassword");
        doReturn(mockUser).when(service).create( mockUser);
        doReturn(Optional.of(mockUser)).when(service).showUser(1L);

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

    @Test
    @DisplayName("GET /users/show/1 - Not Found")
    void testGetUserByIdNotFound() throws Exception {
        //Setup our mocked service
        doReturn(Optional.empty()).when(service).showUser(1L);

        //Execute the GET request
        mockMvc.perform(get("/zc-video-app/users/show/{id}",1))

                //Validate that we get a 404 Not Found
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /users/show - Found All")
    void testGetAllUsersFound() throws Exception {
        //Setup our mocked service
        User mockUser = new User(1L, "testUserName", "testPassword");
        User mockUser2 = new User(2L, "testUserName2", "testPassword2");
        Iterable<User> users = new ArrayList<>(Arrays.asList(mockUser,mockUser2));

        doReturn(users).when(service).showAll();

        //Execute the GET request
        mockMvc.perform(get("/zc-video-app/users/show"))

                //Validate that we get a 200 Found
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.*").isArray())
                .andExpect(jsonPath("$[0].userName", is("testUserName")))
                .andExpect(jsonPath("$[0].password", is("testPassword")))
                .andExpect(jsonPath("$[1].userName", is("testUserName2")))
                .andExpect(jsonPath("$[1].password", is("testPassword2")));

    }

    @Test
    @DisplayName("Post /users/create - Created")
    void testCreateUser() throws Exception {
        //Setup our mocked service
        User mockUser = new User(1L, "testUserName", "testPassword", LocalDate.now(),true);
        User postUser = new User(1L, "testUserName", "testPassword", LocalDate.now(),true);

        given(service.create(postUser)).willReturn(mockUser);
        //Execute the Post request
        mockMvc.perform(post("/zc-video-app/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postUser)))

                //Validate that we get a 201
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string(HttpHeaders.LOCATION,"/create/1"))

                .andExpect(jsonPath("$.userName", is("testUserName")))
                .andExpect(jsonPath("$.password", is("testPassword")));
    }

    @Test
    @DisplayName("Put /users/updateName - Success")
    void testUpdateUserName() throws Exception {
        //Setup our mocked service
        String newUserName = "Kevin";
        User mockUser = new User(1L, "testUserName", "testPassword", LocalDate.now(),true);
        User putUser = new User(1L, newUserName, "testPassword", LocalDate.now(),true);

        given(service.showUser(1L)).willReturn(Optional.of(mockUser));
        given(service.updateUserName(1L,newUserName)).willReturn(putUser);
        //Execute the Post request
        mockMvc.perform(put("/zc-video-app/users/updateName/{userId}",1)
                .param("userName",newUserName)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(putUser))
        )
                //Validate that we get a 200
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.userName", is(newUserName)))
                .andExpect(jsonPath("$.password", is("testPassword")));
    }

    @Test
    @DisplayName("Put /users/updatePw - Success")
    void testUpdatePassword() throws Exception {
        //Setup our mocked service
        String newPassWord = "k3v1n2020";
        User mockUser = new User(1L, "testUserName", "testPassword", LocalDate.now(),true);
        User putUser = new User(1L, "testUserName", newPassWord, LocalDate.now(),true);

        given(service.showUser(1L)).willReturn(Optional.of(mockUser));
        given(service.updatePassword(1L,newPassWord)).willReturn(putUser);
        //Execute the Post request
        mockMvc.perform(put("/zc-video-app/users/updatePw/{userId}",1)
                .param("passWord",newPassWord)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(putUser))
        )
                //Validate that we get a 200
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.userName", is("testUserName")))
                .andExpect(jsonPath("$.password", is(newPassWord)));
    }

    @Test
    @DisplayName("Put /users/login - Success")
    void testUpdateLoginStatus() throws Exception {
        //Setup our mocked service
        User mockUser = new User(1L, "testUserName", "testPassword", LocalDate.now(),false);
        User putUser = new User(1L, "testUserName", "testPassword", LocalDate.now(),true);

        given(service.showUser(1L)).willReturn(Optional.of(mockUser));
        given(service.updateConnection(1L)).willReturn(putUser);
        //Execute the Post request
        mockMvc.perform(put("/zc-video-app/users/login/{userId}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(putUser))
        )
                //Validate that we get a 200
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.userName", is("testUserName")))
                .andExpect(jsonPath("$.password", is("testPassword")))
                .andExpect(jsonPath("$.isConnected", is(true)));
    }

    public static String asJsonString(User user) {
        try {
            StringBuilder jsonString = new StringBuilder("{");
            jsonString.append("\"id\":").append(user.getId()).append(",")
                    .append("\"userName\":\"").append(user.getUserName()).append("\",")
                    .append("\"password\":\"").append(user.getPassword()).append("\",")
                    .append("\"dateCreated\":\"").append(user.getDateCreated()).append("\",")
                    .append("\"isConnected\":").append(user.getIsConnected()).append("}");
            return jsonString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}