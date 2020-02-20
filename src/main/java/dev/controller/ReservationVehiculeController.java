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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.ReservationVehiculeVM;
import dev.domain.Chauffeur;
import dev.domain.Collaborateur;
import dev.domain.ReservationVehicule;
import dev.domain.StatutVehicule;
import dev.domain.Vehicule;
import dev.exception.BadRequestException;
import dev.exception.ElementNotFoundException;
import dev.exception.ForbiddenOperationException;
import dev.repository.ChauffeurRepo;
import dev.repository.CollaborateurRepo;
import dev.repository.ReservationVehiculeRepo;
import dev.repository.VehiculeRepo;

@RestController
public class ReservationVehiculeController {

	private ReservationVehiculeRepo resvRepo;
	private CollaborateurRepo collabRepo;
	private VehiculeRepo vehiRepo;
	private ChauffeurRepo chffRepo;

	private static final Logger LOG = LoggerFactory.getLogger(dev.controller.ReservationVehiculeController.class);

	public ReservationVehiculeController(ReservationVehiculeRepo resvRepo, CollaborateurRepo collabRepo,
			VehiculeRepo vehiRepo, ChauffeurRepo chffRepo) {
		this.resvRepo = resvRepo;
		this.collabRepo = collabRepo;
		this.vehiRepo = vehiRepo;
		this.chffRepo = chffRepo;
	}

	/**
	 * Renvoie la liste des réservations qui concernent une vehicule
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "resv", params = "vid")
	public List<ReservationVehiculeVM> getAllFromVehicule(Long vid) {
		Optional<Vehicule> vehiOpt = this.vehiRepo.findById(vid);
		if (!vehiOpt.isPresent()) {
			String messageErreur = "Véhicule d'id " + vid + " introuvable..";
			LOG.error(messageErreur);
			throw new ElementNotFoundException(messageErreur);
		}
		return this.resvRepo.findAllByVehicule(vehiOpt.get()).stream().map(res -> new ReservationVehiculeVM(res))
				.collect(Collectors.toList());
	}

	/**
	 * Renvoie la liste des réservations de véhicule d'un collaborateur
	 * 
	 * @param cid
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "resv", params = "cid")
	public List<ReservationVehiculeVM> getAllFromCollaborateur(Long cid) {
		Optional<Collaborateur> collabOpt = this.collabRepo.findById(cid);
		if (!collabOpt.isPresent()) {
			String messageErreur = "Collaborateur d'id " + cid + " introuvable..";
			LOG.error(messageErreur);
			throw new ElementNotFoundException(messageErreur);
		}
		return this.resvRepo.findAllByCollaborateur(collabOpt.get()).stream().map(res -> new ReservationVehiculeVM(res))
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
		Optional<Chauffeur> chffOpt = this.chffRepo.findById(chid);
		if (!chffOpt.isPresent()) {
			String messageErreur = "Chauffeur d'id " + chid + " introuvable..";
			LOG.error(messageErreur);
			throw new ElementNotFoundException(messageErreur);
		}
		return this.resvRepo.findAllByCollaborateur(chffOpt.get()).stream().map(res -> new ReservationVehiculeVM(res))
				.collect(Collectors.toList());
	}

	/**
	 * Permet de créer ou de modifier une réservation pour une annonce
	 * 
	 * @param resvVM
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, path = "resv")
	public ResponseEntity<String> creerModifierReservation(@RequestBody ReservationVehiculeVM resvVM) {
		// Vérification des paramètres
		String messageErreur = "";
		if (resvVM == null) {
			messageErreur = "Paramètre manquant";
			LOG.error(messageErreur);
			throw new BadRequestException(messageErreur);
		}
		if (resvVM.getDateDebut() == null) {
			messageErreur += ",date de début ";
		}
		if (resvVM.getDateFin() == null) {
			messageErreur += ",date de fin ";
		}
		if (resvVM.getCollaborateurId() == null || resvVM.getCollaborateurId() <= 0) {
			messageErreur += ",id collaborateur ";
		}
		if (resvVM.getVehiculeId() == null || resvVM.getVehiculeId() <= 0) {
			messageErreur += ",id vehicule ";
		}
		if (!messageErreur.isEmpty()) {
			messageErreur = "Paramètres incorrects " + messageErreur;
			LOG.error(messageErreur);
			throw new BadRequestException(messageErreur);
		}

		// On vérifie si le collaborateur existe
		Optional<Collaborateur> collabOpt = this.collabRepo.findById(resvVM.getCollaborateurId());
		if (!collabOpt.isPresent()) {
			messageErreur = "Collaborateur d'id " + resvVM.getCollaborateurId() + " introuvable..";
			LOG.error(messageErreur);
			throw new ElementNotFoundException(messageErreur);
		}

		// On vérifie si le véhicule existe
		Optional<Vehicule> vehiOpt = this.vehiRepo.findById(resvVM.getVehiculeId());
		if (!vehiOpt.isPresent()) {
			messageErreur = "Véhicule d'id " + resvVM.getVehiculeId() + " introuvable..";
			LOG.error(messageErreur);
			throw new ElementNotFoundException(messageErreur);
		}

		// Si un chauffeur est désigné pour la réservation
		Chauffeur chauffeur = null;
		if (resvVM.isAvecChauffeur() && resvVM.getChauffeurId() != null) {
			// On vérifie si le chauffeur existe
			Optional<Chauffeur> chffOpt = this.chffRepo.findById(resvVM.getChauffeurId());
			if (!chffOpt.isPresent()) {
				messageErreur = "Chauffeur d'id " + resvVM.getChauffeurId() + " introuvable..";
				LOG.error(messageErreur);
				throw new ElementNotFoundException(messageErreur);
			}
			chauffeur = chffOpt.get();
		}

		Collaborateur collaborateur = collabOpt.get();
		Vehicule vehicule = vehiOpt.get();

		/** On vérifie si la réservation est conforme */
		// Si date de début est postérieure à la date de fin
		if (resvVM.getDateDebut().isAfter(resvVM.getDateFin())) {
			messageErreur = "La date de début de réservation (" + resvVM.getDateDebut()
					+ ") est postérieure à la date de fin (" + resvVM.getDateFin() + ")";
			LOG.error(messageErreur);
			throw new ForbiddenOperationException(messageErreur);
		}

