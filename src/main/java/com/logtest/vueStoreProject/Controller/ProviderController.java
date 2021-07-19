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
import com.logtest.vueStoreProject.Model.Provider;
import com.logtest.vueStoreProject.repository.ProviderRepository;
import com.logtest.vueStoreProject.response.ResourceNotFoundException;


@RestController
@RequestMapping("/provider")
public class ProviderController {

	@Autowired
	private ProviderRepository pr;
	
	@GetMapping
	public List<Provider> getAllProviders(){
		return pr.findAll();
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Provider> getProviderById(@PathVariable(value ="id") Long pID) throws ResourceNotFoundException{
		Provider p = pr.findById(pID).orElseThrow(() -> new ResourceNotFoundException("Provider not found for this id:  " + pID));
		return ResponseEntity.ok().body(p);
	}
	
	@PostMapping("/novo")
	public Provider createProvider(@Validated @RequestBody Provider p) {
		return pr.save(p);
	}
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<Provider> updateProvider(@PathVariable(value = "id") Long pID, @Validated @RequestBody Provider pD) throws ResourceNotFoundException{
		Provider p = pr.findById(pID).orElseThrow(() -> new ResourceNotFoundException("Provider not found for this id:  " + pID));
		p.setDescricao(pD.getDescricao());
		final Provider pC = pr.save(p);
		return ResponseEntity.ok().body(pC);
		
	}
	
	@DeleteMapping("/deletar/{id}")
	public Map<String, Boolean> deleteProvider(@PathVariable(value = "id") Long pID) throws ResourceNotFoundException{
		Provider p = pr.findById(pID).orElseThrow(() -> new ResourceNotFoundException("Provider not found for this id:  " + pID));
		pr.delete(p);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return response;
		
		
	}
}
