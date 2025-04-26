package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public User addUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword())); 
		return userRepository.save(user);
	}
	
	public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setUsername(userDetails.getUsername());
        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }
        user.setRole(userDetails.getRole());

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
	
	
	
	
	
	
	
	

}
