package com.fitBuddyGuy.fitBuddyApp.controllers;

import com.fitBuddyGuy.fitBuddyApp.model.User;
import com.fitBuddyGuy.fitBuddyApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class signupController  {


    private final UserService userService;

    public signupController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }


    @RequestMapping("/signup")
    public String signup(Model theModel) {

        User theUser = new User();

        theModel.addAttribute("user", theUser);

        return "signup";
    }

    @PostMapping("/confirmation")
    public String confirmation(
            @Valid @ModelAttribute("user") User theUser,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "signup";
        } else {

            //userService.save(theUser);

            return "confirmation";
        }
    }


}
