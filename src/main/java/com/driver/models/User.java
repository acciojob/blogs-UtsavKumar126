package com.driver.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    private String username;
    private String password;
    private String firstName;
    private String lastName;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Blog> blogList;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(int id, String userName, String password, String firstName, String lastName, List<Blog> blogList) {
        this.id = id;
        this.username = userName;
        this.password = password;
        this.firstName = "test";
        this.lastName = "test";
        this.blogList = new ArrayList<>();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBlogList(List<Blog> blogList) {
        this.blogList = blogList;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Blog> getBlogList() {
        return blogList;
    }
}
