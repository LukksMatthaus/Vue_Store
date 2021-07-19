package com.logtest.vueStoreProject.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "produtos")
public class Product {
	
	private Long id;
	private String nome;
	private float valor;
	private Category categoria;
	private Provider fornecedor;
	
	public Product() {}
	
	public Product (Long i, String n, float v, Category c, Provider p) {
		this.id = i;
		this.nome = n;
		this.valor = v;
		this.categoria = c;
		this.fornecedor = p;
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "nome", nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name = "valor", nullable = false)
	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
	
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	public Category getCategoria() {
		return categoria;
	}

	public void setCategoria(Category categoria) {
		this.categoria = categoria;
	}
	@ManyToOne
	@JoinColumn(name = "fornecedor_id")
	public Provider getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Provider fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	
	
	

}
