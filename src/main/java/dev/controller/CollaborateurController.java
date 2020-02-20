package dev.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.CollaborateurVM;
import dev.domain.Collaborateur;
import dev.exception.ElementNotFoundException;
import dev.repository.CollaborateurRepo;

@RestController
public class CollaborateurController {

	private CollaborateurRepo cRepo;

	private static final Logger LOG = LoggerFactory.getLogger(dev.controller.VehiculeController.class);

	public CollaborateurController(CollaborateurRepo cRepo) {
		this.cRepo = cRepo;
	}

	/** Retourne la liste des collaborateurs */
	@RequestMapping(method = RequestMethod.GET, path = "collaborateurs")
	public List<CollaborateurVM> getCollaborateur() {
		List<Collaborateur> listeCollaborateurs = this.cRepo.findAll();
		return listeCollaborateurs.stream().map(col -> new CollaborateurVM(col)).collect(Collectors.toList());
	}

	/**
	 * Renvoie un collaborateur spécifique à partir de son id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "collaborateur", params = "id")
	public ResponseEntity<CollaborateurVM> getFromId(Long id) {
		Optional<Collaborateur> chffOpt = this.cRepo.findById(id);
		if (!chffOpt.isPresent()) {
			String messageErreur = "Reservation d'id " + id + " introuvable..";
			LOG.error(messageErreur);
			throw new ElementNotFoundException(messageErreur);
		}
		return ResponseEntity.status(HttpStatus.OK).body(new CollaborateurVM(chffOpt.get()));
	}

}
