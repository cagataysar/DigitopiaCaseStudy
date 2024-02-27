package com.digitopia.digitopiacasestudy.exception;

import com.digitopia.digitopiacasestudy.dto.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentExcepiton(IllegalArgumentException exception, WebRequest request) {
        final String message = exception.getMessage();
        final String URI = request.getDescription(false);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createExceptionResponse(message, URI));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundExcepiton(UserNotFoundException exception, WebRequest request) {
        final String message = exception.getMessage();
        final String URI = request.getDescription(false);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createExceptionResponse(message, URI));
    }

    @ExceptionHandler(InvitationCodeNotFoundException.class)
    public ResponseEntity<?> handleInvitationCodeNotFoundException(InvitationCodeNotFoundException exception, WebRequest request) {
        final String message = exception.getMessage();
        final String URI = request.getDescription(false);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createExceptionResponse(message, URI));
    }

    @ExceptionHandler(InvitationExpiredException.class)
    public ResponseEntity<?> handleInvitationExpiredException(InvitationExpiredException exception, WebRequest request) {
        final String message = exception.getMessage();
        final String URI = request.getDescription(false);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createExceptionResponse(message, URI));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception exception, WebRequest request) {
        final String message = exception.getMessage();
        final String URI = request.getDescription(false);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createExceptionResponse(message, URI));
    }

    private ExceptionResponse createExceptionResponse(String message, String URI) {
        return new ExceptionResponse(message, URI);
    }
}
