package com.fitBuddyGuy.fitBuddyApp.service;

import com.fitBuddyGuy.fitBuddyApp.model.Nutrition;
import com.fitBuddyGuy.fitBuddyApp.model.User;
import com.fitBuddyGuy.fitBuddyApp.repository.NutritionRepository;
import com.fitBuddyGuy.fitBuddyApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;

@Service
public class dietServiceImpl  {

    private final NutritionRepository nutritionRepository;
    private UserRepository userRepository;
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
        thisNutrition.setWeight(user.getWeight());
        thisNutrition.setUser_id(user_id);
        thisNutrition.setEntry_date(date);


        nutritionRepository.save(thisNutrition);
    }

    public boolean dateExists(Nutrition nutrition, Principal principal) {
        date = LocalDateTime.now();
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        int id = user.getId();

        Nutrition nutrition1 = nutritionRepository.findByEntryDateAndUserID(date,id);


        if (nutrition.getEntry_date() == nutrition1.getEntry_date()) {
            return true;
        }

        return false;
    }

    //creating a method to update a row by date and user_id
    @Transactional
    public void updateMacros(Nutrition nutrition, Principal principal,
                       int protein, int carbs, int fat, int cals) {

        LocalDateTime dateTime = LocalDateTime.now();
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        int id = user.getId();

        Nutrition nutrition1 = nutritionRepository.findByEntryDateAndUserID(dateTime, id);

        nutrition1.setProtein(nutrition1.getProtein() + protein);
        nutrition1.setCarbs(nutrition1.getCarbs() + carbs);
        nutrition1.setFat(nutrition1.getFat() + fat);
        nutrition1.setTotal_calories(nutrition1.getTotal_calories() + cals);

    }



}
