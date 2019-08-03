package com.stackroute.muzix.service;

import com.stackroute.muzix.model.User;
import com.stackroute.muzix.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //marks the class as service
public class UserServiceImpl implements UserService {
UserRepository userRepository;
@Autowired
public UserServiceImpl(UserRepository userRepository)
{
    this.userRepository=userRepository;
}
    @Override
    public User saveUser(User user) {
    User savedUser=userRepository.save(user);
        return savedUser;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(int userid) {
        return null;
    }

    @Override
    public User updateUser(int id,User user) {
       User updateUser=userRepository.save(user);
       return updateUser;
    }
}
