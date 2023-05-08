package com.mycompany.app.controller;

import java.util.List;
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

import com.mycompany.app.entity.Inventory;
import com.mycompany.app.repository.InventoryRepository;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

	@Autowired
	private InventoryRepository repo;
	
	@GetMapping("/get-all-inventory")
	public List<Inventory> getTotalInventory(){
		return repo.findAll();
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createInventoryItem(@RequestBody Inventory request){
		Inventory saved = repo.save(request);
		
		if(saved != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(saved);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/subtract")
	public ResponseEntity<?> subtractQty(@RequestBody Inventory reqest){
		Optional<Inventory> found = repo.findById(reqest.getId());
		int qty = found.get().getQty();
		found.get().setQty(qty - reqest.getQty());
		
		Inventory saved = repo.save(found.get());
		
		if(saved != null) {
			return ResponseEntity.status(HttpStatus.OK).body(saved);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/add")
	public ResponseEntity<?> addQty(@RequestBody Inventory reqest){
		Optional<Inventory> found = repo.findById(reqest.getId());
		int qty = found.get().getQty();
		found.get().setQty(qty + reqest.getQty());
		
		Inventory saved = repo.save(found.get());
		
		if(saved != null) {
			return ResponseEntity.status(HttpStatus.OK).body(saved);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
