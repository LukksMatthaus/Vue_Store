package com.logtest.vueStoreProject.Model;

import javax.persistence.Entity;


public class Stream {
	 private String entrada;

	    private int indexAtual = -1;

	    public Stream(String entrada) {
	        if(entrada != null) {
	            this.entrada = entrada;
	        }else{
	            this.entrada = "";
	        }
	    }

	    
	    public char getNext() {
	        indexAtual++;
	        return entrada.charAt(indexAtual);
	    }

	    
	    public boolean hasNext() {
	        try {
	            entrada.charAt(indexAtual +1);
	            return true;
	        }catch (IndexOutOfBoundsException ex){
	            //Não existe mais nenhum caracter na String
	        }
	        return false;
	    }
	    
	    public String getEntrada() {
	    	return entrada;
	    }
	    
	    @Override
	    public String toString() {
	    	return entrada;
	    }
	    
	   
}


