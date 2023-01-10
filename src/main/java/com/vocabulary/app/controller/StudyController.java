package com.vocabulary.app.controller;

import com.vocabulary.app.model.Word;
import com.vocabulary.app.service.StudyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class StudyController {

    private final StudyService studyService;

    public StudyController(StudyService studyService) {
        this.studyService = studyService;
    }

    @GetMapping("/study/process/{token}/{userName}")
    public String studyProcess(Model model, @PathVariable("token")String token,
                               @PathVariable("userName")String userName){
        List<Word> words = studyService.getWordList(userName);
        return "auth/study";
    }
}
