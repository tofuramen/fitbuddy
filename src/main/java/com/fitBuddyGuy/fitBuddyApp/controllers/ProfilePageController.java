package com.fitBuddyGuy.fitBuddyApp.controllers;

import com.fitBuddyGuy.fitBuddyApp.model.Nutrition;
import com.fitBuddyGuy.fitBuddyApp.model.User;
import com.fitBuddyGuy.fitBuddyApp.repository.NutritionRepository;
import com.fitBuddyGuy.fitBuddyApp.repository.UserRepository;
import com.fitBuddyGuy.fitBuddyApp.service.dietServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Controller
public class ProfilePageController {


    private final dietServiceImpl dietService;
    private final UserRepository userRepository;
    private final NutritionRepository nutritionRepository;


    public ProfilePageController(dietServiceImpl dietService, UserRepository userRepository, NutritionRepository nutritionRepository) {
        this.dietService = dietService;
        this.userRepository = userRepository;
        this.nutritionRepository = nutritionRepository;
    }

    @RequestMapping(value ="/profile", method = RequestMethod.GET)
    public String profile(Model model, Principal principal, Nutrition nutrition) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        int id = user.getId();
        LocalDate date = LocalDate.now();
        LocalDateTime time = LocalDateTime.now();
        int bmr = (int) dietService.getBMR(principal);
        Nutrition nutritionProfile = nutritionRepository.findByEntryDateAndUserID(time, id);

        model.addAttribute(user);
        model.addAttribute(date);

        model.addAttribute(nutrition);
        model.addAttribute("bmr", bmr);
        model.addAttribute("nutritionProfile", nutritionProfile);



        return "userprofile";
    }

    @RequestMapping(value = "profile", method = RequestMethod.POST)
    public String profile(@Valid @ModelAttribute("nutrition") Nutrition nutrition,
                           BindingResult bindingResult, Principal principal) {

        if (bindingResult.hasErrors()) {
            return "userprofile";
        }


        LocalDateTime dateTime = LocalDateTime.now();
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        int id = user.getId();

        if (!(nutritionRepository.findByEntryDateAndUserID(dateTime,id) == null)) {
            int protein = nutrition.getProtein();
            int carbs = nutrition.getCarbs();
            int fat = nutrition.getFat();
            int calories = nutrition.getTotal_calories();

            dietService.updateMacros(nutrition, principal, protein, carbs, fat, calories);

        } else {

            dietService.save(nutrition, principal);
        }

        return  "redirect:/profile";
    }

    @RequestMapping("/editprofile")
    public String editProfile() {
        return "redirect:/editprofile";
    }





}
