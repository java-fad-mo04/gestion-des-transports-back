package dev.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.CollaborateurVM;
import dev.domain.Collaborateur;
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

}
