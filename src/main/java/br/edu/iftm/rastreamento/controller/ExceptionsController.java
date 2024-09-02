package br.edu.iftm.rastreamento.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.edu.iftm.rastreamento.service.exceptions.pacoteException;

@ControllerAdvice
public class ExceptionsController {

	@ExceptionHandler(pacoteException.class)
	public ResponseEntity<?> naoAchei(pacoteException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}
}
