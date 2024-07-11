package com.fitBuddyGuy.fitBuddyApp.controllers;

import com.fitBuddyGuy.fitBuddyApp.model.Nutrition;
import com.fitBuddyGuy.fitBuddyApp.repository.NutritionRepository;
import com.fitBuddyGuy.fitBuddyApp.repository.UserRepository;
import com.fitBuddyGuy.fitBuddyApp.service.dietServiceImpl;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
public class ProfilePageController {

    private final NutritionRepository nutritionRepository;
    private Nutrition nutrition = new Nutrition();
    private final UserRepository userRepository;
    private dietServiceImpl dietServiceImpl;
    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    public ProfilePageController(UserRepository userRepository, NutritionRepository nutritionRepository,
                                 dietServiceImpl dietServiceImpl) {
        this.userRepository = userRepository;
        this.nutritionRepository = nutritionRepository;
    }

    @RequestMapping(value ="/profile")
    public String profile(Model model, Principal principal) {
        String username = principal.getName();

        model.addAttribute(userRepository.findByUsername(username));
        model.addAttribute(nutritionRepository);
        model.addAttribute(nutrition);

        return "userprofile";
    }

    @PostMapping(value = "profile")
    public String postMeal(Model model) {

        model.addAttribute(nutrition);






        return "userprofile";
    }





}
