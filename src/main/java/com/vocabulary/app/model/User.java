package com.vocabulary.app.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="users")
public class User extends BaseEntity    {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "first_name")
    private String firstName;

    @Column (name = "second_name")
    private String secondName;

    @Column (name = "third_name")
    private String thirdName;

    @Column (name = "login")
    private String login;

    @Column (name = "password")
    private String password;

    @Column (name = "email")
    private  String email;

    @Column (name = "users_roles")
    private String usersRoles;

    public List<Role> getRolesList (String usersRoles){
        List <Role> roleList = new ArrayList<>();
        Role role = new Role();
        role.setName(usersRoles);
        roleList.add(role);
        return roleList;
    }
}
