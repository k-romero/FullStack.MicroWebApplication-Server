package com.cjk.stackcast.services;

import com.cjk.stackcast.models.User;
import com.cjk.stackcast.models.video.BasicVideo;
import com.cjk.stackcast.repositories.UserRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;


    @Test
    @DisplayName("Test findById Success")
    public void testFindByIdSuccess(){
        // Set up mock object and repository
        User mockUser = new User("testUserName", "testpassWord");
        doReturn(Optional.of(mockUser)).when(userRepository).findById(1L);

        // Execute call
        Optional<User> returnUser = userService.showUser(1L);

        Assertions.assertTrue(returnUser.isPresent(), "No User was found when there should be");
        Assertions.assertSame(returnUser.get(),mockUser, "Models don't match up");
    }

    @Test
    @DisplayName("Test findById Fail")
    public void testFindByIdFail(){
        // Set up mock object and repository
        doReturn(Optional.empty()).when(userRepository).findById(1L);

        // Execute call
        Optional<User> returnUser = userService.showUser(1L);

        Assertions.assertFalse(returnUser.isPresent(), "User was found when there shouldn't be");
    }

    @Test
    @DisplayName("Test findAll")
    public void testFindAll(){
        // Set up mock object and repository
        User mockUser1 = new User("testUserName1", "testpassWord1");
        User mockUser2 = new User("testUserName2", "testpassWord2");
        doReturn(Arrays.asList(mockUser1,mockUser2)).when(userRepository).findAll();

        // Execute call
        List<User> returnListUser = (List<User>) userService.showAll();

        // Check assertions
        Assertions.assertEquals(2,returnListUser.size(),"findAll should return 2 users");
    }

    @Test
    @DisplayName("Test save User")
    public void testSave(){
        // Set up mock object and repository
        User mockUser = new User("testUserName", "testPassWord");
        doReturn(mockUser).when(userRepository).save(any());

        // Execute call
        User returnUser = userService.create(mockUser);

        Assertions.assertNotNull(returnUser, "The save user should not be null");
    }

    @Test
    @DisplayName("Test update UserName")
    public void updateUserName(){
        // Set up mock object and repository
        User mockUser = new User("testUserName", "testPassWord");
        doReturn(mockUser).when(userRepository).save(mockUser);
        doReturn(mockUser).when(userRepository).getOne(1L);
        String expected = "updatedUserName2020";

        // Execute call
        String actual =  userService.updateUserName(1L,expected).getUserName();

        // Assert UserName updated
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Test update password")
    public void updatePassWord(){
        // Set up mock object and repository
        User mockUser = new User("testUserName", "testPassWord");
        doReturn(mockUser).when(userRepository).save(mockUser);
        doReturn(mockUser).when(userRepository).getOne(1L);
        String expected = "updatedPassWord2020";

        // Execute call
        String actual =  userService.updatePassword(1L,expected).getPassword();

        // Assert PassWord updated
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Test update connection")
    public void updateConnection(){
        // Set up mock object and repository
        User mockUser = new User("testUserName", "testPassWord");
        doReturn(mockUser).when(userRepository).save(mockUser);
        doReturn(mockUser).when(userRepository).getOne(1L);

        // Execute call
        Boolean actual =  userService.updateConnection(1L).getIsConnected();

        // Assert PassWord updated
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("Test update connection false")
    public void updateConnectionFalse(){
        // Set up mock object and repository
        User mockUser = new User("testUserName", "testPassWord");
        doReturn(mockUser).when(userRepository).save(mockUser);
        doReturn(mockUser).when(userRepository).getOne(1L);

        // Execute call
        userService.updateConnection(1L);
        Boolean actual =  userService.updateConnection(1L).getIsConnected();

        // Assert PassWord updated
        Assertions.assertFalse(actual);
    }

}