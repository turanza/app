package com.vocabulary.app.controller;

import com.vocabulary.app.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WordController {
    private final WordService wordService;

    @Autowired
    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/auth/word-list/{token}/{userName}")
    public String getAll(Model model, @PathVariable("token")String token,
                         @PathVariable("userName") String userName){
        model.addAttribute("token", token);
        model.addAttribute("userName", userName);
        return "auth/word-list";
    }
}
