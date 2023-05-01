package com.learning.reactivejava.api;

import com.learning.reactivejava.exception.GenericException;
import com.learning.reactivejava.utils.dtos.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GenericExceptionAp {

    @ExceptionHandler(value = GenericException.class)
    public ResponseEntity<Mono<ResponseDTO>> internalSeverError(@RequestBody GenericException genericException) {
        return ResponseEntity.internalServerError().body(Mono.just(new ResponseDTO(null, HttpStatus.INTERNAL_SERVER_ERROR, genericException.getMessage())));
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Mono<ResponseDTO>> badRequest(@RequestBody RuntimeException exception) {
        return ResponseEntity.badRequest().body(Mono.just(new ResponseDTO(null, HttpStatus.EXPECTATION_FAILED, exception.getMessage())));
    }
}
