package app.zinu.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.zinu.com.entity.User;
import app.zinu.com.repository.UserRepository;

@RestController
@RequestMapping("/api/v1/demo-controller")
public class UserController {
	
	@Autowired
	private UserRepository repo;
	

	@GetMapping
	public ResponseEntity<String> sayHello(){
		return ResponseEntity.ok("Hello from secured endpoint");
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createUser(@RequestBody User user){
		User savedUser = repo.save(user);

	    if (savedUser != null) {
	        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	    } else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	    }
		
	}
	
}
