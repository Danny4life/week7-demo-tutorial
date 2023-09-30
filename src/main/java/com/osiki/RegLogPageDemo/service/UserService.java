package com.osiki.RegLogPageDemo.service;

import com.osiki.RegLogPageDemo.model.UserModel;
import com.osiki.RegLogPageDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    //dependency injection
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel registerUser(String login, String email, String password){
        if(login == null || password == null){
            return null;
        }else {
            if(userRepository.findFirstByLogin(login).isPresent()){
                System.out.println("User Already Exists");
                return null;
            }

            UserModel user = new UserModel();

            user.setLogin(login);
            user.setEmail(email);
            user.setPassword(password);

            userRepository.save(user);

            return user;

        }
    }
}
