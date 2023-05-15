package com.mycompany.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.app.entity.UserAddress;
import com.mycompany.app.repository.UserAddressRepository;

@RestController
@RequestMapping("/user-address")
public class UserAddressController {

	@Autowired
	private UserAddressRepository repo;

	@GetMapping("/get-all")
	public List<UserAddress> getAllAddress() {
		return repo.findAll();
	}

	@PostMapping("/create")
	public ResponseEntity<?> createUserAddress(@RequestBody UserAddress req) {

		UserAddress created = repo.save(req);

		if (created != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(created);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateUserAddress(@RequestBody UserAddress req) {

		UserAddress found = repo.findByUser_Id(req.getUser_Id());

		if (found != null) {

			UserAddress updated = repo.save(found);

			if (updated != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(updated);
			}
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

}
