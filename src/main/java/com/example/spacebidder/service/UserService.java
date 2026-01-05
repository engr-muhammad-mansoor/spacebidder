package com.example.spacebidder.service;

import com.example.spacebidder.model.User;

public interface UserService {
    void updateUser(User user);

    void saveUser(User user);

    boolean CheckUserExist(String clientEmail);
}