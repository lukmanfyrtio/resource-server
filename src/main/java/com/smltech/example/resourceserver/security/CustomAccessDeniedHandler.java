package com.smltech.example.resourceserver.security;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	private final ObjectMapper objectMapper;
	
    public CustomAccessDeniedHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		 response.setStatus(HttpServletResponse.SC_FORBIDDEN);

	        // Create a custom error response
	        Map<String, Object> errorResponse = new HashMap<>();
	        errorResponse.put("timestamp", LocalDateTime.now());
	        errorResponse.put("status", HttpServletResponse.SC_FORBIDDEN);
	        errorResponse.put("error", "Forbidden");
	        errorResponse.put("message", "Anda tidak memiliki izin untuk mengakses sumber daya ini");
	        errorResponse.put("path", request.getRequestURI());

	        // Serialize the error response as JSON and write it to the response
	        response.setContentType("application/json");
	        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
		
	}
}
