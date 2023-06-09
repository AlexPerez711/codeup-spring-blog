package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class TestController {

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "Hello World!";
    }

    @GetMapping("/parks/{park}/{message}")
    @ResponseBody
    public String parks(@PathVariable String park, @PathVariable String message) {
        return "<h1>Welcome to " + park + "!</h1><p>" + message + "</p>";
    }

    @GetMapping("/parks")
    @ResponseBody
    public String parks() {
        return "<h1>Welcome to Jurassic Park!</h1>";
    }

    @RequestMapping(path = "/increment/{number}", method = RequestMethod.GET)
    @ResponseBody
    public String addOne(@PathVariable int number) {
        return number + " plus one is " + (number + 1) + "!";
    }

}
