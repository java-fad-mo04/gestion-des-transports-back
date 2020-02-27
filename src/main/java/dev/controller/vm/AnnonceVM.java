package dev.controller.vm;

import java.time.LocalDateTime;

import dev.domain.Annonce;
import dev.domain.Statut;

/**
 * Structure modèlisant une annonce servant à communiquer avec l'extérieur (WEB
 * API).
 */
public class AnnonceVM {

	private Long id;
	private String adresseDepart;
	private String adresseArrivee;
	private String immatriculation;
	private String marque;
	private String modele;
	private Integer nombrePlacesDispo;
	private LocalDateTime dateDepart;
	private Statut statut;
	private Long collaborateurId;
	private CollaborateurVM collaborateurDetails;
	private Long nombreReservationsActives;

	public AnnonceVM() {
	}

	public AnnonceVM(Annonce annonce) {
		if (annonce != null) {
			this.id = annonce.getId();
			this.adresseDepart = annonce.getAdresseDepart();
			this.adresseArrivee = annonce.getAdresseArrivee();
			this.immatriculation = annonce.getImmatriculation();
			this.marque = annonce.getMarque();
			this.modele = annonce.getModele();
			this.nombrePlacesDispo = annonce.getNombrePlacesDispo();
			this.dateDepart = annonce.getDateDepart();
			this.statut = annonce.getStatut();
			this.collaborateurId = annonce.getCollaborateur().getId();
			this.collaborateurDetails = new CollaborateurVM(annonce.getCollaborateur());
			this.nombreReservationsActives = annonce.getReservations().stream()
					.filter(resa -> resa.getStatut().equals(Statut.ACTIF)).count();
		}
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the adresseDepart
	 */
	public String getAdresseDepart() {
		return adresseDepart;
	}

	/**
	 * @param adresseDepart
	 *            the adresseDepart to set
	 */
	public void setAdresseDepart(String adresseDepart) {
		this.adresseDepart = adresseDepart;
	}

	/**
	 * @return the adresseArrivée
	 */
	public String getAdresseArrivee() {
		return adresseArrivee;
	}

	/**
	 * @param adresseArrivée
	 *            the adresseArrivée to set
	 */
	public void setAdresseArrivee(String adresseArrivee) {
		this.adresseArrivee = adresseArrivee;
	}

	/**
	 * @return the immatriculation
	 */
	public String getImmatriculation() {
		return immatriculation;
	}

	/**
	 * @param immatriculation
	 *            the immatriculation to set
	 */
	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	/**
	 * @return the marque
	 */
	public String getMarque() {
		return marque;
	}

	/**
	 * @param marque
	 *            the marque to set
	 */
	public void setMarque(String marque) {
		this.marque = marque;
	}

	/**
	 * @return the modele
	 */
	public String getModele() {
		return modele;
	}

	/**
	 * @param modele
	 *            the modele to set
	 */
	public void setModele(String modele) {
		this.modele = modele;
	}

	/**
	 * @return the nombrePlacesDispo
	 */
	public Integer getNombrePlacesDispo() {
		return nombrePlacesDispo;
	}

	/**
	 * @param nombrePlacesDispo
	 *            the nombrePlacesDispo to set
	 */
	public void setNombrePlacesDispo(Integer nombrePlacesDispo) {
		this.nombrePlacesDispo = nombrePlacesDispo;
	}

	/**
	 * @return the dateDepart
	 */
	public LocalDateTime getDateDepart() {
		return dateDepart;
	}

	/**
	 * @param dateDepart
	 *            the dateDepart to set
	 */
	public void setDateDepart(LocalDateTime dateDepart) {
		this.dateDepart = dateDepart;
	}

	/**
	 * @return the statut
	 */
	public Statut getStatut() {
		return statut;
	}

	/**
	 * @param statut
	 *            the statut to set
	 */
	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	/**
	 * @return the collaborateur
	 */
	public Long getCollaborateurId() {
		return collaborateurId;
	}

	/**
	 * @param collaborateur
	 *            the collaborateur to set
	 */
	public void setCollaborateurId(Long collaborateurId) {
		this.collaborateurId = collaborateurId;
	}

	/**
	 * @return the collaborateurDetails
	 */
	public CollaborateurVM getCollaborateurDetails() {
		return collaborateurDetails;
	}

	/**
	 * @param collaborateurDetails
	 *            the collaborateurDetails to set
	 */
	public void setCollaborateurDetails(CollaborateurVM collaborateurDetails) {
		this.collaborateurDetails = collaborateurDetails;
	}

	/**
	 * @return the nombreReservationsActives
	 */
	public Long getNombreReservationsActives() {
		return nombreReservationsActives;
	}

	/**
	 * @param nombreReservationsActives
	 *            the nombreReservationsActives to set
	 */
	public void setNombreReservationsActives(Long nombreReservationsActives) {
		this.nombreReservationsActives = nombreReservationsActives;
	}

}
