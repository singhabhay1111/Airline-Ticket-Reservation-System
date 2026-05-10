package com.example.RawTravel.service;

import com.example.RawTravel.JwtUtil;
import com.example.RawTravel.model.User;
import com.example.RawTravel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // ✅ SIGNUP
    public User register(User user) {

        user.setPassword(encoder.encode(user.getPassword()));

        if (user.getRole() == null) {
            user.setRole("USER");
        }

        return userRepository.save(user);
    }

    // ✅ LOGIN
    public String login(String email, String password) {

        System.out.println("EMAIL: " + email);
        System.out.println("PASSWORD: " + password);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        System.out.println("DB PASSWORD: " + user.getPassword());

        // ✅ FIXED (IMPORTANT)
        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // ✅ FIXED TOKEN
        return JwtUtil.generateToken(user.getId(), user.getRole());
    }
}