package com.stackroute.muzix.repository;

import com.stackroute.muzix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository //ndicates that an annotated class is a repository,
public interface UserRepository extends JpaRepository<User,Integer> {

}
