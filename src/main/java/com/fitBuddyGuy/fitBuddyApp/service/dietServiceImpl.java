package com.fitBuddyGuy.fitBuddyApp.service;

import com.fitBuddyGuy.fitBuddyApp.model.Meal;
import com.fitBuddyGuy.fitBuddyApp.model.Nutrition;
import com.fitBuddyGuy.fitBuddyApp.model.User;
import com.fitBuddyGuy.fitBuddyApp.repository.NutritionRepository;
import com.fitBuddyGuy.fitBuddyApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;

@Service
public class dietServiceImpl  {

    private final NutritionRepository nutritionRepository;
    private UserRepository userRepository;
    private Meal meal;
    private LocalDateTime date;

    @Autowired
    public dietServiceImpl(NutritionRepository nutritionRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.nutritionRepository = nutritionRepository;
    }


    public void save(Nutrition thisNutrition, Principal principal) {
        date = LocalDateTime.now();
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        int user_id = user.getId();

        thisNutrition.setUser_id(user_id);
        thisNutrition.setEntry_date(date);


        nutritionRepository.save(thisNutrition);
    }




}
