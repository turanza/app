package com.vocabulary.app.repository;

import com.vocabulary.app.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word,Long> {
}
