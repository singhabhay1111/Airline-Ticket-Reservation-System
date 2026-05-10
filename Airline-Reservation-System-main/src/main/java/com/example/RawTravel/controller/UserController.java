package com.example.RawTravel.controller;

import com.example.RawTravel.model.User;
import com.example.RawTravel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    // ✅ SIGNUP
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {

        String token = userService.login(user.getEmail(), user.getPassword());

        return Map.of("token", token);
    }
}