package com.example.spacebidder.service;

import com.example.spacebidder.model.User;
import com.example.spacebidder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void updateUser(User user) {
        long id =userRepository.findUserByClientEmail(user.getClientEmail()).getClientId();
        user.setClientId(id);
        userRepository.save(user);
    }
    @Override
    public void saveUser(User user) {
            userRepository.save(user);
        }

    @Override
    public boolean CheckUserExist(String clientEmail) {
        boolean ch = userRepository.existsByClientEmail(clientEmail);
     if(ch){
         return true;
     }
     else return false;
    }
}
