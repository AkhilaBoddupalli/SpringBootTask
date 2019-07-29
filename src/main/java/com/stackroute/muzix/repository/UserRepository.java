package com.stackroute.muzix.repository;

import com.mongodb.Mongo;
import com.stackroute.muzix.model.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
@Repository
public interface UserRepository extends MongoRepository<User,Integer> {


//        @Query(value = "SELECT u FROM User u WHERE u.firstName = ?1")
//        public List<User> getUserByName(String firstName);
    }


