package com.vocabulary.app.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "words")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "word_in_foreign")
    private String wordF;

    @Column(name = "word_in_rus")
    private String wordR;

    @Column(name = "example_f")
    private  String exampleF;

    @Column(name = "example_t")
    private String exampleR;

//    @Column(name = "picture")
//    private String picture;

    @Column(name="active")
    private Integer active;

    @Column(name = "language")
    private Integer language;
}
