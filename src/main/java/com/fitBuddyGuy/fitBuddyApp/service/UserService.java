package com.fitBuddyGuy.fitBuddyApp.service;

import com.fitBuddyGuy.fitBuddyApp.model.Role;
import com.fitBuddyGuy.fitBuddyApp.model.User;
import com.fitBuddyGuy.fitBuddyApp.repository.UserRepository;
import dto.PasswordDAO;
import dto.UserDAO;
import jakarta.validation.Valid;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapperImpl userMapper;
    private final UserPasswordMapper passwordMapper;
    private SessionFactory sessionFactory;

    PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository theUserRepository, UserMapperImpl userMapper, UserPasswordMapper passwordMapper) {
        userRepository = theUserRepository;
        this.userMapper = userMapper;
        this.passwordMapper = passwordMapper;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void save(User theUser) {

        String encodedPassword = this.passwordEncoder.encode(theUser.getPassword());//the user service hashes the password
        theUser.setPassword(encodedPassword);
        theUser.setRole(Role.USER);

        userRepository.save(theUser);
    }


    @Transactional
    public void updateUserInformation(@Valid UserDAO editUser) {

        if (editUser.getUsername() == null) {
            throw new RuntimeException("username doesn't exist.");
        }
        User existingUser = userRepository.findByUsername(editUser.getUsername());
        //map the userDao to existingUser using mapStruct
        userMapper.update(editUser, existingUser);

        userRepository.save(existingUser);

    }

    public void changePassword(PasswordDAO user, String newPassword, User existingUser) {

        String password = this.passwordEncoder.encode(newPassword);
        user.setPassword(password);

        if (existingUser == null) {
            throw new RuntimeException("existing user does not exist.");
        }


        //passwordMapper.updatePassword(user, existingUser);
        existingUser.setPassword(password);

        userRepository.save(existingUser);
    }

    public boolean checkOldPasswordMatches(User user, String oldPassword) {


        if (user == null) {
            throw new RuntimeException("existing user " + user.getUsername() + " does not exist.");
        }


        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            return true;
        }


        return false;
    }






}
