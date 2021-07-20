package com.logtest.vueStoreProject.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import com.logtest.vueStoreProject.Model.Product;
import com.logtest.vueStoreProject.Model.ProductCategory;
import com.logtest.vueStoreProject.Model.ProductProvider;
import com.logtest.vueStoreProject.repository.ProductRepository;
import com.logtest.vueStoreProject.response.ResourceNotFoundException;
import com.logtest.vueStoreProject.response.ResponseHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/products")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	private ProductRepository pr;
	
	 @Autowired
	 @PersistenceContext
	 private EntityManager em;
	
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
		Product px = new Product(p.getId(), p.getNome(), p.getValor(),p.getCategoria(),p.getFornecedor(), p.getQuantidade());
		return pr.save(px);
	}
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long pID, @Validated @RequestBody Product pD) throws ResourceNotFoundException{
		Product p = pr.findById(pID).orElseThrow(() -> new ResourceNotFoundException("Product not found for this id:  " + pID));
		p.setNome(pD.getNome());
		p.setValor(pD.getValor());
		p.setCategoria(pD.getCategoria());
		p.setFornecedor(pD.getFornecedor());
		p.setQuantidade(pD.getQuantidade());
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
	
	@GetMapping("/listarporCategoria")
	public List<ProductCategory> listByCategory() {
		StoredProcedureQuery  q = em.createStoredProcedureQuery("public.listbycategory");
		q.execute();
		List<Object[]> dataList= q.getResultList();
		List<ProductCategory> cc =  new ArrayList<ProductCategory>();
		for(int i = 0; i < dataList.size(); i++ ) {
			ProductCategory c = new ProductCategory(dataList.get(i)[0].toString(), (Integer) dataList.get(i)[1]);
			cc.add(c);
		}
     	
        
		return cc;
		
		
	}
	
	@GetMapping("/listarporEstoque")
	public List<Product> listEsotque() {
		
		return pr.findByEstoque();
		
		
		
	}
	
	@GetMapping("/listarporFornecedor")
	public List<ProductProvider> listByFornecedor() {
		StoredProcedureQuery  q = em.createStoredProcedureQuery("public.listbyprovider");
		q.execute();
		List<Object[]> dataList= q.getResultList();
		List<ProductProvider> pp =  new ArrayList<ProductProvider>();
		for(int i = 0; i < dataList.size(); i++ ) {
			ProductProvider p = new ProductProvider(dataList.get(i)[0].toString(),dataList.get(i)[1].toString(), (Integer) dataList.get(i)[2]);
			pp.add(p);
		}
     	
        
		return pp;
		
		
	}
	
	

}
