package dev.controller.vm;

import java.time.LocalDateTime;

import dev.domain.ReservationVehicule;

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
	private Long vehiculeId;
	private Long chauffeurId;

	public ReservationVehiculeVM() {
	}

	public ReservationVehiculeVM(ReservationVehicule resVehicule) {
		this.id = resVehicule.getId();
		this.dateDebut = resVehicule.getDateDebut();
		this.dateFin = resVehicule.getDateFin();
		this.avecChauffeur = resVehicule.isAvecChauffeur();
		this.collaborateurId = resVehicule.getCollaborateur().getId();
		this.vehiculeId = resVehicule.getVehicule().getId();
		if (resVehicule.getChauffeur() != null)
			this.chauffeurId = resVehicule.getChauffeur().getId();
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

}
