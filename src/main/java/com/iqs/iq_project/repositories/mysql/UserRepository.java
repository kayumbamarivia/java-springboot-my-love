package com.iqs.iq_project.repositories.mysql;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.iqs.iq_project.models.mysql.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
}
