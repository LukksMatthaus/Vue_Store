package com.logtest.vueStoreProject.Model;

public class ProductCategory {
	
	private String categoria;
	private int quantidadeTotal;
	
	public ProductCategory() {}
	public ProductCategory(String c, int qt) {
		this.categoria = c;
		this.quantidadeTotal = qt;
	}
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public int getQuantidadeTotal() {
		return quantidadeTotal;
	}
	public void setQuantidadeTotal(int quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}
	

}
