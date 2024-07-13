package com.fitBuddyGuy.fitBuddyApp.controllers;

import com.fitBuddyGuy.fitBuddyApp.model.Nutrition;
import com.fitBuddyGuy.fitBuddyApp.model.User;
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


@Controller
public class ProfilePageController {


    private final dietServiceImpl dietService;
    private final UserRepository userRepository;


    public ProfilePageController(dietServiceImpl dietService, UserRepository userRepository) {
        this.dietService = dietService;
        this.userRepository = userRepository;
    }

    @RequestMapping(value ="/profile", method = RequestMethod.GET)
    public String profile(Model model, Principal principal, Nutrition nutrition) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        LocalDate localDate = LocalDate.now();


        model.addAttribute(user);
        model.addAttribute(localDate);

        model.addAttribute(nutrition);


        return "userprofile";
    }

    @RequestMapping(value = "profile", method = RequestMethod.POST)
    public String profile(@Valid @ModelAttribute("nutrition") Nutrition nutrition,
                           BindingResult bindingResult, Principal principal) {

        if (bindingResult.hasErrors()) {
            return "profile";
        } else {

        dietService.save(nutrition, principal);


        return  "redirect:/profile"; }
    }





}
