package com.iqs.iq_project.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record  StudentDto(
    @NotBlank(message = "Name cannot be null") String name,
    @Min(value = 3, message = "Age must be at least 3") Integer age,
    @Email(message = "Email should be valid") String email, Long userId){}

