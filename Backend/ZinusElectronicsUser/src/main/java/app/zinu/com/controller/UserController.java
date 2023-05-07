package app.zinu.com.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.zinu.com.entity.PasswordChangeRequest;
import app.zinu.com.entity.User;
import app.zinu.com.repository.UserRepository;

@RestController
@RequestMapping("/user")
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
	
	@PutMapping("update-password")
	public ResponseEntity<?> updatePassword(@RequestBody PasswordChangeRequest request){
		Optional<User> user = repo.findByUser_id(request.getUser());
		User saved = repo.save(user.get());
		
		if(saved != null) {
			return ResponseEntity.status(HttpStatus.OK).body(saved);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
}
