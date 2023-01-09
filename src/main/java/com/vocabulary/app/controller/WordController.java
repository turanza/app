package com.vocabulary.app.controller;

import com.vocabulary.app.dto.WordCreateDto;
import com.vocabulary.app.model.Word;
import com.vocabulary.app.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

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
        List<Word> words = new ArrayList<>();
        words = wordService.getWords();
        model.addAttribute("words", words);
        model.addAttribute("token", token);
        model.addAttribute("userName", userName);
        return "auth/word-list";
    }

    @GetMapping("/word/word-update/{id}/{token}/{userName}")
    public String updateWord (Model model, @PathVariable("token") String token,
                                     @PathVariable("userName")String userName,
                                     @PathVariable("id")Long id){
        Word word = wordService.getById(id);
        model.addAttribute("word", word);
        model.addAttribute("token", token);
        model.addAttribute("userName", userName);
        return "auth/word-update";
    }

    @PostMapping("/word/word-update/{token}/{userName}")
    public String updateWordForm (Word word, @PathVariable("token")String token,
                                  @PathVariable("userName")String userName){
        wordService.updateWord(word);

        return "redirect:/auth/word-list/"+token+"/"+userName;
    }

    @GetMapping("/word/word-create/{token}/{userName}")
    public String createWord(Model model,Word word, @PathVariable("token")String token,
                             @PathVariable("userName")String userName){
        model.addAttribute("token", token);
        model.addAttribute("userName", userName);
        return "auth/word-create";
    }

    @PostMapping("/word/word-create/{token}/{userName}")
    public String createWordForm(WordCreateDto word, @PathVariable("token") String token,
                                 @PathVariable("userName") String userName){
        wordService.saveWord(word.toWord());
        return "redirect:/auth/word-list/"+token+"/"+userName;

    }
}
