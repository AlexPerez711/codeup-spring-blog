package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.PostCategories;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostCategoriesRepository;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    private final PostRepository postsDao;
    private final UserRepository userDao;
    private final PostCategoriesRepository catDao;

    public PostController(PostRepository postsDao, UserRepository userDao, PostCategoriesRepository catDao) {
        this.postsDao = postsDao;
        this.userDao = userDao;
        this.catDao = catDao;
    }

    @GetMapping("/posts")
    public String viewPosts(Model model) {
        model.addAttribute("posts", postsDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String singlePost(@PathVariable long id, Model model) {
        model.addAttribute("post", postsDao.findById(id).orElse(null));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String showPostForm(Model model) {
        model.addAttribute("categories", catDao.findAll());
        model.addAttribute("post", new Post());
        return "/posts/create";
    }

    @GetMapping("/posts/{id}/edit")
    public String showEditPostForm(@PathVariable long id, Model model) {
        Post existingPost = postsDao.findById(id).orElse(null);
//        if (existingPost == null) {
//            return "error";
//        }
        model.addAttribute("post", existingPost);
        return "/posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@PathVariable long id, @ModelAttribute Post updatedPost) {
        Post post = postsDao.findById(id).orElse(null);
        if (post != null) {
            post.setTitle(updatedPost.getTitle());
            post.setBody(updatedPost.getBody());
            postsDao.save(post);
        }
        return "redirect:/posts";
    }



    @PostMapping("/posts/create")
    public String submitNewPost(@ModelAttribute Post post) {
        User user = userDao.findById(1L).orElse(null);
        if (user == null) {
            return "error";
        }
        post.setUser(user);
        postsDao.save(post);
        return "redirect:/posts";
    }
}
