package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    @GetMapping("/posts")
    public String index(Model model){
        List<Post> posts = new ArrayList<>();
        Post p1 = new Post("Cats", "Cats go meow");
        Post p2 = new Post("Dogs", "Dogs go woof");
        posts.add(p1);
        posts.add(p2);

        model.addAttribute("posts", posts);
        return "/post/index";
    }
    @GetMapping("/posts/{id}")
    public String specificPost(@PathVariable long id, Model model){
        Post p1 = new Post("Cats", "Cats go meow");

        model.addAttribute("post", p1);
        return "/post/show";
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




