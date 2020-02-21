package dev.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.Vehicule;
import dev.exception.ElementNotFoundException;
import dev.repository.VehiculeRepo;

@RestController
public class VehiculeController {

	private VehiculeRepo vRepo;

	private static final Logger LOG = LoggerFactory.getLogger(dev.controller.VehiculeController.class);

	public VehiculeController(VehiculeRepo vRepo) {
		this.vRepo = vRepo;
	}

	/** Retourne la liste des véhicules */
	@RequestMapping(method = RequestMethod.GET, path = "vehicules")
	public List<Vehicule> getVehicules() {
		List<Vehicule> listeVehicules = this.vRepo.findAll();
		return listeVehicules;
	}

	/** Retourne un véhicule à partir de son id */
	@RequestMapping(method = RequestMethod.GET, path = "vehicule", params = "vid")
	public Vehicule getVehicule(Long vid) {
		Optional<Vehicule> vehiOpt = this.vRepo.findById(vid);
		if (!vehiOpt.isPresent()) {
			String messageErreur = "Véhicule d'id " + vid + " introuvable..";
			LOG.error(messageErreur);
			throw new ElementNotFoundException(messageErreur);
		}
		return vehiOpt.get();
	}

}
