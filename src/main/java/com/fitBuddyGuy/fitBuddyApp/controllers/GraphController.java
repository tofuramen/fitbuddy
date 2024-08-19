package com.fitBuddyGuy.fitBuddyApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.HashMap;

@Controller
public class GraphController {

    @GetMapping("/profile/WeightGraph")
    public String WeightGraph(Model model) {

        HashMap<LocalDateTime,Integer> dateTimeIntegerHashMap = new HashMap<>();



        

        return "weightgraph";
    }



}
