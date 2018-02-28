package com.prueba.votacion.controller.exceptions.advices;

import com.prueba.votacion.controller.exceptions.PersonaNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


class PersonaControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {PersonaNotFoundException.class})
    protected ResponseEntity<Object> notFound(PersonaNotFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
