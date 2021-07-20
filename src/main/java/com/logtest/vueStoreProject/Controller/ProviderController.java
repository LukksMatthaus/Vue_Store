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


import com.logtest.vueStoreProject.Model.Provider;
import com.logtest.vueStoreProject.repository.ProviderRepository;
import com.logtest.vueStoreProject.response.ResourceNotFoundException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/provider")
public class ProviderController {

	@Autowired
	private ProviderRepository pr;
	
	
	@ApiOperation(value = "Listagem de todos os fornecedores")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Listagem retornada com sucesso")
    })
	@GetMapping
	public List<Provider> getAllProviders(){
		return pr.findAll();
		
	}
	
	
	@ApiOperation(value = "Listar fornecedor pelo ID")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Fornecedor retornado com sucesso")
    })
	@GetMapping("/{id}")
	public ResponseEntity<Provider> getProviderById(@PathVariable(value ="id") Long pID) throws ResourceNotFoundException{
		Provider p = pr.findById(pID).orElseThrow(() -> new ResourceNotFoundException("Provider not found for this id:  " + pID));
		return ResponseEntity.ok().body(p);
	}
	
	
	@ApiOperation(value = "Inserir novo fornecedor")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Fornecedor inserido com sucesso")
    })
	@PostMapping("/novo")
	public Provider createProvider(@Validated @RequestBody Provider p) {
		return pr.save(p);
	}
	
	
	@ApiOperation(value = "Atualziar fornecedor pelo ID")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Fornecedor atualziado com sucesso")
    })
	@PutMapping("/editar/{id}")
	public ResponseEntity<Provider> updateProvider(@PathVariable(value = "id") Long pID, @Validated @RequestBody Provider pD) throws ResourceNotFoundException{
		Provider p = pr.findById(pID).orElseThrow(() -> new ResourceNotFoundException("Provider not found for this id:  " + pID));
		p.setDescricao(pD.getDescricao());
		final Provider pC = pr.save(p);
		return ResponseEntity.ok().body(pC);
		
	}
	
	
	
	@ApiOperation(value = "Deletar fornecedor pelo ID")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Fornecedor deletado com sucesso")
    })
	@DeleteMapping("/deletar/{id}")
	public Map<String, Boolean> deleteProvider(@PathVariable(value = "id") Long pID) throws ResourceNotFoundException{
		Provider p = pr.findById(pID).orElseThrow(() -> new ResourceNotFoundException("Provider not found for this id:  " + pID));
		pr.delete(p);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return response;
		
		
	}
}
