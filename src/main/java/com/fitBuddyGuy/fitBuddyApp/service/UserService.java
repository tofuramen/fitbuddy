package com.fitBuddyGuy.fitBuddyApp.service;

import com.fitBuddyGuy.fitBuddyApp.model.User;
import com.fitBuddyGuy.fitBuddyApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService  {

    private final UserRepository userRepository;


    PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository theUserRepository) {
        userRepository = theUserRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void save(User theUser) {

        String encodedPassword = this.passwordEncoder.encode(theUser.getPassword());//the user service hashes the password
        theUser.setPassword(encodedPassword);

        userRepository.save(theUser);
    }



}
