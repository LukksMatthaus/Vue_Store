package com.logtest.vueStoreProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.logtest.vueStoreProject.Model.Category;
import com.logtest.vueStoreProject.repository.CategoryRepository;
import com.logtest.vueStoreProject.response.ResourceNotFoundException;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryRepository cr;
	
	@GetMapping
	public List<Category> getAllCategories(){
		return cr.findAll();
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable(value ="id") Long cID) throws ResourceNotFoundException{
		Category c = cr.findById(cID).orElseThrow(() -> new ResourceNotFoundException("Category not found for this id:  " + cID));
		return ResponseEntity.ok().body(c);
	}
	
	@PostMapping("/novo")
	public Category createCategory(@Validated @RequestBody Category c) {
		return cr.save(c);
	}
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable(value = "id") Long cID, @Validated @RequestBody Category cD) throws ResourceNotFoundException{
		Category c = cr.findById(cID).orElseThrow(() -> new ResourceNotFoundException("Category not found for this id:  " + cID));
		c.setDescricao(cD.getDescricao());
		final Category uC = cr.save(c);
		return ResponseEntity.ok().body(uC);
		
	}
	
	@DeleteMapping("/deletar/{id}")
	public Map<String, Boolean> deleteCategory(@PathVariable(value = "id") Long cID) throws ResourceNotFoundException{
		Category c = cr.findById(cID).orElseThrow(() -> new ResourceNotFoundException("Category not found for this id:  " + cID));
		cr.delete(c);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return response;
		
		
	}
}
