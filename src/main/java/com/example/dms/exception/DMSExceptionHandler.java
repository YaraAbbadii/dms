package com.example.dms.exception;

import com.example.dms.data.GeneralResponse;
import com.example.dms.infrastructure.exception.UndefinedException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DMSExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeneralResponse> validationHandling(MethodArgumentNotValidException ex) {
        String errorField = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(FieldError::getField)
                .orElse("unknown");

        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("Validation failure");

        String fullMessage = "Error in '" + errorField + "': " + errorMessage;

        GeneralResponse response = new GeneralResponse();
        response.setMessage(fullMessage);
        response.setStatusCode(0);
        response.setSuccess(false);
        response.setData(null);

        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(UndefinedException.class)
    public ResponseEntity<GeneralResponse> UndefinedException(UndefinedException e){

        GeneralResponse generalResponse=new GeneralResponse();
        generalResponse.setMessage(e.getMessage());
        generalResponse.setStatusCode(400);
        generalResponse.setSuccess(false);
        generalResponse.setData(null);

        return new ResponseEntity<>(generalResponse, HttpStatus.valueOf(400));
    }

}
