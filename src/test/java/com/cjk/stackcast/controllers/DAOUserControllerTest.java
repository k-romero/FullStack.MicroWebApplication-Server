package com.cjk.stackcast.controllers;

import com.cjk.stackcast.models.DAOUser;

import com.cjk.stackcast.services.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DAOUserControllerTest {

    @MockBean
    private UserService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Get /users/show/1 - Found")
    void testGetUserByIdFound() throws Exception{
        //Setup mocked user
        DAOUser mockDAOUser = new DAOUser(1L, "testUserName", "testPassword");
        doReturn(mockDAOUser).when(service).create(mockDAOUser);
        doReturn(Optional.of(mockDAOUser)).when(service).showUser(1L);

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
    @DisplayName("Get /users/find - Found")
    void testGetUserByUserNameFound() throws Exception{
        //Setup mocked user
        DAOUser mockDAOUser = new DAOUser(1L, "testUserName", "testPassword");
        doReturn(mockDAOUser).when(service).create(mockDAOUser);
        doReturn(Optional.of(mockDAOUser)).when(service).findByUserName("testUserName");

        //Execute the Get request
        mockMvc.perform(get("/zc-video-app/users/find")
                .param("userName","testUserName"))

                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate the returned fields
                .andExpect(jsonPath("$.userName",is("testUserName")))
                .andExpect(jsonPath("$.password",is("testPassword")))
                .andExpect(jsonPath("$.isConnected",is(false)));
    }

    @Test
    @DisplayName("GET /users/show - Found All")
    void testGetAllUsersFound() throws Exception {
        //Setup our mocked service
        DAOUser mockDAOUser = new DAOUser(1L, "testUserName", "testPassword");
        DAOUser mockDAOUser2 = new DAOUser(2L, "testUserName2", "testPassword2");
        Iterable<DAOUser> users = new ArrayList<>(Arrays.asList(mockDAOUser, mockDAOUser2));

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
        DAOUser mockDAOUser = new DAOUser(1L, "testUserName", "testPassword", LocalDate.now(),true);
        DAOUser postDAOUser = new DAOUser(1L, "testUserName", "testPassword", LocalDate.now(),true);

        given(service.create(postDAOUser)).willReturn(mockDAOUser);
        //Execute the Post request
        mockMvc.perform(post("/zc-video-app/users/create")
                .contentType(MediaType.APPLICATION_JSON)

                .content(asJsonString(postDAOUser))
                )
                //Validate that we get a 200

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
        DAOUser mockDAOUser = new DAOUser(1L, "testUserName", "testPassword", LocalDate.now(),true);
        DAOUser putDAOUser = new DAOUser(1L, newUserName, "testPassword", LocalDate.now(),true);

        given(service.showUser(1L)).willReturn(Optional.of(mockDAOUser));
        given(service.updateUserName(1L,newUserName)).willReturn(putDAOUser);
        //Execute the Post request
        mockMvc.perform(put("/zc-video-app/users/updateName/{userId}",1)
                .param("userName",newUserName)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(putDAOUser))
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
        DAOUser mockDAOUser = new DAOUser(1L, "testUserName", "testPassword", LocalDate.now(),true);
        DAOUser putDAOUser = new DAOUser(1L, "testUserName", newPassWord, LocalDate.now(),true);

        given(service.showUser(1L)).willReturn(Optional.of(mockDAOUser));
        given(service.updatePassword(1L,newPassWord)).willReturn(putDAOUser);
        //Execute the Post request
        mockMvc.perform(put("/zc-video-app/users/updatePw/{userId}",1)
                .param("passWord",newPassWord)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(putDAOUser))
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
        DAOUser mockDAOUser = new DAOUser(1L, "testUserName", "testPassword", LocalDate.now(),false);
        DAOUser putDAOUser = new DAOUser(1L, "testUserName", "testPassword", LocalDate.now(),true);

        given(service.showUser(1L)).willReturn(Optional.of(mockDAOUser));
        given(service.updateConnection(1L)).willReturn(putDAOUser);
        //Execute the Post request
        mockMvc.perform(put("/zc-video-app/users/login/{userId}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(putDAOUser))
        )
                //Validate that we get a 200
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.userName", is("testUserName")))
                .andExpect(jsonPath("$.password", is("testPassword")))
                .andExpect(jsonPath("$.isConnected", is(true)));
    }

    public static String asJsonString(DAOUser DAOUser) {
        try {
            StringBuilder jsonString = new StringBuilder("{");
            jsonString.append("\"id\":").append(DAOUser.getId()).append(",")
                    .append("\"userName\":\"").append(DAOUser.getUserName()).append("\",")
                    .append("\"password\":\"").append(DAOUser.getPassword()).append("\",")
                    .append("\"dateCreated\":\"").append(DAOUser.getDateCreated()).append("\",")
                    .append("\"isConnected\":").append(DAOUser.getIsConnected()).append("}");
            return jsonString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}