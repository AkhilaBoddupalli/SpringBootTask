package com.stackroute.repository;

import com.stackroute.muzix.MuzixApplication;
import com.stackroute.muzix.model.User;
import com.stackroute.muzix.repository.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sound.midi.Track;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest //to test spring data jpa repositories
@ContextConfiguration(classes = MuzixApplication.class)
public class RepositoryTest {
    @Autowired
    UserRepository userRepository;
    User user;

    @Before
    public void setUp()
    {
        user = new User();
        user.setAge(10);
        user.setFirstName("John");
        user.setId(101);
        user.setLastName("Jenny");

    }

    @After
    public void tearDown(){

        userRepository.deleteAll();
    }


    @Test
    public void testSaveUser(){
        userRepository.save(user);
        User fetchUser = userRepository.findById(user.getId()).get();
        Assert.assertEquals(101,fetchUser.getId());

    }

    @Test
    public void testSaveUserFailure(){
        User testUser = new User(22,"john","jhonny",11);
        userRepository.save(user);
        User fetchUser = userRepository.findById(user.getId()).get();
        Assert.assertNotSame(testUser,user);
    }
    @Test
    public void testGetAllUser(){
        User u = new User(23,"Jaya","MurthyK",102);
        User u1 = new User(24,"Jaya","Murthy",103);
        userRepository.save(u);
        userRepository.save(u1);

        List<User> list = userRepository.findAll();
        Assert.assertEquals("Jaya",list.get(0).getFirstName());

    }
    @Test
    public void updateTrack()
    {
        User user=new User(106,"akhila","b",22);

        userRepository.save(user);
        userRepository.findById(user.getId()).get().setFirstName("akhila");
        List<User> list=userRepository.findAll();
        Assert.assertEquals("akhila",list.get(0).getFirstName());
    }

    @Test
    public void deleteTrack()
    {
        User user=new User(106,"akhila","b",22);
        userRepository.save(user);
        userRepository.deleteById(106);
        boolean result=userRepository.existsById(106);
        Assert.assertEquals(false,result);

    }

}
