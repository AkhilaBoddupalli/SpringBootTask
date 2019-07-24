package com.stackroute.muzix.service;

import com.stackroute.muzix.model.User;

import java.util.List;

public interface UserService {
    public User saveUser(User user);
    public List<User> getAllUsers();
    public void deleteUser(int id);
    public User getUserById(int userid);
    public User updateUser(int id,User user);

}
