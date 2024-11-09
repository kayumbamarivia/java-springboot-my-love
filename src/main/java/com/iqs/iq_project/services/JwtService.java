package com.iqs.iq_project.services;

import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import com.iqs.iq_project.models.mysql.User;

import io.jsonwebtoken.Claims;

public interface JwtService {
    boolean isValid(String token, UserDetails user);
    String extractUsername(String token);
    <T> T extractClaim(String token, Function<Claims, T> resolver);
    String generateToken(User user);
}