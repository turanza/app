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

    @GetMapping("/auth/users/{token}/{userName}")
    public String findAll(Model model, @PathVariable("token") String token,
                          @PathVariable("userName")String userName){
        List<User> userList = userService.findAll();
        model.addAttribute("users",userList);
        model.addAttribute("token",token);
        model.addAttribute("userName",userName);
        return "auth/user-list";
    }

    @GetMapping("/users/update-user/{id}/{token}/{userName}")
    public String updateUserForm(@PathVariable("id") Long id, Model model, @PathVariable("token") String token,
                                 @PathVariable ("userName") String userName){
        User user = userService.findById(id);

        model.addAttribute("user",user);
        model.addAttribute("token", token);
        model.addAttribute("userName", userName);
        return "auth/user-update";
    }

    @PostMapping("/users/update-user/{token}/{userName}")
    public String updateUser (User user, @PathVariable("token") String token,
                              @PathVariable("userName") String userName){

        if (user.getPassword().equals(""))
            userService.updateUserWithOutPass(user);
        else
            userService.updateUser(user);
        return "redirect:/users/"+token+'/'+userName;
    }

    @GetMapping("/users/create-user/{token}/{userName}")
    public String createUserForm(Model model, User user, @PathVariable("token")String token,
                                 @PathVariable("userName") String userName){
        model.addAttribute("token", token);
        model.addAttribute("userName", userName);
        return "auth/user-create";
    }

    @PostMapping("/users/create-user/{token}/{userName}")
    public String createUser (UserCreateDto user, @PathVariable("token")String token,
                              @PathVariable("userName") String userName){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User findUser = userService.findByUsername(user.getLogin());
        if (findUser!=null){
            return "redirect:/users/update-user/"+findUser.getId()+'/'+token+'/'+userName;
        }
        userService.saveUser(user.toUser());
        return "redirect:/users"+'/'+token+'/'+userName;
    }
}
