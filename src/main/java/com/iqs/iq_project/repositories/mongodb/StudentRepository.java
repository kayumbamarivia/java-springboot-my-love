package com.iqs.iq_project.repositories.mongodb;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.iqs.iq_project.models.mongodb.Student;

public interface StudentRepository extends MongoRepository<Student, String> {

    Optional<Student> findByEmail(String email);

    List<Student> findByUserId(long userId);

    @Query("{ 'userId': ?0, '$or': [ { 'name': { $regex: ?1, $options: 'i' } }, { 'age': { $regex: ?1, $options: 'i' } }, { 'email': { $regex: ?1, $options: 'i' } } ] }")
List<Student> findByUserIdAndSearchTerm(Long userId, String searchTerm);

}
