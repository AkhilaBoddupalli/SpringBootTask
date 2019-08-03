package com.stackroute.muzix.service;

import com.stackroute.muzix.exceptions.UserAlreadyExistsException;
import com.stackroute.muzix.exceptions.UserNotFoundException;
import com.stackroute.muzix.model.User;
import com.stackroute.muzix.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //marks class as a service
public class UserServiceImpl implements UserService {
UserRepository userRepository;
@Autowired
public UserServiceImpl(UserRepository userRepository)
{
    this.userRepository=userRepository;
}
    @Override

    public User saveUser(User user) throws UserAlreadyExistsException {


    if(userRepository.existsById(user.getId()))
    {
        throw new UserAlreadyExistsException("user already exists");
    }
        User savedUser=userRepository.save(user);
    if(savedUser==null)
    {
        throw new UserAlreadyExistsException("user alraedy exists");
    }
    return savedUser;
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public boolean deleteUser(int id) throws UserNotFoundException
    {
        //userRepository.deleteById(id);
        Optional<User> user1 = userRepository.findById(id);

        if(!user1.isPresent())
        {
            throw new UserNotFoundException("User Not Found");
        }

        try {

            userRepository.delete(user1.get());

            return true;

        }
        catch (Exception exception)
        {
            return false;
        }
    }


    @Override
    public List<User> getUserByName(String firstName) throws UserNotFoundException {
    List<User> user=userRepository.getUserByName(firstName);
    if(user.isEmpty())
    {
        throw new UserNotFoundException("User not found");
    }

        return userRepository.getUserByName(firstName);
    }


    @Override
    public User updateUser(int id,User user) {
       User updateUser=userRepository.save(user);
       return updateUser;
    }
}
