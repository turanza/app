package com.vocabulary.app.service;

import com.vocabulary.app.model.Word;
import com.vocabulary.app.repository.StudyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class StudyService {

    private final StudyRepository studyRepository;
    private final UserService userService;

    private final WordService wordService;

    public StudyService(StudyRepository studyRepository, UserService userService, WordService wordService) {
        this.studyRepository = studyRepository;
        this.userService = userService;
        this.wordService = wordService;
    }


    @Transactional
    public List<Word> getWordList(String userName) {
        List <Long> studiedWords = new ArrayList<>();
        List <String> progress = new ArrayList<>();
        Long userId = userService.getUsersId(userName);
        progress = studyRepository.getWordsIds(userId);
        Map<Long, Long> studyResults = new HashMap<>();
        Long word = null;
        for (String p: progress 
        ) {
            String [] pr =p.split(",");
            int count = 0;
            for (String temp: pr
                 ) {
                if(count==0){
                    count++;
                    word=Long.getLong(temp);
                } else if (count==1) {
                    if (Long.getLong(temp)<10) {
                        studyResults.put(word, Long.getLong(temp));
                    }
                    else
                        studiedWords.add(word);
                }
            }
        }

        if (studyResults.size()<10) {
            List<Word> allWords = wordService.getWords();
            List<Word> unLearnedWords = new ArrayList<>();
            unLearnedWords = allWords.stream().filter(t->studiedWords.contains(t.getId())).collect(Collectors.toList());
            int count = 10 - studiedWords.size();
            if (unLearnedWords.size() < count)
                count = unLearnedWords.size();
            for (Word w: unLearnedWords
                 ) {
                if (count!=0){
                    studyRepository.addWordsToProgress(userId, w.getId());
                    count--;
                    studyResults.put(w.getId(),0L);
                }else {
                    continue;
                }
            }

        }
        System.out.println(progress);

        return null;
    }
}
