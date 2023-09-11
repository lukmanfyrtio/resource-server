package com.smltech.example.resourceserver.security;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private final ObjectMapper objectMapper;
	
    public CustomAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
    	  response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

          // Create a custom error response
          Map<String, Object> errorResponse = new HashMap<>();
          errorResponse.put("timestamp", LocalDateTime.now());
          errorResponse.put("status", HttpStatus.UNAUTHORIZED.value());
          errorResponse.put("error", "Unauthorized");
          errorResponse.put("message", "Anda belum terautentikasi untuk mengakses sumber daya ini");
          errorResponse.put("path", request.getRequestURI());

          // Serialize the error response as JSON and write it to the response
          response.setContentType("application/json");
          response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
      }
}
