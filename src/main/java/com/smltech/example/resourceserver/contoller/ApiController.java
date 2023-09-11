package com.smltech.example.resourceserver.contoller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

	@GetMapping("/user")
	public Map<String, Object> userEndpoint(Authentication authentication) {
		Map<String, Object> response = new HashMap<>();

		// This endpoint is accessible to users with the "user" role
		response.put("message", "Hello, User!");
		response.put("code", 200);
		response.put("status", "Success");

		return response;
	}

	@GetMapping("/admin")
	public Map<String, Object> adminEndpoint(Authentication authentication) {
		Map<String, Object> response = new HashMap<>();

		// This endpoint is accessible to users with the "admin" role
		response.put("message", "Hello, Admin!");
		response.put("code", 200);
		response.put("status", "Success");

		return response;
	}

}
