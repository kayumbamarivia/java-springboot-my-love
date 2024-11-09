package com.iqs.iq_project.services;

import java.util.List;

import com.iqs.iq_project.controllers.SearchRequest;
import com.iqs.iq_project.models.StudentDto;
import com.iqs.iq_project.models.mongodb.Student;

public interface StudentService {

    List<Student> getAllStudents();

    List<Student> getStudentsByUserId(long id);

    Student getStudentById(String id) ;

    Student saveStudentByUserId(StudentDto dto, Long userId);

    Student editStudent(StudentDto student, String id);

    List<Student> advancedSearchStudent(String name, Integer age, String email);

    List<Student> searchByUserId(SearchRequest searchRequest);

    void deleteStudentById(String id);
}

