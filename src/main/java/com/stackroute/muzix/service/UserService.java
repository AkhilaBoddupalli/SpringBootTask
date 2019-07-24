package com.stackroute.muzix.service;

import com.stackroute.muzix.exceptions.UserAlreadyExistsException;
import com.stackroute.muzix.exceptions.UserNotFoundException;
import com.stackroute.muzix.model.User;

import java.util.List;

public interface UserService {
    public User saveUser(User user) throws UserAlreadyExistsException;
    public List<User> getAllUsers();
    public void deleteUser(int id) ;
    public List<User> getUserByName(String firstName) throws UserNotFoundException;
    public User updateUser(int id,User user);

}
