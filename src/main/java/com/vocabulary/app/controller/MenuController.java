package com.vocabulary.app.controller;

import com.vocabulary.app.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/auth/menu/{token}/{userName}")
    public String showMenu(Model model, @PathVariable("token") String token,
                           @PathVariable("userName") String userName){
        model.addAttribute("token", token);
        model.addAttribute("userName", userName);
        return "auth/menu-list";
    }
}