		// Si le véhicule est indisponible
		if (!vehicule.getStatut().equals(StatutVehicule.EN_SERVICE)) {
			messageErreur = "Le véhicule spécifié n'est pas disponible: " + vehicule;
			LOG.error(messageErreur);
			throw new ForbiddenOperationException(messageErreur);
		}

		// Si le véhicule est déjà réservé sur la période donnée
		if (this.resvRepo.getAllByVehiculeAndBetweenDates(vehicule, resvVM.getDateDebut(), resvVM.getDateFin()).stream()
				.count() != 0) {
			messageErreur = "Le véhicule spécifié (" + vehicule
					+ ") a déjà été réservé sur la période donnée: date de début (" + resvVM.getDateDebut()
					+ "), date de fin (" + resvVM.getDateFin() + ")";
			LOG.error(messageErreur);
			throw new ForbiddenOperationException(messageErreur);
		}

		if (chauffeur != null) {
			// Si le collaborateur qui a réservé est également le chauffeur
			if (chauffeur.equals(collaborateur)) {
				messageErreur = "Le collaborateur et le chauffeur ne peuvent pas être identiques: " + chauffeur;
				LOG.error(messageErreur);
				throw new ForbiddenOperationException(messageErreur);
			}

			// Si le chauffeur a déjà une réservation sur la période donnée
			if (this.resvRepo.getAllByChauffeurAndBetweenDates(chauffeur, resvVM.getDateDebut(), resvVM.getDateFin())
					.stream().count() != 0) {
				messageErreur = "Le chauffeur désigné a déjà une réservation sur la période donnée: date de début ("
						+ resvVM.getDateDebut() + "), date de fin (" + resvVM.getDateFin() + ")";
				LOG.error(messageErreur);
				throw new ForbiddenOperationException(messageErreur);
			}
		}

		// Si on est dans aucun des derniers cas, on cree la réservation
		ReservationVehicule resv = new ReservationVehicule(resvVM.getId(), resvVM.getDateDebut(), resvVM.getDateFin(),
				resvVM.isAvecChauffeur(), collaborateur, vehicule, chauffeur);
		this.resvRepo.save(resv);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("La réservation a été créée avec succès!");
	}

	/**
	 * Assigner une réservation à un chauffeur
	 * 
	 * @param rvid,
	 *            chid
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, path = "resvch")
	public ResponseEntity<String> assignerChauffeur(@RequestParam Long rvid, @RequestParam Long chid) {
		// Vérification des paramètres
		if (rvid == null || rvid <= 0) {
			String messageErreur = "Identifiant manquant ou incorrect";
			LOG.error(messageErreur);
			throw new BadRequestException(messageErreur);
		}
		if (chid == null || chid <= 0) {
			String messageErreur = "Identifiant du chauffeur manquant ou incorrect";
			LOG.error(messageErreur);
			throw new BadRequestException(messageErreur);
		}

		// On vérifie si la réservation existe
		Optional<ReservationVehicule> resvOpt = this.resvRepo.findById(rvid);
		if (!resvOpt.isPresent()) {
			String messageErreur = "Réservation d'id " + rvid + " introuvable..";
			LOG.error(messageErreur);
			throw new ElementNotFoundException(messageErreur);
		}

		// On récupère la réservation
		ReservationVehicule resv = resvOpt.get();
		// Si le chauffeur est déjà désigné pour la réservation, on ne modifie
		// rien et génère un exception
		if (resv.getChauffeur() != null) {
			String messageErreur = "La réservation a déjà été assigné au chauffeur (" + resv.getChauffeur() + ")";
			LOG.error(messageErreur);
			throw new ForbiddenOperationException(messageErreur);
		}

		// On vérifie si le chauffeur existe
		Optional<Chauffeur> chffOpt = this.chffRepo.findById(chid);
		if (!chffOpt.isPresent()) {
			String messageErreur = "Chauffeur d'id " + chid + " introuvable..";
			LOG.error(messageErreur);
			throw new ElementNotFoundException(messageErreur);
		}
		Chauffeur chauffeur = chffOpt.get();

		// Si le chauffeur a déjà une réservation sur la période donnée
		if (this.resvRepo.getAllByChauffeurAndBetweenDates(chauffeur, resv.getDateDebut(), resv.getDateFin()).stream()
				.count() != 0) {
			String messageErreur = "Le chauffeur désigné a déjà une réservation sur la période donnée: date de début ("
					+ resv.getDateDebut() + "), date de fin (" + resv.getDateFin() + ")";
			LOG.error(messageErreur);
			throw new ForbiddenOperationException(messageErreur);
		}

		// Si on est dans aucun des autres cas, on assigne le chauffeur à la
		// réservation
		resv.setChauffeur(chauffeur);
		this.resvRepo.save(resv);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Le chauffeur a été correctement assigné.");
	}

}
