package com.iqs.iq_project.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.iqs.iq_project.controllers.SearchRequest;
import com.iqs.iq_project.exceptions.ResourceNotFoundException;
import com.iqs.iq_project.models.StudentDto;
import com.iqs.iq_project.models.mongodb.Student;
import com.iqs.iq_project.models.mysql.User;
import com.iqs.iq_project.repositories.mongodb.StudentRepository;
import com.iqs.iq_project.repositories.mysql.UserRepository;
import com.iqs.iq_project.services.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
    private final MongoTemplate tmpl;
    private final StudentRepository stRepo;
    private final UserRepository repo;
    public static final String ST = "Student";
    public static final String USER = "User";
    public StudentServiceImpl(StudentRepository sTrepo, MongoTemplate template, UserRepository repo){
        super();
        this.stRepo = sTrepo;
        this.tmpl = template;
        this.repo = repo;
    }

    @Override
    public List<Student> getAllStudents() {
        return stRepo.findAll();
    }

    @Override
    public Student getStudentById(String id) {
        return stRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException(ST, "id", id));
    }

    @Override
    public Student saveStudentByUserId(StudentDto dto, Long userId) {
        Optional<User> userOptional = repo.findById(userId);
		if (userOptional.isPresent()) {
	        User user = userOptional.get();
	        Optional<Student> exists = stRepo.findByEmail(dto.email());
        if (exists.isPresent()) {
            throw new ResourceNotFoundException(ST, dto.email());
        } else {
            Student st = new Student(dto.name(), dto.age(), dto.email(), user.getId());
            st = stRepo.save(st);
            return st;
            
        }
	    } else {
	        throw new ResourceNotFoundException(USER, "id", userId);
	    }
        
    }

    @Override
    public void deleteStudentById(String id) {
        Student st = stRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException(ST, "id", id));
        stRepo.delete(st);
        
    }

    @Override
    public Student editStudent(StudentDto dto, String id) {
        Student exists = stRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException(ST, "id", id));
        Optional<Student> emailExists = stRepo.findByEmail(dto.email());
        if(emailExists.isPresent() && !emailExists.get().getId().equals(id)){
           throw new ResourceNotFoundException(ST, dto.email());
        }else{
            exists.setName(dto.name());
            exists.setAge(dto.age());
            exists.setEmail(dto.email());
            return stRepo.save(exists);
        }  
    }

    @Override
    public List<Student> advancedSearchStudent(String name, Integer age, String email) {
        if (name == null && age == null && email == null) {
            throw new IllegalArgumentException("At least one search parameter is required.");
        }
        Query query = new Query();
        if (name != null && !name.isEmpty()) {
            query.addCriteria(Criteria.where("name").regex(name, "i"));
        }
        if (age != null) {
            query.addCriteria(Criteria.where("age").is(age));
        }
        if (email != null && !email.isEmpty()) {
            query.addCriteria(Criteria.where("email").regex(email, "i"));
        }
        return tmpl.find(query, Student.class);
    }

    @Override
	public List<Student> getStudentsByUserId(long userId) {
	    return stRepo.findByUserId(userId);
	}
   
    @Override
	public List<Student> searchByUserId(SearchRequest searchRequest) {
		return stRepo.findByUserIdAndSearchTerm(
                searchRequest.getUserId(), searchRequest.getSearchTerm());
	}
}
