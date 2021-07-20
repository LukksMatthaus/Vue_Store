package com.logtest.vueStoreProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.logtest.vueStoreProject.Model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>  {
	@Query("select p from Product p where p.quantidade < 10")
	public List<Product> findByEstoque();

}
