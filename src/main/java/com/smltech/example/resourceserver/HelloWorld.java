package com.smltech.example.resourceserver;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

	@GetMapping("/hello")
	private ResponseEntity<?> sayHello() {
		return ResponseEntity.status(200).body("Hello world!");
	}
}
