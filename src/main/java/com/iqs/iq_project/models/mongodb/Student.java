package com.iqs.iq_project.models.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.*;

@Document(collection = "students")
public class Student {
    
    @Id
    private String id;
    @NotBlank(message = "Name is required")
    private String name;
    @Min(value = 3, message = "Age should be atleast 3 years")
    private Integer age;
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    private Long userId;

    public Student() {
    }

    

    public Student(String id, @NotBlank(message = "Name is required") String name,
            @Min(value = 3, message = "Age should be atleast 3 years") Integer age,
            @NotBlank(message = "Email is required") @Email(message = "Email should be valid") String email, Long userId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.userId = userId;
    }

    public Student(@NotBlank(message = "Name is required") String name,
            @Min(value = 3, message = "Age should be atleast 3 years") Integer age,
            @NotBlank(message = "Email is required") @Email(message = "Email should be valid") String email, Long userId) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.userId = userId;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }



    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", age=" + age + ", email=" + email + ", userId=" + userId
                + "]";
    }
 
}
