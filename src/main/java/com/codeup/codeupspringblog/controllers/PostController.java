package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.Post;
import com.codeup.codeupspringblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@Controller
public class PostController {

    private PostRepository postsDao;

    public PostController(PostRepository postsDao) {
        this.postsDao = postsDao;
    }

    @GetMapping("/posts")
    public String index(Model model){

        List<Post> posts = postsDao.findAll();
        model.addAttribute("posts", posts);
//        List<Post> posts = new ArrayList<>();
//        Post p1 = new Post(1L, "Cats", "Cats go meow");
//        Post p2 = new Post(2L, "Dogs", "Dogs go woof");
//        posts.add(p1);
//        posts.add(p2);
//
//        model.addAttribute("posts", posts);
        return "/post/index";
    }

//    @GetMapping("/posts/{id}")
//    public String specificPost(@PathVariable long id, Model model){
//        Post p1 = new Post(1L, "Cats", "Cats go meow");
//
//        model.addAttribute("post", p1);
//        return "/post/show";
//    }

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




