package com.iqs.iq_project.services;

import java.util.List;

import com.iqs.iq_project.models.LoginDto;
import com.iqs.iq_project.models.RegisterDto;
import com.iqs.iq_project.models.mysql.User;

public interface UserService {
    User login(LoginDto loginDto);
	User register(RegisterDto registerDto);
	List<User> getAllUsers();
	void deleteUserById(long id);
	User getUserById(long id);
	User updateUserById(User u, long id);
}
