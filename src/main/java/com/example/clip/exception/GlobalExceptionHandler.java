package com.example.clip.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler{
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<String> fieldErrors = result.getFieldErrors()
									    .stream()
									    .map(x -> x.getDefaultMessage())
									    .collect(Collectors.toList());
		
		Map<String, Object> body = new LinkedHashMap<>();
	    body.put("timestamp", LocalDateTime.now());
	    body.put("cause", ex.getMessage());
	    body.put("errors", fieldErrors);
	    log.error(ex.getMessage());
		return ResponseEntity.badRequest().body((ex.getMessage()));
	}
	
	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<Object> dataAccessException(DataAccessException ex) {
		Map<String, Object> body = new LinkedHashMap<>();
	    body.put("timestamp", LocalDateTime.now());
	    body.put("status", HttpStatus.BAD_REQUEST.value());
	    body.put("message", ex.getMessage());
	    body.put("cause", ex.getCause());
	    log.error(ex.getMessage());
		return ResponseEntity.badRequest().body((ex.getMessage()));
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	public ResponseEntity<Object> constraintException(ConstraintViolationException ex) {
		List<String> errores = new ArrayList<>();
		ex.getConstraintViolations().forEach(
				e ->{
					errores.add(e.getMessage());
				}
			);
		
		Map<String, Object> body = new LinkedHashMap<>();
	    body.put("timestamp", LocalDateTime.now());
	    body.put("status", HttpStatus.BAD_REQUEST.value());
	    body.put("message", ex.getMessage());
	    body.put("cause", errores.get(0));
	    log.error(ex.getMessage() + errores.get(0));
		return ResponseEntity.badRequest().body(body);
	}
	
	@ExceptionHandler(UsernameTokenNotFoundException.class)
	@ResponseBody
	public ResponseEntity<Object> usernameNotFound(UsernameTokenNotFoundException ex) {
		
		Map<String, Object> body = new LinkedHashMap<>();
	    body.put("timestamp", LocalDateTime.now());
	    body.put("status", HttpStatus.UNAUTHORIZED.value());
	    body.put("message", ex.getMessage());
	    body.put("cause", ex.getCause());
	    log.error(ex.getMessage());
		return ResponseEntity.badRequest().body(body);
	}
	
	@ExceptionHandler(NotDataFoundException.class)
	@ResponseBody
	public ResponseEntity<Object> notDataFound(NotDataFoundException ex) {
		
		Map<String, Object> body = new LinkedHashMap<>();
	    body.put("timestamp", LocalDateTime.now());
	    body.put("status", HttpStatus.NO_CONTENT.value());
	    body.put("message", ex.getMessage());
	    body.put("cause", ex.getCause());
	    log.error(ex.getMessage());
		return ResponseEntity.badRequest().body(body);
	}
	
	@ExceptionHandler(BussinesException.class)
	@ResponseBody
	public ResponseEntity<Object> bussinesException(BussinesException ex) {
		
		Map<String, Object> body = new LinkedHashMap<>();
	    body.put("timestamp", LocalDateTime.now());
	    body.put("status", HttpStatus.NOT_IMPLEMENTED.value());
	    body.put("message", ex.getMessage());
	    body.put("cause", ex.getCause());
	    log.error(ex.getMessage());
		return ResponseEntity.badRequest().body(body);
	}
	
}