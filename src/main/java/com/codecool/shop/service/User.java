package com.codecool.shop.service;

public class User {

    private String userName;
    private String password;
    private Integer id;

    public User(String userName, String password) {
        this.userName=userName;
        this.password=password;
    }

    public String getUserNameEmail() {
        return userName;
    }

    public void setUserNameEmail(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
