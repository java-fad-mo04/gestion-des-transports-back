package dev.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.ChauffeurVM;
import dev.domain.Chauffeur;
import dev.repository.ChauffeurRepo;

@RestController
public class ChauffeurController {

	private ChauffeurRepo cRepo;

	private static final Logger LOG = LoggerFactory.getLogger(dev.controller.VehiculeController.class);

	public ChauffeurController(ChauffeurRepo cRepo) {
		this.cRepo = cRepo;
	}

	/** Retourne la liste des chauffeurs */
	@RequestMapping(method = RequestMethod.GET, path = "chauffeurs")
	public List<ChauffeurVM> getChauffeur() {
		List<Chauffeur> listeChauffeurs = this.cRepo.findAll();
		return listeChauffeurs.stream().map(col -> new ChauffeurVM(col)).collect(Collectors.toList());
	}

}
