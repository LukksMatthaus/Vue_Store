package com.logtest.vueStoreProject.Controller;
import java.util.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.logtest.vueStoreProject.Model.Stream;
import com.logtest.vueStoreProject.response.ResponseHandler;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ExampleProperty;

@RestController
public class CharController {
	 private static final String VOGAIS = "aeiou";
	// private static final Logger logger = LoggerFactory.getLogger(CharController.class);

	    public static Optional<Character> encontraPrimeiraVogalNaoRepetida(Stream stream) {

	        if(stream != null) {

	            Set<Character> recorrente = new HashSet<>();
	            List<Character> naoRecorrente = new ArrayList<>();

	            while (stream.hasNext()) {

	                char letra = Character.toLowerCase(stream.getNext());  

	                if (recorrente.contains(letra)) {
	                    continue;
	                }
	                if (naoRecorrente.contains(letra)) {
	                    naoRecorrente.remove((Character) letra);
	                    recorrente.add(letra);
	                } else {
	                    if (isVogal(letra)) {
	                        naoRecorrente.add(letra);
	                    }
	                }
	            }
	            try {
	                return Optional.of(naoRecorrente.get(0));
	            } catch (IndexOutOfBoundsException ex) {
	                System.out.println("Não foi encontrado nenhuma vogal não repetida");
	                return Optional.empty();
	            }
	        }
	        return Optional.empty();
	    }

	    private static boolean isVogal(char letra) {
	        return VOGAIS.indexOf(letra) >= 0;
	    }
	    
	    @ApiOperation(value = "Encontrar  o primeiro caractere Vogal, após uma consoante, onde a mesma é antecessora a uma vogal e que não se repita na string.")
	    @ApiResponses(value = {
	    		@ApiResponse(code = 200, message = "Retorna o primeiro caractere vogal que corresponde ao requerimento da questão"),
	    		@ApiResponse(code = 201, message = "Nenhum caracatere vogal foi encontrado que preencha os requisitos")
	    })
	    @ResponseStatus(value = HttpStatus.OK)
	    @PostMapping(value = "/vowel", consumes = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseBody
	    public ResponseEntity<Object> lastChar(@ApiParam(name = "txt", value = "string para buscar a vogal", type = "String", required = true) @RequestBody Map<String, Object> txt) {
	    	
	    	StopWatch watch = new StopWatch();
	    	watch.start();
	    	Stream s =  new Stream(txt.get("txt").toString());
	    	try {
	    		Optional<Character> chara = encontraPrimeiraVogalNaoRepetida(s);
		    	String tst = chara.get().toString();
		    	watch.stop();
		    	return ResponseHandler.generateResponse(s.getEntrada(), tst, watch.getTotalTimeMillis(), HttpStatus.OK);
	    	} catch (Exception ex) {
	    		watch.stop();
	    		return ResponseHandler.generateResponse(s.getEntrada(), "Vogal não presente", watch.getTotalTimeMillis(), HttpStatus.ACCEPTED);
	    	}
    		} 	
	  

}
