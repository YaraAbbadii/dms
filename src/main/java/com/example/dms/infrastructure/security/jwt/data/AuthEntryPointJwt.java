package com.example.dms.infrastructure.security.jwt.data;

import com.example.dms.data.GeneralResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        GeneralResponse errorResponse = new GeneralResponse();
        errorResponse.setSuccess(false);

        Integer statusCode = 401;

        String token = request.getHeader("Authorization");
        try {
            if (token == null || token.isEmpty()) {
                errorResponse.setData(null);
                errorResponse.setMessage("Token is missing");
            } else {
                errorResponse.setData(null);
                errorResponse.setMessage("Token is invalid");
            }
        } catch (MalformedJwtException e) {
            statusCode = HttpStatus.UNAUTHORIZED.value();

            errorResponse.setData(null);
            errorResponse.setMessage("Unauthorized");
        } catch (JwtException e) {
            statusCode = HttpStatus.UNPROCESSABLE_ENTITY.value();

            errorResponse.setData(null);
            errorResponse.setMessage("Unprocessable entity");
        }

        errorResponse.setStatusCode(statusCode);

        response.setStatus(statusCode);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
