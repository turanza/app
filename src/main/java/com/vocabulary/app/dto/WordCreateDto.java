package com.vocabulary.app.dto;

import com.vocabulary.app.model.Word;
import lombok.Data;

@Data
public class WordCreateDto {

    private String wordF;

    private String wordR;

    private  String exampleF;

    private String exampleR;

//    @Column(name = "picture")
//    private String picture;

    private Integer active;

    private Integer language;

    public Word toWord(){
        Word word = new Word();
        word.setWordF(this.wordF);
        word.setWordR(this.wordR);
        word.setExampleF(this.exampleF);
        word.setExampleR(this.exampleR);
        word.setActive(this.active);
        word.setLanguage(this.language);

        return word;
    }
}
