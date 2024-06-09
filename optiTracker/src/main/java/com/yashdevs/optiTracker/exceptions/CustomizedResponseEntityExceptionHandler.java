package com.yashdevs.optiTracker.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String ERROR_CAPTION = "ERROR: ";

	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<List<ErrorDetails>> handleUserAlreadyExistsException(UserAlreadyExistsException ex,
			WebRequest request) {
		List<ErrorDetails> errors = new ArrayList<>();
		errors.add(
				new ErrorDetails(LocalDateTime.now(), ERROR_CAPTION + ex.getMessage(), request.getDescription(false)));

		return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler({ UserNotFoundException.class, TaskNotFoundException.class, UsernameNotFoundException.class })
	public ResponseEntity<List<ErrorDetails>> handleUserNotFoundException(RuntimeException ex, WebRequest request) {
		List<ErrorDetails> errors = new ArrayList<>();
		errors.add(
				new ErrorDetails(LocalDateTime.now(), ERROR_CAPTION + ex.getMessage(), request.getDescription(false)));

		return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<ErrorDetails> errors = new ArrayList<>();

		errors.add(new ErrorDetails(LocalDateTime.now(), ERROR_CAPTION + ex.getMessage(), request.getDescription(false)));

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
		List<ErrorDetails> errors = new ArrayList<>();

		ex.getConstraintViolations().forEach(c -> errors.add(new ErrorDetails(LocalDateTime.now(), ERROR_CAPTION + c.getMessage(), request.getDescription(false))));

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGeneralException(Exception ex, WebRequest request) {
		List<ErrorDetails> errors = new ArrayList<>();

		errors.add(new ErrorDetails(LocalDateTime.now(), ERROR_CAPTION + ex.getMessage(), request.getDescription(false)));

		return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
