package com.vocabulary.app.service;

import com.vocabulary.app.model.Word;
import com.vocabulary.app.repository.WordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WordService {
    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public List<Word> getWords() {

        return wordRepository.findAll();
    }

    public Word getById(Long id) {
        return wordRepository.getById(id);
    }

    public void updateWord(Word word) {
        wordRepository.save(word);
    }

    @Transactional
    public void saveWord(Word word) {
        wordRepository.saveWord(word.getWordF(),word.getWordR(),
                word.getExampleF(),word.getExampleR(),
                word.getActive(),word.getLanguage());
//        wordRepository.save(word);
    }
}
