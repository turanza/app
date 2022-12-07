package com.vocabulary.app.service;

import com.vocabulary.app.model.User;
import com.vocabulary.app.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById (Long id){return userRepository.getById(id);}

    public List <User> findAll(){return  userRepository.findAll();}

    public User saveUser(User user){return userRepository.save(user);}

    /*
    need to check:
    1. such login exist
    2. who can change users roles
     */
    @Transactional
    public void updateUser(User user){
        userRepository.updateUser(user.getId(),user.getFirstName(),
            user.getSecondName(),user.getThirdName(),user.getEmail(),user.getLogin(),user.getPassword(),
            user.getUsersRoles());
    }

    @Transactional
    public User findByUsername (String userName){
        User user = userRepository.findByLogin(userName);
        if (user!=null)return user; else return null;
    }

    @Transactional
    public void updateUserWithOutPass(User user) {
        userRepository.updateUserWithOutPass(user.getId(),user.getFirstName(),
                user.getSecondName(),user.getThirdName(),user.getEmail(),user.getLogin(),
                user.getUsersRoles());
    }
}
