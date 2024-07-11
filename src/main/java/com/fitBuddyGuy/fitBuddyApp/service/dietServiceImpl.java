package com.fitBuddyGuy.fitBuddyApp.service;

import com.fitBuddyGuy.fitBuddyApp.model.Meal;
import com.fitBuddyGuy.fitBuddyApp.model.Nutrition;
import com.fitBuddyGuy.fitBuddyApp.repository.NutritionRepository;
import org.springframework.stereotype.Service;

@Service
public class dietServiceImpl  {

    private final NutritionRepository nutritionRepository;
    private Nutrition nutrition;
    private Meal meal;

    public dietServiceImpl(NutritionRepository nutritionRepository) {
        this.nutritionRepository = nutritionRepository;
    }



    public void save(Nutrition nutrition, int date) {
        //if today's date's entry_date and user_id already exists then merge. Add each macro to relative column

        //if no entry for today's date, user id and

        nutritionRepository.save(nutrition);
    }











}
