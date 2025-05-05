package com.example.dms.infrastructure.security.exception;

import com.example.dms.data.GeneralResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<GeneralResponse> handleExpiredJwtException(ExpiredJwtException e) {
        GeneralResponse response = new GeneralResponse();
        response.setMessage("Unauthorized: Token has expired");
        response.setStatusCode(401);
        response.setSuccess(false);
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<GeneralResponse> handleInvalidJwtException(JwtException e) {
        GeneralResponse response = new GeneralResponse();
        response.setMessage("Unauthorized: Unprocessable entity");
        response.setStatusCode(422);
        response.setSuccess(false);
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<GeneralResponse> malformed(JwtException e) {
        GeneralResponse response = new GeneralResponse();
        response.setMessage("Unauthorized");
        response.setStatusCode(401);
        response.setSuccess(false);
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }



    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<GeneralResponse> handleBadCredentialsException(BadCredentialsException e) {
        GeneralResponse response = new GeneralResponse();
        response.setMessage("Unauthorized: Invalid credentials or token");
        response.setStatusCode(450);
        response.setSuccess(false);
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<GeneralResponse> handleGenericException(Exception e) {
//        GeneralResponse response = new GeneralResponse();
//        response.setMessage("Unauthorized: " + e.getMessage());
//        response.setStatusCode(401);
//        response.setSuccess(false);
//        response.setData(null);
//        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
//    }



}
