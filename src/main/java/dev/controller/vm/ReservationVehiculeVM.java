package dev.controller.vm;

import java.time.LocalDateTime;

import dev.domain.ReservationVehicule;
import dev.domain.Vehicule;

/**
 * Structure modèlisant une réservation de véhicule servant à communiquer avec
 * l'extérieur (WEB API).
 */
public class ReservationVehiculeVM {

	private Long id;
	private LocalDateTime dateDebut;
	private LocalDateTime dateFin;
	private boolean avecChauffeur;
	private Long collaborateurId;
	private CollaborateurVM collaborateurDetails;
	private Long vehiculeId;
	private Vehicule vehiculeDetails;
	private Long chauffeurId;
	private ChauffeurVM chauffeurDetails;

	public ReservationVehiculeVM() {
	}

	public ReservationVehiculeVM(ReservationVehicule resVehicule) {
		if (resVehicule != null) {
			this.id = resVehicule.getId();
			this.dateDebut = resVehicule.getDateDebut();
			this.dateFin = resVehicule.getDateFin();
			this.avecChauffeur = resVehicule.isAvecChauffeur();
			this.collaborateurId = resVehicule.getCollaborateur().getId();
			this.collaborateurDetails = new CollaborateurVM(resVehicule.getCollaborateur());
			this.vehiculeId = resVehicule.getVehicule().getId();
			this.vehiculeDetails = resVehicule.getVehicule();
			if (resVehicule.getChauffeur() != null) {
				this.chauffeurId = resVehicule.getChauffeur().getId();
				this.chauffeurDetails = new ChauffeurVM(resVehicule.getChauffeur());
			}
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDateTime dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDateTime getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDateTime dateFin) {
		this.dateFin = dateFin;
	}

	public boolean isAvecChauffeur() {
		return avecChauffeur;
	}

	public void setAvecChauffeur(boolean avecChauffeur) {
		this.avecChauffeur = avecChauffeur;
	}

	public Long getCollaborateurId() {
		return collaborateurId;
	}

	public void setCollaborateurId(Long collaborateurId) {
		this.collaborateurId = collaborateurId;
	}

	public Long getVehiculeId() {
		return vehiculeId;
	}

	public void setVehiculeId(Long vehiculeId) {
		this.vehiculeId = vehiculeId;
	}

	public Long getChauffeurId() {
		return chauffeurId;
	}

	public void setChauffeurId(Long chauffeurId) {
		this.chauffeurId = chauffeurId;
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
	 * @return the vehiculeDetails
	 */
	public Vehicule getVehiculeDetails() {
		return vehiculeDetails;
	}

	/**
	 * @param vehiculeDetails
	 *            the vehiculeDetails to set
	 */
	public void setVehiculeDetails(Vehicule vehiculeDetails) {
		this.vehiculeDetails = vehiculeDetails;
	}

	/**
	 * @return the chauffeurDetails
	 */
	public ChauffeurVM getChauffeurDetails() {
		return chauffeurDetails;
	}

	/**
	 * @param chauffeurDetails
	 *            the chauffeurDetails to set
	 */
	public void setChauffeurDetails(ChauffeurVM chauffeurDetails) {
		this.chauffeurDetails = chauffeurDetails;
	}

}
