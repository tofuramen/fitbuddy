package com.fitBuddyGuy.fitBuddyApp.controllers;

import com.fitBuddyGuy.fitBuddyApp.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
public class ProfilePageController {

    UserRepository userRepository;

    public ProfilePageController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value ="/profile")
    public String profile(Model model, Principal principal) {
        String username = principal.getName();
        model.addAttribute(userRepository.findByUsername(username));

        return "userprofile";
    }









}
