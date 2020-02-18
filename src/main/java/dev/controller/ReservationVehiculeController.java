package dev.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.ReservationVehiculeVM;
import dev.domain.Chauffeur;
import dev.domain.Collegue;
import dev.domain.Vehicule;
import dev.repository.ReservationVehiculeRepo;

@RestController
public class ReservationVehiculeController {

	private ReservationVehiculeRepo resvRepo;

	private static final Logger LOG = LoggerFactory.getLogger(dev.controller.ReservationVehiculeController.class);

	public ReservationVehiculeController(ReservationVehiculeRepo resvRepo) {
		this.resvRepo = resvRepo;
	}

	/**
	 * Renvoie la liste des réservations qui concernent une vehicule
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "resv", params = "vid")
	public List<ReservationVehiculeVM> getAllFromVehicule(Integer vid) {
		Vehicule vehicule = new Vehicule();
		vehicule.setId(vid);
		return this.resvRepo.findAllByVehicule(vehicule).stream().map(res -> new ReservationVehiculeVM(res))
				.collect(Collectors.toList());
	}

	/**
	 * Renvoie la liste des réservations de véhicule d'un collègue
	 * 
	 * @param cid
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "resv", params = "cid")
	public List<ReservationVehiculeVM> getAllFromCollegue(Long cid) {
		Collegue collegue = new Collegue();
		collegue.setId(cid);
		return this.resvRepo.findAllByCollegue(collegue).stream().map(res -> new ReservationVehiculeVM(res))
				.collect(Collectors.toList());
	}

	/**
	 * Renvoie la liste des réservations de véhicule d'un chauffeur
	 * 
	 * @param chid
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "resv", params = "chid")
	public List<ReservationVehiculeVM> getAllFromChauffeur(Long chid) {
		Chauffeur chauffeur = new Chauffeur();
		chauffeur.setId(chid);
		return this.resvRepo.findAllByCollegue(chauffeur).stream().map(res -> new ReservationVehiculeVM(res))
				.collect(Collectors.toList());
	}

}
