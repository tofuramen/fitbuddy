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

    //this will be the method that allows you to edit the user's profile
    public void editProfile(Principal principal) {

    }

    //this method will generate the user's calorie target
    public double getBMR(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        double weight = user.getWeight() * 0.45359237; //converting weight in KG
        double height = user.getHeight() * 2.54; //converting height to cm
        String activityLevel = user.getActivityLevel();
        String gender = user.getGender();
        int age = user.getAge();
        double bmr = 0;

        if (activityLevel.equals("sedentary") && gender.equals("male")) {
            bmr = 10 * weight + 6.25 * height - 5 * age + 5 * 1.2;
        }

        if (activityLevel.equals("lightly-active") && gender.equals("male")) {
            bmr = 10 * weight + 6.25 * height - 5 * age + 5 * 1.375;
        }

        if (activityLevel.equals("moderately-active") && gender.equals("male")) {
            bmr = 10 * weight + 6.25 * height - 5 * age + 5 * 1.55;
        }

        if (activityLevel.equals("very-active") && gender.equals("male")) {
            bmr = 10 * weight + 6.25 * height - 5 * age + 5 * 1.725;
        }

        if (activityLevel.equals("sedentary") && gender.equals("female")) {
            bmr = 10 * weight + 6.25 * height - 5 * age - 161 * 1.2;
        }

        if (activityLevel.equals("lightly-active") && gender.equals("female")) {
            bmr = 10 * weight + 6.25 * height - 5 * age - 161 * 1.375;
        }

        if (activityLevel.equals("moderately-active") && gender.equals("female")) {
            bmr = 10 * weight + 6.25 * height - 5 * age - 161 * 1.55;
        }

        if (activityLevel.equals("very-active") && gender.equals("female")) {
            bmr = 10 * weight + 6.25 * height - 5 * age - 161 * 1.725;
        }

        return bmr;
    }

    public int getCalorieGoal(int bmr, String userGoal) {

        double calorieGoal = 0;
        double maintenance = bmr *1.2;

        if (userGoal.equals("maintain")) {
            calorieGoal = maintenance;
        }

        if (userGoal.equals("easyLoss")) {
            calorieGoal = maintenance - 250;
        }

        if(userGoal.equals("normalLoss")) {
            calorieGoal = maintenance - 500;
        }

        if (userGoal.equals("aggressiveLoss")) {
            calorieGoal = maintenance - 1000;
        }

        if(userGoal.equals("gainWeight")) {
            calorieGoal = maintenance + 500;
        }


        return (int) calorieGoal;
    }



}
