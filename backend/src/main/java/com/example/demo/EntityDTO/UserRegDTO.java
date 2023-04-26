package com.example.demo.EntityDTO;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;

public class UserRegDTO {
    private String phoneNumber;
    private String password;
    private String name;
    private Role role;

    public UserRegDTO(String phoneNumber, String password, String name, Role role) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public User toUser(){
        return User.builder().phoneNumber(phoneNumber).password(password).role(role).name(name).build();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
