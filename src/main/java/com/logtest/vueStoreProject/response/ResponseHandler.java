package com.logtest.vueStoreProject.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
	
	 public static ResponseEntity<Object> generateResponse(String entrada, String vogal, long timestamp, HttpStatus status) {
	        Map<String, Object> map = new HashMap<String, Object>();
	            map.put("string", entrada);
	            map.put("vogal", vogal);
	            map.put("tempoTotal", timestamp+"ms");

	            return new ResponseEntity<Object>(map,status);
	    }

}

