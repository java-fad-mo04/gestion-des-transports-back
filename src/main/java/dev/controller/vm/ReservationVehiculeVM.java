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
	private CollaborateurVM collaborateur;
	private Vehicule vehicule;
	private ChauffeurVM chauffeur;

	public ReservationVehiculeVM() {
	}

	public ReservationVehiculeVM(ReservationVehicule resVehicule) {
		this.id = resVehicule.getId();
		this.dateDebut = resVehicule.getDateDebut();
		this.dateFin = resVehicule.getDateFin();
		this.avecChauffeur = resVehicule.isAvecChauffeur();
		this.collaborateur = new CollaborateurVM(resVehicule.getCollaborateur());
		this.vehicule = resVehicule.getVehicule();
		if (resVehicule.getChauffeur() != null)
			this.chauffeur = new ChauffeurVM(resVehicule.getChauffeur());
	}

	public ReservationVehicule toReservationVehicule() {
		return new ReservationVehicule(this.id, this.dateDebut, this.dateFin, this.avecChauffeur,
				this.collaborateur.toCollaborateur(), this.vehicule, this.chauffeur.toChauffeur());
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

	public CollaborateurVM getCollaborateur() {
		return collaborateur;
	}

	public void setCollaborateur(CollaborateurVM collaborateur) {
		this.collaborateur = collaborateur;
	}

	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

	public ChauffeurVM getChauffeur() {
		return chauffeur;
	}

	public void setChauffeur(ChauffeurVM chauffeur) {
		this.chauffeur = chauffeur;
	}

}
