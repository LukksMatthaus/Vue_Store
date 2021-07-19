package com.logtest.vueStoreProject.Controller;

import org.hibernate.Session;
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

import javax.persistence.EntityManager;

import com.logtest.vueStoreProject.Model.Category;
import com.logtest.vueStoreProject.Model.Product;
import com.logtest.vueStoreProject.Model.Provider;
import com.logtest.vueStoreProject.repository.ProductRepository;
import com.logtest.vueStoreProject.response.ResourceNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductRepository pr;
	
	@GetMapping
	public List<Product> getAllProducts(){
		return pr.findAll();
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable(value ="id") Long pID) throws ResourceNotFoundException{
		Product p = pr.findById(pID).orElseThrow(() -> new ResourceNotFoundException("Product not found for this id:  " + pID));
		return ResponseEntity.ok().body(p);
	}
	
	@PostMapping("/novo")
	public Product createProduct(@Validated @RequestBody Product p) {
		Product px = new Product(p.getId(), p.getNome(), p.getValor(),p.getCategoria(),p.getFornecedor() );
		return pr.save(px);
	}
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long pID, @Validated @RequestBody Product pD) throws ResourceNotFoundException{
		Product p = pr.findById(pID).orElseThrow(() -> new ResourceNotFoundException("Product not found for this id:  " + pID));
		p.setNome(pD.getNome());
		p.setValor(pD.getValor());
		p.setCategoria(pD.getCategoria());
		p.setFornecedor(pD.getFornecedor());
		final Product pC = pr.save(p);
		return ResponseEntity.ok().body(pC);
		
	}
	
	@DeleteMapping("/deletar/{id}")
	public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Long pID) throws ResourceNotFoundException{
		Product p = pr.findById(pID).orElseThrow(() -> new ResourceNotFoundException("Product not found for this id:  " + pID));
		pr.delete(p);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return response;
		
		
	}

}
