package com.fitBuddyGuy.fitBuddyApp.controllers;

import com.fitBuddyGuy.fitBuddyApp.model.Nutrition;
import com.fitBuddyGuy.fitBuddyApp.model.User;
import com.fitBuddyGuy.fitBuddyApp.repository.NutritionRepository;
import com.fitBuddyGuy.fitBuddyApp.repository.UserRepository;
import com.fitBuddyGuy.fitBuddyApp.service.UserService;
import com.fitBuddyGuy.fitBuddyApp.service.dietServiceImpl;
import dto.PasswordDAO;
import dto.UserDAO;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Controller
public class ProfilePageController {


    private final dietServiceImpl dietService;
    private final UserRepository userRepository;
    private final NutritionRepository nutritionRepository;
    private final UserService userService;
    private final PasswordEncoder encoder;

    public ProfilePageController(dietServiceImpl dietService, UserRepository userRepository, NutritionRepository nutritionRepository, UserService userService,
                                 PasswordEncoder encoder) {

        this.dietService = dietService;
        this.userRepository = userRepository;
        this.nutritionRepository = nutritionRepository;
        this.userService = userService;
        this.encoder = new BCryptPasswordEncoder();
    }

    @RequestMapping(value ="/profile", method = RequestMethod.GET)
    public String profile(Model model, Principal principal, Nutrition nutrition) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        int id = user.getId();
        LocalDate date = LocalDate.now();
        LocalDateTime time = LocalDateTime.now();
        int bmr = (int) dietService.getBMR(principal);
        if (user.getUser_goal() == null) {
            user.setUser_goal("maintain");
        }
        int target_calories = dietService.getCalorieGoal(bmr, user.getUser_goal());
        Nutrition nutritionProfile = nutritionRepository.findByEntryDateAndUserID(time, id);

        model.addAttribute(user);
        model.addAttribute(date);

        model.addAttribute(nutrition);
        model.addAttribute("bmr", bmr);
        model.addAttribute("target_calories", target_calories);
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

    @GetMapping( "/profile/editprofile")
    public String editProfile(Model model, Principal principal) {

        String username = principal.getName();
        User editUser = userRepository.findByUsername(username);
        UserDAO user = new UserDAO();


        model.addAttribute("userDAO", user);
        model.addAttribute("editUser", editUser);

        return "editprofile";
    }



    @PostMapping("/profile/editprofile")
    public String updateUserInformation(@Valid @ModelAttribute("userDAO") UserDAO user,
                                        BindingResult result, Principal principal, @ModelAttribute("editUser")
                                            User editUser) {



        if (result.hasErrors()) {
            throw new RuntimeException("form is not validating.");
        }

        else {
            user.setUsername(principal.getName());
            userService.updateUserInformation(user);

            return "editprofile";
        }

    }

    @GetMapping("/profile/changepassword")
    public String changePassword(Model model, Principal principal) {


        String changeSuccessMessage = "Password was changed successfully.";
        String username = principal.getName();
        User userPass = userRepository.findByUsername(username);
        PasswordDAO passwordDAO = new PasswordDAO();
        model.addAttribute(userPass);
        model.addAttribute(passwordDAO);
        model.addAttribute(changeSuccessMessage);
        return "changePassword";
    }

    @PostMapping("/profile/changepassword")
    public String changePassword(@ModelAttribute("passwordDAO") PasswordDAO user,
                                 @RequestParam("password") String password,
                                 @RequestParam("oldpassword") String oldPassword,
                                 @ModelAttribute("currentUser") User userPass, Principal principal) {

        String username = principal.getName();
        User existingUser = userRepository.findByUsername(username);


        if (userService.checkOldPasswordMatches(existingUser, oldPassword)) {
            userService.changePassword(user, password, existingUser);
        } else {
            throw new RuntimeException( oldPassword + " does not match " + existingUser.getPassword());
        }

        return "changePassword";
    }

}
