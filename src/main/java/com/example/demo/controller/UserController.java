package com.example.demo.controller;

import java.net.http.HttpClient;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.User;
import com.example.demo.security.SecurityConfig;
import com.example.demo.service.BookService;
import com.example.demo.service.UserService;

@RestController
public class UserController {

    private final SecurityConfig securityConfig;
	
	@Autowired
	private UserService userService;


    UserController(SecurityConfig securityConfig) {
        this.securityConfig = securityConfig;
    }
	
	
	@GetMapping("/users/view")
	public List<User> getAllUsers(){
		return userService.getAllUsers();	
	}
	
	@PostMapping("/users/add")
	public ResponseEntity<User> addUser(@RequestBody User user){
		User addedUser=userService.addUser(user);
		return new ResponseEntity<>(addedUser,HttpStatus.CREATED);
		
	}
	
   @PutMapping("/users/update/{id}")
   public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }


	

}
