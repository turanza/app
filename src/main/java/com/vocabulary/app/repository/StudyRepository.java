package com.vocabulary.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudyRepository extends UserRepository{


@Query(value = """
    SELECT word_id, progress FROM progress WHERE user_id=:usersId 
""",nativeQuery = true)
    List<String> getWordsIds(Long usersId);

@Modifying
@Query(value = """
    INSERT INTO progress VALUES (user_id=:userId, word_id=:id, 0,"")
""",nativeQuery = true)
    void addWordsToProgress(Long userId, Long id);
}
