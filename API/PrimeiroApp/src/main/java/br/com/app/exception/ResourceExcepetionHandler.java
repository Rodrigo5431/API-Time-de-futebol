package br.com.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExcepetionHandler {

	@ExceptionHandler (ObjectNotFoundException.class)
	public ResponseEntity<StandardError>ObjectNotFound(ObjectNotFoundException ex,
			HttpServletRequest request){
		ValidationError errors = new ValidationError
				(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
						"Object Not Found", ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
	}
	public ResponseEntity<StandardError>ValidationError
	(MethodArgumentNotValidException ex, HttpServletRequest request){
		ValidationError errors = new ValidationError (System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value(),"field  null", "Erro na validação de campo",
				request.getRequestURI());
				for (FieldError x : ex.getBindingResult().getFieldErrors()) {
					errors.addError(x.getField(), x.getDefaultMessage());
				}
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
}