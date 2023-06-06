package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

@Controller
public class RollDiceController {
    @GetMapping("/roll-dice")
    public String ShowChoices(){
        return"/roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String ShowResults(@PathVariable int n, Model model){
        int random = (int) Math.floor((Math.random() * 6) + 1);
        System.out.println(random);
        model.addAttribute("randomNum", random);

        boolean result = random == n;

        model.addAttribute("result", result);
        return"/roll-dice";
    }

}
