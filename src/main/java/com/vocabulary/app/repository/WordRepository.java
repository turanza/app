package com.vocabulary.app.repository;

import com.vocabulary.app.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface WordRepository extends JpaRepository<Word,Long> {
    @Modifying
    @Query(value = """
    INSERT INTO words VALUES (:wordF,:wordR,:exampleF,:exampleR,
    null,:active,:language)
""" ,nativeQuery = true)
    void saveWord(String wordF, String wordR, String exampleF, String exampleR, Integer active, Integer language);
}
