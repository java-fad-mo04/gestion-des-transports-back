package dev.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.ReservationAnnonceVM;
import dev.domain.Annonce;
import dev.domain.Collaborateur;
import dev.domain.ReservationAnnonce;
import dev.domain.Statut;
import dev.exception.BadRequestException;
import dev.exception.ElementNotFoundException;
import dev.exception.ForbiddenOperationException;
import dev.repository.AnnonceRepo;
import dev.repository.CollaborateurRepo;
import dev.repository.ReservationAnnonceRepo;

@RestController
public class ReservationAnnonceController {

	private ReservationAnnonceRepo resaRepo;
	private AnnonceRepo annRepo;
	private CollaborateurRepo collRepo;

	private static final Logger LOG = LoggerFactory.getLogger(dev.controller.ReservationAnnonceController.class);

	public ReservationAnnonceController(ReservationAnnonceRepo resaRepo, AnnonceRepo annRepo,
			CollaborateurRepo collRepo) {
		this.resaRepo = resaRepo;
		this.annRepo = annRepo;
		this.collRepo = collRepo;
	}

	/**
	 * Renvoie une réservations spécifique à partir de son id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "resa", params = "id")
	public ResponseEntity<ReservationAnnonceVM> getFromId(Long id) {
		Optional<ReservationAnnonce> resaOpt = this.resaRepo.findById(id);
		if (!resaOpt.isPresent()) {
			String messageErreur = "Reservation d'id " + id + " introuvable..";
			LOG.error(messageErreur);
			throw new ElementNotFoundException(messageErreur);
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ReservationAnnonceVM(resaOpt.get()));
	}

	/**
	 * Renvoie la liste des réservations qui concernent une annonce
	 * 
	 * @param aid
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "resa", params = "aid")
	public ResponseEntity<List<ReservationAnnonceVM>> getAllFromAnnonce(Long aid) {
		// On vérifie si l'annonce existe
		Optional<Annonce> annOpt = this.annRepo.findById(aid);
		if (!annOpt.isPresent()) {
			String messageErreur = "Annonce d'id " + aid + " introuvable..";
			LOG.error(messageErreur);
			throw new ElementNotFoundException(messageErreur);
		}
		List<ReservationAnnonceVM> listeResAVM = this.resaRepo.findAllByAnnonce(annOpt.get()).stream()
				.map(res -> new ReservationAnnonceVM(res)).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(listeResAVM);
	}

	/**
	 * Renvoie la liste des réservations d'un collaborateur
	 * 
	 * @param cid
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "resa", params = "cid")
	public ResponseEntity<List<ReservationAnnonceVM>> getAllFromCollaborateur(Long cid) {
		// On vérifie si le collaborateur existe
		Optional<Collaborateur> collabOpt = this.collRepo.findById(cid);
		if (!collabOpt.isPresent()) {
			String messageErreur = "Collaborateur d'id " + cid + " introuvable..";
			LOG.error(messageErreur);
			throw new ElementNotFoundException(messageErreur);
		}
		List<ReservationAnnonceVM> listeResAVM = this.resaRepo.findAllByCollaborateur(collabOpt.get()).stream()
				.map(res -> new ReservationAnnonceVM(res)).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(listeResAVM);
	}

	/**
	 * Permet de créer ou de modifier une réservation pour une annonce
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, path = "resa")
	public ResponseEntity<String> creerModifierReservation(@RequestBody ReservationAnnonceVM resaVM) {
		// On vérifie si les paramètres sont bien spécifiés
		if (resaVM == null || resaVM.getAnnonceId() == null || resaVM.getCollaborateurId() == null) {
			String messageErreur = "Requête incomplète - " + resaVM;
			LOG.error(messageErreur);
			throw new BadRequestException(messageErreur);
		}
		// Si le statut n'a pas été défini, il est ACTIF par défaut
		if (resaVM.getStatut() == null
				|| (!resaVM.getStatut().equals(Statut.ACTIF) && !resaVM.getStatut().equals(Statut.ANNULE))) {
			resaVM.setStatut(Statut.ACTIF);
		}
		// On vérifie si l'annonce existe
		Optional<Annonce> annOpt = this.annRepo.findById(resaVM.getAnnonceId());
		if (!annOpt.isPresent()) {
			String messageErreur = "Annonce d'id " + resaVM.getAnnonceId() + " introuvable..";
			LOG.error(messageErreur);
			throw new ElementNotFoundException(messageErreur);
		}

		// On vérifie si le collaborateur existe
		Optional<Collaborateur> collabOpt = this.collRepo.findById(resaVM.getCollaborateurId());
		if (!collabOpt.isPresent()) {
			String messageErreur = "Collaborateur d'id " + resaVM.getCollaborateurId() + " introuvable..";
			LOG.error(messageErreur);
			throw new ElementNotFoundException(messageErreur);
		}

		Annonce annonce = annOpt.get();
		Collaborateur collaborateur = collabOpt.get();

		/** On vérifie si la réservation est conforme */
		// Si le collaborateur qui a fait l'annonce est différent de celui y a
		// répondu
		if (annonce.getCollaborateur().equals(collaborateur)) {
			String messageErreur = "Le collaborateur ne peut pas répondre à sa propre annonce.";
			LOG.error(messageErreur);
			throw new ForbiddenOperationException(messageErreur);
		}

		// Si le nombre de réservation dépasse le nombre de places disponibles
		if (Statut.ACTIF.equals(resaVM.getStatut()) && this.resaRepo.findAllByAnnonceAndStatut(annonce, Statut.ACTIF)
				.stream().count() >= annonce.getNombrePlacesDispo()) {
			String messageErreur = "Le nombre de réservations maximal a déjà été atteint pour cette annonce (id: "
					+ annonce.getId() + ").";
			LOG.error(messageErreur);
			throw new ForbiddenOperationException(messageErreur);
		}

		// Si le collaborateur a déjà réservé pour cette annonce, le statut de
		// la réservation est mis à jour
		Optional<ReservationAnnonce> resaOpt = this.resaRepo.findByAnnonceAndCollaborateur(annonce, collaborateur);
		ReservationAnnonce resa = null;
		if (resaOpt.isPresent()) {
			resa = resaOpt.get();
			// Dans le cas où aucune modification est nécessaire
			if (resaVM.getStatut().equals(resa.getStatut())) {
				String messageErreur = "La réservation existe déjà avec le statut défini: aucun changement à effectuer.";
				LOG.error(messageErreur);
				throw new ForbiddenOperationException(messageErreur);
			}
			resa.setStatut(resaVM.getStatut());
		} else {
			// Si on est dans aucun des derniers cas, on cree la réservation
			resa = new ReservationAnnonce(annonce, collaborateur, resaVM.getStatut());
		}
		this.resaRepo.save(resa);
		return ResponseEntity.status(HttpStatus.OK).body("La réservation a été créée avec succès!");
	}

}
