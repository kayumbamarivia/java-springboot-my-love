package com.iqs.iq_project.models;
import com.iqs.iq_project.models.mysql.Role;

import jakarta.validation.constraints.NotBlank;

public record RegisterDto(
    @NotBlank(message = "Name cannot be null") String name,
    @NotBlank(message = "Username cannot be null") String username,
    @NotBlank(message = "Password cannot be null") String password,String avatar, Role role){}
