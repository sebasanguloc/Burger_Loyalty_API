package org.api.burger_loyalty_api.exceptionHandling;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        LocalDateTime localDateTime = LocalDateTime.now();
        String message = (authException != null && authException.getMessage() != null) ? authException.getMessage() : "Unauthorized";
        String path = request.getRequestURI();

        Map<String,Object> jsonMap = new HashMap<>();

        jsonMap.put("timestamp", localDateTime.toString());
        jsonMap.put("status", HttpStatus.UNAUTHORIZED.value());
        jsonMap.put("error", HttpStatus.UNAUTHORIZED.getReasonPhrase());
        jsonMap.put("message", message);
        jsonMap.put("path", path);

        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = mapper.writeValueAsString(jsonMap);

        response.setHeader("AngusBurgersLoyaly-Error-Reason", "Authentication failed");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");

        response.getWriter().write(jsonResponse);
    }
}
