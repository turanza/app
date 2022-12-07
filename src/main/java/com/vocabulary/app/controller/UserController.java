package com.vocabulary.app.controller;

import com.vocabulary.app.dto.UserCreateDto;
import com.vocabulary.app.model.User;
import com.vocabulary.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/users")
    public String findAll(Model model){
        List<User> userList = userService.findAll();
        model.addAttribute("users",userList);
        return "user-list";
    }

    @GetMapping("/users/update-user/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user",user);
        return "user-update";
    }

    @PostMapping("/users/update-user")
    public String updateUser (User user){
        if (user.getPassword().equals(""))
            userService.updateUserWithOutPass(user);
        else
            userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/create-user")
    public String createUserForm(Model model, User user){
        return "user-create";
    }

    @PostMapping("/users/create-user")
    public String createUser (UserCreateDto user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User findUser = userService.findByUsername(user.getLogin());
        if (findUser!=null){
            return "redirect:/users/update-user/"+findUser.getId();
        }
        userService.saveUser(user.toUser());
        return "redirect:/users";
    }
}
