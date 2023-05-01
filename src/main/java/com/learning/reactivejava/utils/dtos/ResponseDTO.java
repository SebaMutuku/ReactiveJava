package com.learning.reactivejava.utils.dtos;

import org.springframework.http.HttpStatus;

public record ResponseDTO(Object payload, HttpStatus statusCode, String message) {
}
