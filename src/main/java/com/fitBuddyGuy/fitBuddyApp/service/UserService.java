package com.fitBuddyGuy.fitBuddyApp.service;

import com.fitBuddyGuy.fitBuddyApp.model.User;
import com.fitBuddyGuy.fitBuddyApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository theUserRepository) {
        userRepository = theUserRepository;
    }

    public void save(User theUser) {
        userRepository.save(theUser);
    }



}
