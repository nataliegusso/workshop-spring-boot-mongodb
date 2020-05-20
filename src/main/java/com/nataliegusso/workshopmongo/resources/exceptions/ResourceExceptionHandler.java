package com.nataliegusso.workshopmongo.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nataliegusso.workshopmongo.services.exception.ObjectNotFoundException;

//Mudar erro de 500 para 404
@ControllerAdvice  //Responsável por tratar erros nas requisições  // as declarações abaixo são meio padrão, só muda o nome dado
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)  //padrão
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}