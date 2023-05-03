package com.mycompany.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.app.entity.Product;
import com.mycompany.app.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductRepository repo;
	
	
	@GetMapping("/get-all")
	public List<Product> getAllProducts(){
		return repo.findAll();
	}
	
	@PostMapping
	public ResponseEntity<?> saveProduct(@RequestBody Product item){
		Product saved = repo.save(item);
		
		if(item != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(saved);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
}
