package com.iqs.iq_project.services.impl;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.iqs.iq_project.exceptions.ResourceNotFoundException;
import com.iqs.iq_project.models.LoginDto;
import com.iqs.iq_project.models.RegisterDto;
import com.iqs.iq_project.models.mysql.User;
import com.iqs.iq_project.repositories.mysql.UserRepository;
import com.iqs.iq_project.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository repo;
    private static final String USER = "USER";
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;


	public UserServiceImpl(UserRepository repo, PasswordEncoder passwordEncoder,
			AuthenticationManager authenticationManager) {
		super();
		this.repo = repo;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
	}

	@Override
	public  User login(LoginDto request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.username(), request.password()));
		return repo.findByUsername(request.username()).orElseThrow(()-> new ResourceNotFoundException(USER, "Username", request.username()));
	}

	@Override
	public User register(RegisterDto request) {
		User user = new User();
		user.setName(request.name());
		user.setUsername(request.username());
		user.setPassword(passwordEncoder.encode(request.password()));
		String avatar = request.avatar();
		if (avatar == null || avatar.isEmpty()) {
			user.setAvatar("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png");
		} else {
			user.setAvatar(avatar);
		}
		user.setRole(request.role());
		user = repo.save(user);
		return user;
	}


	@Override
	public User getUserById(long id) {
		return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException(USER, "id", id));
	}
	@Override
	public List<User> getAllUsers() {
		return repo.findAll();
	}

	@Override
	public void deleteUserById(long id) {
		User user = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException(USER, "id", id));
		repo.delete(user);
	}

	@Override
	public User updateUserById(User u, long id) {
	    User existingOne = repo.findById(id)
	                           .orElseThrow(() -> new ResourceNotFoundException(USER, "id", id));

	    String name = u.getName();
	    if (name != null && !name.isEmpty()) {
	        existingOne.setName(name);
	    }

	    String username = u.getUsername();
	    if (username != null && !username.isEmpty()) {
	        existingOne.setUsername(username);
	    }

	    String pass = u.getPassword();
	    if (pass != null && !pass.isEmpty()) {
	        existingOne.setPassword(passwordEncoder.encode(pass));
	    }

	    String avatar = u.getAvatar();
	    if (avatar != null) {
	        existingOne.setAvatar(avatar);
	    }
	    
	    return repo.save(existingOne);
	}
}
