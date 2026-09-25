package com.e3s.mercedes.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:5500", "http://127.0.0.1:5500"})
public class AuthController {
    @GetMapping("/me")
    public Map<String, Object> me(Authentication authentication) {
        return Map.of("authenticated", true, "email", authentication.getName(), "authorities", authentication.getAuthorities());
    }
}
