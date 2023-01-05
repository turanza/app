package com.vocabulary.app.controller;

import com.vocabulary.app.dto.AuthenticationRequestDto;
import com.vocabulary.app.model.User;
import com.vocabulary.app.security.jwt.JwtTokenProvider;
import com.vocabulary.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthentificationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthentificationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @GetMapping("/login")
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getCredentials (Model model){
        AuthenticationRequestDto authenticationRequestDto = new AuthenticationRequestDto();
        model.addAttribute("authenticationRequestDto",authenticationRequestDto);
        return "login";
    }

    @PostMapping("/login")
    public String/*ResponseEntity*/ login(/*@RequestBody*/ AuthenticationRequestDto requestDto, Model model) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRolesList(user.getUsersRoles()));

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);
            requestDto.setToken(token);
//делаем модель, кладем токен, вызываем форму со скрытым полем
            model.addAttribute("requestDto",requestDto);
            model.addAttribute("token",token);
//            return ResponseEntity.ok(response);
//            String temp ="redirect:/auth/users/";
            String temp ="redirect:/auth/menu/";
            temp+=token;
            temp+="/";
            temp+=username;
            //System.out.println(temp);
            return temp;
        } catch (AuthenticationException e) {
            System.out.println("Invalid username or password");
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
