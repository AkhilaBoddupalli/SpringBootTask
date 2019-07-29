package com.stackroute.service;

import com.stackroute.muzix.exceptions.UserAlreadyExistsException;
import com.stackroute.muzix.exceptions.UserNotFoundException;
import com.stackroute.muzix.model.User;
import com.stackroute.muzix.repository.UserRepository;
import com.stackroute.muzix.service.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.sound.midi.Track;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserServiceTest {
    User user;

    //Create a mock for UserRepository
    @Mock
    UserRepository userRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    UserServiceImpl userService;
    List<User> list= null;


    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setLastName("John");
        user.setId(101);
        user.setFirstName("Jenny");
        user.setAge(10);
        list = new ArrayList<>();
        list.add(user);


    }

    @Test
    public void saveUserTestSuccess() throws UserAlreadyExistsException {

        when(userRepository.save((User)any())).thenReturn(user);
        User savedUser = userService.saveUser(user);
        Assert.assertEquals(user,savedUser);

        //verify here verifies that userRepository save method is only called once
        verify(userRepository,times(1)).save(user);

    }
    @Test(expected = UserAlreadyExistsException.class)
    public void saveUserTestFailure() throws UserAlreadyExistsException {

        when(userRepository.save((User)any())).thenReturn(null);
        User savedUser = userService.saveUser(user);
        //Assert.assertEquals(user,savedUser);

        //verify here verifies that userRepository save method is only called once
       // verify(userRepository,times(1)).save(user);

    }
    @Test
    public void getAllUsersSucess()
    {
        userRepository.save(user);
        //stubbing the mock to return specific data
        when(userRepository.findAll()).thenReturn(list);
        List<User> userlist = userService.getAllUsers();
        Assert.assertEquals(list,userlist);
    }
    @Test
    public void updateUser()  {

        when(userService.updateUser(anyInt(),user)).thenReturn(user);
        User updateUser=userService.updateUser(1,user);
        Assert.assertEquals(user,updateUser);
        verify(userRepository,times(1)).save(user);
    }
       @Test
    public void testUpdateTrack() {


        when(userRepository.existsById(user.getId())).thenReturn(true);
        user.setFirstName("bhanu");
        User user1=userService.updateUser(user.getId(),user);
        when(userRepository.save((User)any())).thenReturn(user1);
        Assert.assertEquals("bhanu",user1.getFirstName());
    }

    @Test
    public void testUpdateTrackFailure() {

        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
        user.setFirstName("rama");
       User user1=userService.updateUser(user.getId(),user);
    }


    @Test
    public void deleteTrackTest()
    {
        User user=new User(57,"bhanu","b",22);
        userRepository.delete(user);
        boolean result=userRepository.existsById(57);
        Assert.assertEquals(false,result);

    }
}
