package com.logtest.vueStoreProject.Controller;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.logtest.vueStoreProject.Model.Stream;
import com.logtest.vueStoreProject.response.ResponseHandler;

@RestController
public class CharController {
	 private static final String VOGAIS = "aeiou";
	 private static final Logger logger = LoggerFactory.getLogger(CharController.class);

	    /**
	     * Retorna um Optional com o primeiro char vogal não repetido encontrado ou um Optional vazio caso não encontre
	     * @param stream
	     * @return ptional<Character>
	     */
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
	            }
	        }
	        return Optional.empty();
	    }

	    private static boolean isVogal(char letra) {
	        return VOGAIS.indexOf(letra) >= 0;
	    }
	    
	    
	    @ResponseStatus(value = HttpStatus.OK)
	    @PostMapping(value = "/vowel", consumes = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseBody
	    public ResponseEntity<Object> lastChar(@RequestBody Map<String, Object> txt) {
	    	Instant start = Instant.now();
	    	Stream s =  new Stream(txt.get("txt").toString());
	    	Optional<Character> chara = encontraPrimeiraVogalNaoRepetida(s);
	    	String tst = chara.get().toString();
	    	Instant finish = Instant.now();
	    	long time = Duration.between(start, finish).toMillis();
	    	logger.info("this is S:" + chara);
	    	return ResponseHandler.generateResponse(s.getEntrada(), tst, time, HttpStatus.OK);
	    } 	
	    
	   

}
