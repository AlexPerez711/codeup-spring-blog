package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String posts() {
        return "posts index page";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String postsById(@PathVariable long id) {
        return "<h1>Viewing post with an id of:" + id + " .</h1>";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String getCreate() {
        return "<h1>View the form for creating a post</h1>";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String postCreate() {
        return "<h1>Create a new post </h1>";
    }
}
