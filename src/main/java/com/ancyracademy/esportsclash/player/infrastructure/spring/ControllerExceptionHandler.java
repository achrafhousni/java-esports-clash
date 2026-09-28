package com.ancyracademy.esportsclash.player.infrastructure.spring;

import com.ancyracademy.esportsclash.core.domain.exceptions.BadRequestException;
import com.ancyracademy.esportsclash.core.domain.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<?> handleExceptionNotFound(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(BadRequestException.class)
    ResponseEntity<?> handleBadRequestException(BadRequestException e){
        return ResponseEntity.badRequest().build();
    }
}
