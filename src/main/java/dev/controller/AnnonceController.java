package dev.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.AnnonceVM;
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
public class AnnonceController {

	private AnnonceRepo annRepo;
	private CollaborateurRepo collRepo;
	private ReservationAnnonceRepo resaRepo;

	private static final Logger LOG = LoggerFactory.getLogger(dev.controller.VehiculeController.class);

	public AnnonceController(AnnonceRepo aRepo, CollaborateurRepo collRepo, ReservationAnnonceRepo resaRepo) {
		this.annRepo = aRepo;
		this.collRepo = collRepo;
		this.resaRepo = resaRepo;
	}

	/** Retourne une annonce à partir de son id */
	@RequestMapping(method = RequestMethod.GET, path = "annonce", params = "aid")
	public AnnonceVM get(Long aid) {
		Optional<Annonce> annOpt = this.annRepo.findById(aid);
		if (!annOpt.isPresent()) {
			String messageErreur = "Annonce d'id " + aid + " introuvable..";
			LOG.error(messageErreur);
			throw new ElementNotFoundException(messageErreur);
		}
		return new AnnonceVM(annOpt.get());
	}

	/** Retourne la liste des annonces */
	@RequestMapping(method = RequestMethod.GET, path = "annonces")
	public List<AnnonceVM> getAll() {
		List<Annonce> listeAnnonces = this.annRepo.findAll();
		return listeAnnonces.stream().map(annonce -> new AnnonceVM(annonce)).collect(Collectors.toList());
	}

	/**
	 * Permet de créer ou modifier une annonce
	 * 
	 * @param annonceReçue
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, path = "annonces")
	public ResponseEntity<String> creer(@RequestBody AnnonceVM annVM) {
		// Vérification des paramètres
		String messageErreur = "";
		if (annVM == null) {
			messageErreur = "Paramètre manquant";
			LOG.error(messageErreur);
			throw new BadRequestException(messageErreur);
		}
		if (annVM.getDateDepart() == null) {
			messageErreur += ",date de départ ";
		}
		if (annVM.getAdresseDepart() == null || annVM.getAdresseDepart().trim().isEmpty()) {
			messageErreur += ",lieu de départ ";
		}
		if (annVM.getAdresseArrivee() == null || annVM.getAdresseArrivee().trim().isEmpty()) {
			messageErreur += ",lieu d'arrivée ";
		}
		if (annVM.getImmatriculation() == null || annVM.getImmatriculation().trim().isEmpty()) {
			messageErreur += ",immatriculation ";
		}
		if (annVM.getMarque() == null || annVM.getMarque().trim().isEmpty()) {
			messageErreur += ",marque ";
		}
		if (annVM.getModele() == null || annVM.getModele().trim().isEmpty()) {
			messageErreur += ",modele ";
		}
		if (annVM.getNombrePlacesDispo() == null || annVM.getNombrePlacesDispo() <= 0) {
			messageErreur += ",nombre de places disponibles ";
		}
		if (annVM.getCollaborateurId() == null || annVM.getCollaborateurId() <= 0) {
			messageErreur += ",id collaborateur ";
		}
		if (!messageErreur.isEmpty()) {
			messageErreur = "Paramètres incorrects" + messageErreur;
			LOG.error(messageErreur);
			throw new BadRequestException(messageErreur);
		}
		// On défini le statut à ACTIF par défaut
		if (annVM.getStatut() == null
				|| (!annVM.getStatut().equals(Statut.ACTIF) && !annVM.getStatut().equals(Statut.ANNULE))) {
			annVM.setStatut(Statut.ACTIF);
		}

		// On vérifie si le collaborateur existe
		Optional<Collaborateur> collabOpt = this.collRepo.findById(annVM.getCollaborateurId());
		if (!collabOpt.isPresent()) {
			messageErreur = "Collaborateur d'id " + annVM.getCollaborateurId() + " introuvable..";
			LOG.error(messageErreur);
			throw new ElementNotFoundException(messageErreur);
		}

		Annonce annonce = new Annonce(annVM.getId(), annVM.getAdresseDepart(), annVM.getAdresseArrivee(),
				annVM.getImmatriculation(), annVM.getMarque(), annVM.getModele(), annVM.getNombrePlacesDispo(),
				annVM.getDateDepart(), annVM.getStatut(), collabOpt.get());

		this.annRepo.save(annonce);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("L'annonce " + annonce + " a été créée avec succès!");
	}

	/**
	 * Modifier le statut d'une annonce
	 * 
	 * @param aid,
	 *            annVM
	 * @return
	 */
	@Transactional
	@RequestMapping(method = RequestMethod.POST, path = "annonce")
	public ResponseEntity<String> modifierStatut(@RequestParam Long aid, @RequestParam Statut statut) {
		// Vérification des paramètres
		if (aid == null || aid <= 0) {
			String messageErreur = "Identifiant manquant ou incorrect";
			LOG.error(messageErreur);
			throw new BadRequestException(messageErreur);
		}
		if (statut == null || (!Statut.ACTIF.equals(statut) && !Statut.ANNULE.equals(statut))) {
			String messageErreur = "Statut manquant ou incorrect";
			LOG.error(messageErreur);
			throw new BadRequestException(messageErreur);
		}

		// On vérifie si l'annonce existe
		Optional<Annonce> annOpt = this.annRepo.findById(aid);
		if (!annOpt.isPresent()) {
			String messageErreur = "Annonce d'id " + aid + " introuvable..";
			LOG.error(messageErreur);
			throw new ElementNotFoundException(messageErreur);
		}
		// On récupère l'annonce
		Annonce annonce = annOpt.get();
		// Si le statut est le même, on ne modifie rien et génère un exception
		if (statut.equals(annonce.getStatut())) {
			String messageErreur = "Rien à modifier";
			LOG.error(messageErreur);
			throw new ForbiddenOperationException(messageErreur);
		}
		// Autrement si on annule l'annonce, on annule également toutes les
		// réservations qui y sont associées
		if (Statut.ANNULE.equals(statut)) {
			annonce.setStatut(statut);
			List<ReservationAnnonce> listeResa = this.resaRepo.findAllByAnnonceAndStatut(annonce, Statut.ACTIF);
			for (ReservationAnnonce resa : listeResa) {
				resa.setStatut(statut);
				this.resaRepo.save(resa);
			}
			// Enfin on active l'annonce, on l'active simplement
		} else {
			annonce.setStatut(statut);
		}
		this.annRepo.save(annonce);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("L'annonce " + annonce + " a été modifiée avec succès!");
	}

}
