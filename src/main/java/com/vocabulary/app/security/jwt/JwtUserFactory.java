package com.vocabulary.app.security.jwt;

import com.vocabulary.app.model.Status;
import com.vocabulary.app.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public final class JwtUserFactory {
    public JwtUserFactory(){}

    public static JwtUser create (User user){
        return new JwtUser(user.getId(),
                user.getLogin(),
                user.getFirstName(),
                user.getSecondName(),
                user.getThirdName(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getUsersRoles()),
                user.getStatus().equals(Status.ACTIVE),
                user.getUpdated()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(String userRoles) {
//        return userRoles.stream()
//                .map(role ->
//                        new SimpleGrantedAuthority(role.getName())
//                ).collect(Collectors.toList());
        List <GrantedAuthority>  list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(userRoles));
        return list;
    }
}
