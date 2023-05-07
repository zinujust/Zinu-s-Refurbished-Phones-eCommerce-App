package com.mycompany.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	public List<Product> getProductByName(){
		return null;
	}
	
	public List<Product> getProductByModel(){
		return null;
	}
	
	public List<Product> getProductByBrand(){
		return null;
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> saveProduct(@RequestBody Product item){
		Product saved = repo.save(item);
		
		if(item != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(saved);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@DeleteMapping("/remove-item/{id}")
	public void removeProduct(@PathVariable Integer id){
		repo.deleteById(id);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateProduct(@RequestBody Product product){
		Optional<Product> found = repo.findById(product.getId());
		
		if(found.isPresent()) {
			Product saved = repo.save(product);
			
			if(saved != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(saved);
			}
			else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
}
