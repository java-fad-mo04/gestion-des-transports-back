package dev.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.ReservationAnnonceVM;
import dev.domain.Annonce;
import dev.domain.Collaborateur;
import dev.repository.ReservationAnnonceRepo;

@RestController
public class ReservationAnnonceController {

	private ReservationAnnonceRepo resaRepo;

	private static final Logger LOG = LoggerFactory.getLogger(dev.controller.ReservationAnnonceController.class);

	public ReservationAnnonceController(ReservationAnnonceRepo resaRepo) {
		this.resaRepo = resaRepo;
	}

	/**
	 * Renvoie la liste des réservations qui concernent une annonce
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "resa", params = "id")
	public List<ReservationAnnonceVM> getAllFromAnnonce(Long id) {
		Annonce annonce = new Annonce();
		annonce.setId(id);
		return this.resaRepo.findAllByAnnonce(annonce).stream().map(res -> new ReservationAnnonceVM(res))
				.collect(Collectors.toList());
	}

	/**
	 * Renvoie la liste des réservations d'un collaborateur
	 * 
	 * @param cid
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "resa", params = "cid")
	public List<ReservationAnnonceVM> getAllFromCollaborateur(Long cid) {
		Collaborateur collaborateur = new Collaborateur();
		collaborateur.setId(cid);
		return this.resaRepo.findAllByCollaborateur(collaborateur).stream().map(res -> new ReservationAnnonceVM(res))
				.collect(Collectors.toList());
	}

}
