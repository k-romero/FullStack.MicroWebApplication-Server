package com.cjk.stackcast.services;

import com.cjk.stackcast.models.DAOUser;
import com.cjk.stackcast.repositories.UserDaoRepository;

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
public class DAOUserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserDaoRepository userDaoRepository;


    @Test
    @DisplayName("Test findById Success")
    public void testFindByIdSuccess(){
        // Set up mock object and repository
        DAOUser mockDAOUser = new DAOUser("testUserName", "testPassWord");
        doReturn(Optional.of(mockDAOUser)).when(userDaoRepository).findById(1L);

        // Execute call
        Optional<DAOUser> returnUser = userService.showUser(1L);

        Assertions.assertTrue(returnUser.isPresent(), "No User was found when there should be");
        Assertions.assertSame(returnUser.get(), mockDAOUser, "Models don't match up");
    }

    @Test
    @DisplayName("Test findById Fail")
    public void testFindByIdFail(){
        // Set up mock object and repository
        doReturn(Optional.empty()).when(userDaoRepository).findById(1L);

        // Execute call
        Optional<DAOUser> returnUser = userService.showUser(1L);

        Assertions.assertFalse(returnUser.isPresent(), "User was found when there shouldn't be");
    }

    @Test
    @DisplayName("Test findAll")
    public void testFindAll(){
        // Set up mock object and repository
        DAOUser mockDAOUser1 = new DAOUser("testUserName1", "testPassWord1");
        DAOUser mockDAOUser2 = new DAOUser("testUserName2", "testPassWord2");
        doReturn(Arrays.asList(mockDAOUser1, mockDAOUser2)).when(userDaoRepository).findAll();

        // Execute call
        List<DAOUser> returnListDAOUser = (List<DAOUser>) userService.showAll();

        // Check assertions
        Assertions.assertEquals(2, returnListDAOUser.size(),"findAll should return 2 users");
    }

    @Test
    @DisplayName("Test findByUserName Success")
    public void testFindByUserNameSuccess(){
        // Set up mock object and repository
        DAOUser mockDAOUser = new DAOUser("testUserName", "testPassWord");
        doReturn(Optional.of(mockDAOUser)).when(userDaoRepository).findUserByUserName("testUserName");

        // Execute call
        Optional<DAOUser> returnUser = userService.findByUserName("testUserName");

        Assertions.assertTrue(returnUser.isPresent(), "No User was found when there should be");
        Assertions.assertSame(returnUser.get(), mockDAOUser, "Models don't match up");
    }

    @Test
    @DisplayName("Test save User")
    public void testSave(){
        // Set up mock object and repository
        DAOUser mockDAOUser = new DAOUser("testUserName", "testPassWord");
        doReturn(mockDAOUser).when(userDaoRepository).save(any());

        // Execute call
        DAOUser returnDAOUser = userService.create(mockDAOUser);

        Assertions.assertNotNull(returnDAOUser, "The save user should not be null");
    }

    @Test
    @DisplayName("Test update UserName")
    public void updateUserName(){
        // Set up mock object and repository
        DAOUser mockDAOUser = new DAOUser("testUserName", "testPassWord");
        doReturn(mockDAOUser).when(userDaoRepository).save(mockDAOUser);
        doReturn(mockDAOUser).when(userDaoRepository).getOne(1L);
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
        DAOUser mockDAOUser = new DAOUser("testUserName", "testPassWord");
        doReturn(mockDAOUser).when(userDaoRepository).save(mockDAOUser);
        doReturn(mockDAOUser).when(userDaoRepository).getOne(1L);
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
        DAOUser mockDAOUser = new DAOUser("testUserName", "testPassWord");
        doReturn(mockDAOUser).when(userDaoRepository).save(mockDAOUser);
        doReturn(mockDAOUser).when(userDaoRepository).getOne(1L);

        // Execute call
        Boolean actual =  userService.updateConnection(1L).getIsConnected();

        // Assert PassWord updated
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("Test update connection false")
    public void updateConnectionFalse(){
        // Set up mock object and repository
        DAOUser mockDAOUser = new DAOUser("testUserName", "testPassWord");
        doReturn(mockDAOUser).when(userDaoRepository).save(mockDAOUser);
        doReturn(mockDAOUser).when(userDaoRepository).getOne(1L);

        // Execute call
        userService.updateConnection(1L);
        Boolean actual =  userService.updateConnection(1L).getIsConnected();

        // Assert PassWord updated
        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("Test delete User success")
    public void deleteUserTest(){
        // Set up mock object and repository
        DAOUser mockDAOUser = new DAOUser("testUserName", "testPassWord");
        doReturn(mockDAOUser).when(userDaoRepository).getOne(1L);

        // Execute call
        Boolean actual =  userService.deleteUser(1L);

        // Assert PassWord updated
        Assertions.assertTrue(actual);
    }


}