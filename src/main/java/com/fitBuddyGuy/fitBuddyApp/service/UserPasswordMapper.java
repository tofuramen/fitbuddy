package com.fitBuddyGuy.fitBuddyApp.service;

import com.fitBuddyGuy.fitBuddyApp.model.User;
import dto.PasswordDAO;
import dto.UserDAO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class UserPasswordMapper implements UserMapper {

    private PasswordEncoder encoder = new BCryptPasswordEncoder();




    public void updatePassword(PasswordDAO from, User to) {
        if ( from == null ) {
            throw new NoSuchElementException("This UserDTO object has no data.");
        }

        if (from.getPassword() != null) {
            String password = encoder.encode(from.getPassword());
            to.setPassword(password);
        }
    }


    public void update(UserDAO from, User to) {

    }


}
