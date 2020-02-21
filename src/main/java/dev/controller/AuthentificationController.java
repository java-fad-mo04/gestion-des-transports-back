package dev.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.CollaborateurVM;
import dev.repository.CollaborateurRepo;

/**
 * WEB API d'authentification.
 *
 * Elle permet de récupérer les informations du collaborateur connecté.
 */
@RestController
public class AuthentificationController {

	private CollaborateurRepo collaborateurRepo;

	public AuthentificationController(CollaborateurRepo collaborateurRepo) {
		this.collaborateurRepo = collaborateurRepo;
	}

	@GetMapping("/me")
	public ResponseEntity<?> quiSuisJe() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return this.collaborateurRepo.findByEmail(email).map(CollaborateurVM::new).map(col -> ResponseEntity.ok(col))
				.orElse(ResponseEntity.badRequest().build());
	}
}
