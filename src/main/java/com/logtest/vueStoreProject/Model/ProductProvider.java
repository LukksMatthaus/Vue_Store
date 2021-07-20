package com.logtest.vueStoreProject.Model;

public class ProductProvider {
	
	private String fornecedor;
	private String produto;
	private int quantidade;
	
	public ProductProvider() {}
	public ProductProvider(String f, String p, int q) {
		this.fornecedor = f;
		this.produto = p;
		this.quantidade = q;
	}
	public String getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
