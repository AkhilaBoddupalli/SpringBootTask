package com.stackroute.muzix.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity //marks class to a table
@Data //
@NoArgsConstructor //generates constructor with no arguments
@AllArgsConstructor //generates constructor with all arguments
@Builder //produces complex builder APIs for your classes.
public class User {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private int age;


}
