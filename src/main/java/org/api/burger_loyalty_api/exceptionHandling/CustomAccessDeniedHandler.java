package org.api.burger_loyalty_api.exceptionHandling;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        LocalDateTime localDateTime = LocalDateTime.now();
        String message = (accessDeniedException != null && accessDeniedException.getMessage() != null) ? accessDeniedException.getMessage() : "Unauthorized";
        String path = request.getRequestURI();

        Map<String,Object> jsonMap = new HashMap<>();

        jsonMap.put("timestamp", localDateTime.toString());
        jsonMap.put("status", HttpStatus.FORBIDDEN.value());
        jsonMap.put("error", HttpStatus.FORBIDDEN.getReasonPhrase());
        jsonMap.put("message", message);
        jsonMap.put("path", path);

        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = mapper.writeValueAsString(jsonMap);

        response.setHeader("AngusBurgersLoyaly-Error-Reason", "Authentication failed");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json;charset=UTF-8");

        response.getWriter().write(jsonResponse);
    }
}
