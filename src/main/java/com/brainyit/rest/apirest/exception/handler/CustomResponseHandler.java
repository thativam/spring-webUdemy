package com.brainyit.rest.apirest.exception.handler;


import com.brainyit.rest.apirest.exception.CustomExceptionResponse;
import com.brainyit.rest.apirest.exception.FileNotFoundException;
import com.brainyit.rest.apirest.exception.FileStorageException;
import com.brainyit.rest.apirest.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomResponseHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<CustomExceptionResponse> handleCustomException(final Exception ex, final WebRequest request) {
        CustomExceptionResponse response = new CustomExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<CustomExceptionResponse> handleBadRequestException(final ResourceNotFoundException ex, final WebRequest request) {
        CustomExceptionResponse response = new CustomExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(FileNotFoundException.class)
    public final ResponseEntity<CustomExceptionResponse> handleBadRequestException(final FileNotFoundException ex, final WebRequest request) {
        CustomExceptionResponse response = new CustomExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }    

    @ExceptionHandler(FileStorageException.class)
    public final ResponseEntity<CustomExceptionResponse> handleBadRequestException(final FileStorageException ex, final WebRequest request) {
        CustomExceptionResponse response = new CustomExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }    

}
