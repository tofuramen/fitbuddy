package com.fitBuddyGuy.fitBuddyApp.service;

import com.fitBuddyGuy.fitBuddyApp.model.User;
import dto.UserDAO;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class UserMapperImpl implements UserMapper {



    public void update(UserDAO from, User to) {
        if ( from == null ) {
            throw new NoSuchElementException("This UserDTO object has no data.");
        }

        if (from.getUser_goal() != null) {
            to.setUser_goal(from.getUser_goal());
        }

        if (from.getWeight() > 85) {
            to.setWeight(from.getWeight());
        }

        if (from.getActivityLevel() != null) {
            to.setActivityLevel(from.getActivityLevel());
        }

        if (from.getAge() >= 18) {
            to.setAge(from.getAge());
        }

    }
}
