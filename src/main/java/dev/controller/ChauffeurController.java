package dev.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.ChauffeurVM;
import dev.domain.Chauffeur;
import dev.domain.Collaborateur;
import dev.exception.BadRequestException;
import dev.exception.ElementNotFoundException;
import dev.repository.ChauffeurRepo;
import dev.repository.CollaborateurRepo;

@RestController
@CrossOrigin
public class ChauffeurController {

	private ChauffeurRepo chffRepo;
	private CollaborateurRepo collabRepo;

	private static final Logger LOG = LoggerFactory.getLogger(dev.controller.ChauffeurController.class);

	public ChauffeurController(ChauffeurRepo chffRepo, CollaborateurRepo collabRepo) {
		this.chffRepo = chffRepo;
		this.collabRepo = collabRepo;
	}

	/**
	 * Renvoie un chauffeur spécifique à partir de son id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "chauffeur", params = "id")
	public ResponseEntity<ChauffeurVM> getFromId(Long id) {
		Optional<Chauffeur> chffOpt = this.chffRepo.findById(id);
		if (!chffOpt.isPresent()) {
			String messageErreur = "Reservation d'id " + id + " introuvable..";
			LOG.error(messageErreur);
			throw new ElementNotFoundException(messageErreur);
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ChauffeurVM(chffOpt.get()));
	}
	
	/** 
	 * Retourne la liste de tous les chauffeurs 
	 * 
	 * @param 
	 * @return
	 */	 
	@RequestMapping(method = RequestMethod.GET, path = "chauffeurs")
	public List<ChauffeurVM> getChauffeurs() {
		LOG.info( "*** Recuperer tous les chauffeurs ***");
		List<Chauffeur> listeChauffeurs = this.chffRepo.findAll();
		LOG.info( listeChauffeurs.get(0).toString());
		return listeChauffeurs.stream().map(col -> new ChauffeurVM(col)).collect(Collectors.toList());
	}

	/**
	 * Renvoie de tous les  chauffeurs commencant par matricule, nom, prenom passés en paramètre
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "chauffeursFiltres") 
	public List<ChauffeurVM> getChauffeurFiltreParMatriculeNom( @RequestParam("matricule") 	String matricule, 
			                                                    @RequestParam("nom") 		String nom,
			                                                    @RequestParam("prenom") 	String prenom) {
		LOG.info( "*** Filtrer les chauffeurs par matricule/nom/prenom : " + matricule + '/' + nom + '/' + prenom);
		List<Chauffeur> listeChauffeurs = this.chffRepo.findByMatriculeStartingWithAndNomStartingWithAndPrenomStartingWith( matricule, nom, prenom);
		return listeChauffeurs.stream().map(chauffeur -> new ChauffeurVM( chauffeur)).collect(Collectors.toList());
	}
	
	/**
	 * Cree un chauffeur à partir d'un collaborateur identifié par son matricule recue
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, path = "chauffeurCree") 
	public ResponseEntity<String> createChauffeur( @RequestParam("matricule") 	String matricule) {
		LOG.info( "*** Creer le chauffeur de matricule : " + matricule );
		
		// On vérifie si le collaborateur existe
		Optional<Collaborateur> collabOpt = this.collabRepo.findByMatricule( matricule);
		if (!collabOpt.isPresent()) {
			String messageErreur = "";			
			messageErreur = "Collaborateur de matricule : " + matricule + " introuvable..";
			LOG.error(messageErreur);
			throw new ElementNotFoundException(messageErreur);
		}

		// On vérifie si le chauffeur n'a pas déja été créé
		Optional<Chauffeur> chauffeurOpt = this.chffRepo.findById( collabOpt.get().getId());
		if (chauffeurOpt.isPresent()) {
			String messageErreur = "";			
			messageErreur = "Chauffeur de matricule : " + matricule + " déja existant..";
			LOG.error(messageErreur);
			throw new ElementNotFoundException(messageErreur);
		}

		LOG.info( ">>>> Creer le chauffeur de matricule : " + matricule );
		// Creer le chauffeur
		Chauffeur chauffeur = new Chauffeur(collabOpt.get(), "Permis B");
		this.chffRepo.save(chauffeur);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Le chauffeur a été créé avec succès!");
		
	}	
	/**
	 * Permet de créer ou de modifier une réservation pour une annonce
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, path = "chauffeur")
	public ResponseEntity<String> creerModifierReservation(@RequestBody ChauffeurVM chffVM) {
		// Vérification des paramètres
		String messageErreur = "";
		if (chffVM == null) {
			messageErreur = "Paramètre manquant";
			LOG.error(messageErreur);
			throw new BadRequestException(messageErreur);
		}
		if (chffVM.getId() == null || chffVM.getId() <= 0) {
			messageErreur += ",id ";
		}
		if (chffVM.getMatricule() == null) {
			messageErreur += ",matricule ";
		}
		if (chffVM.getNumeroPermis() == null) {
			messageErreur += ",numéro de permis ";
		}
		if (!messageErreur.isEmpty()) {
			messageErreur = "Paramètres incorrects " + messageErreur;
			LOG.error(messageErreur);
			throw new BadRequestException(messageErreur);
		}

		// On vérifie si le collaborateur existe
		Optional<Collaborateur> collabOpt = this.collabRepo.findById(chffVM.getId());
		if (!collabOpt.isPresent()) {
			messageErreur = "Collaborateur d'id " + chffVM.getId() + " introuvable..";
			LOG.error(messageErreur);
			throw new ElementNotFoundException(messageErreur);
		}

		Chauffeur chauffeur = new Chauffeur(collabOpt.get(), chffVM.getNumeroPermis());
		this.chffRepo.save(chauffeur);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Le chauffeur a été créé avec succès!");
	}

}
