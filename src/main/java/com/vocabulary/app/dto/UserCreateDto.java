package com.vocabulary.app.dto;

import com.vocabulary.app.model.User;
import lombok.Data;

@Data
public class UserCreateDto {

    private String firstName;

    private String secondName;

    private String thirdName;

    private String login;

    private String password;

    private  String email;

    private String usersRoles;

    public UserCreateDto(String firstName, String secondName, String thirdName, String login, String password, String email, String usersRoles) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.thirdName = thirdName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.usersRoles = usersRoles;
    }

    public User toUser (){
        User user = new User();
        user.setId(null);
        user.setFirstName(this.firstName);
        user.setSecondName(this.secondName);
        user.setThirdName(this.thirdName);
        user.setLogin(this.login);
        user.setPassword(this.password);
        user.setEmail(this.email);
        user.setUsersRoles(this.usersRoles);
        return user;
    }
}
