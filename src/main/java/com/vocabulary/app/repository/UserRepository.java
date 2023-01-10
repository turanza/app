package com.vocabulary.app.repository;

import com.vocabulary.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {

    @Modifying
    @Query(value = """
    UPDATE users SET first_name =:firstName, second_name=:secondName, third_name=:thirdName, login=:login,
     password=:password,email=:email,users_roles=:usersRoles WHERE id=:id
""",nativeQuery = true)
    void updateUser(Long id, String firstName, String secondName, String thirdName, String email,
                    String login, String password, String usersRoles);

    User findByLogin(String login);

    @Modifying
    @Query(value = """
    UPDATE users SET first_name =:firstName, second_name=:secondName, third_name=:thirdName, login=:login,
     email=:email,users_roles=:usersRoles WHERE id=:id
""",nativeQuery = true)
    void updateUserWithOutPass(Long id, String firstName, String secondName, String thirdName, String email, String login, String usersRoles);

    @Query(value = """
    SELECT id FROM users WHERE login=:userName
""",nativeQuery = true)
    Long getByUserName(String userName);
}
