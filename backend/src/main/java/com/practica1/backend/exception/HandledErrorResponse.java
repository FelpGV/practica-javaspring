package com.practica1.backend.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class HandledErrorResponse {
    private String message;
    private LocalDateTime timestamp;
    private HttpStatus status;
}
