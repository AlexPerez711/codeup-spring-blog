package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostCategoriesRepository;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import com.codeup.codeupspringblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    private final PostRepository postsDao;
    private final UserRepository userDao;
    private final PostCategoriesRepository catDao;
    private final EmailService emailService;

    public PostController(PostRepository postsDao, UserRepository userDao, PostCategoriesRepository catDao, EmailService emailService) {
        this.postsDao = postsDao;
        this.userDao = userDao;
        this.catDao = catDao;
        this.emailService = emailService;
    }

    @GetMapping("/posts")

    public String viewPosts(Model model) {
        model.addAttribute("posts", postsDao.findAll());
        return "posts/index";
    }


    @GetMapping("/posts/{id}")
    public String singlePost(@PathVariable long id, Model model) {
        model.addAttribute("post", postsDao.findById(id).get());
        return "posts/show";
    }


    @GetMapping("/posts/create")
    public String showPostForm(Model model) {
        // show categories in form
        model.addAttribute("categories", catDao.findAll());
        // pass a new Post object to the form
        model.addAttribute("post", new Post());
        return "/posts/create";
    }


    @PostMapping("/posts/create")
//    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    public String submitNewPost(@ModelAttribute Post post) {
        User user = userDao.findById(1L).get();
        post.setUser(user);

        emailService.prepareAndSend(post, "New Post Created!", post.getBody());
        postsDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String showEditForm(@PathVariable long id, Model model) {
        if(postsDao.findById(id).isPresent()) {
            Post postToEdit = postsDao.findById(id).get();
            model.addAttribute("post", postToEdit);
        }
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@ModelAttribute Post newPost) {
        User user = userDao.findById(1L).get();
        newPost.setUser(user);
        postsDao.save(newPost);
        return "redirect:/posts";
    }



}