package dev.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.exception.BadRequestException;
import dev.exception.ElementNotFoundException;
import dev.exception.ForbiddenOperationException;

/*
 * Permet de gérer les exceptions retournés par les controleurs 
 */
@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {

	private static final Logger LOG = LoggerFactory.getLogger(dev.controller.VehiculeController.class);

	/**
	 * Gestion des erreurs
	 */
	@ExceptionHandler(value = { ElementNotFoundException.class })
	public ResponseEntity<String> onNotFound(ElementNotFoundException exception) {
		LOG.error(exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
	}

	@ExceptionHandler(value = { BadRequestException.class })
	public ResponseEntity<String> onBadRequest(BadRequestException exception) {
		LOG.error(exception.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}

	@ExceptionHandler(value = { ForbiddenOperationException.class })
	public ResponseEntity<String> onForbiddenOperation(ForbiddenOperationException exception) {
		LOG.error(exception.getMessage());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
	}

}
