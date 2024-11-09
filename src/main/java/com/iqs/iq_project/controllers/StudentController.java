package com.iqs.iq_project.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.iqs.iq_project.models.StudentDto;
import com.iqs.iq_project.models.mongodb.Student;
import com.iqs.iq_project.services.impl.StudentServiceImpl;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {
    private final StudentServiceImpl stService;
    
    public StudentController(StudentServiceImpl serve){
        this.stService = serve;
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(stService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/{userId}/students")
	public List<Student> getAllStudentsByUserId(@PathVariable("userId") long userId){
		return stService.getStudentsByUserId(userId);
	}

    @GetMapping("/student/{id}/get")
    public ResponseEntity<Student> getStudentById(@PathVariable String id) {
        Student student = stService.getStudentById(id);
       return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("/student/{userId}/add")
    public ResponseEntity<Student> createStudent(@Valid @RequestBody StudentDto student, @PathVariable("userId") long userId) {
        return new ResponseEntity<>(stService.saveStudentByUserId(student, userId), HttpStatus.CREATED);
    }


    @PutMapping("/student/{id}/edit")
    public ResponseEntity<Student> editStudent(@Valid @RequestBody StudentDto student, @PathVariable("id") String id) {
        return new ResponseEntity<>(stService.editStudent(student, id), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/student/{id}/delete")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") String id) {
        stService.deleteStudentById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/students/advanced-search")
public ResponseEntity<List<Student>> advancedSearch(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) Integer age,
        @RequestParam(required = false) String email) {
    List<Student> results = stService.advancedSearchStudent(name, age, email);
    return ResponseEntity.ok(results);
}

@PostMapping("/student/search")
	public List<Student> searchByUserId(@RequestBody SearchRequest searchRequest){
		return stService.searchByUserId(searchRequest);
	}

}
