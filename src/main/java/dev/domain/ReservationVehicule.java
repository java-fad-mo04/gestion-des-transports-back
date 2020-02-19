package dev.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Représente le concept de réservation d'un véhicule
 * 
 * @author Diginamic
 *
 */
@Entity
public class ReservationVehicule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/** Identifiant d'une réservation de véhicule */

	private LocalDateTime dateDebut;
	/** Date de début de réservation */

	private LocalDateTime dateFin;
	/** Date de fin de réservation */

	private boolean avecChauffeur;
	/**
	 * Désigne s'il s'agit d'une réservation d'un véhicule avec chauffeur ou non
	 */

	@ManyToOne
	@JoinColumn(name = "collaborateur_id")
	private Collaborateur collaborateur;
	/** Collaborateur ayant effectué la réservation */

	@ManyToOne
	@JoinColumn(name = "vehicule_id")
	private Vehicule vehicule;
	/** Véhicule réservé */

	@ManyToOne
	@JoinColumn(name = "chauffeur_id")
	private Chauffeur chauffeur;

	/** Désigne le chauffeur ayant accepté la réservation */

	/**
	 * Constructeur
	 */
	public ReservationVehicule() {
	}

	public ReservationVehicule(Long id, LocalDateTime dateDebut, LocalDateTime dateFin, boolean avecChauffeur,
			Collaborateur collaborateur, Vehicule vehicule, Chauffeur chauffeur) {
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.avecChauffeur = avecChauffeur;
		this.collaborateur = collaborateur;
		this.vehicule = vehicule;
		this.chauffeur = chauffeur;
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
	 * @return the dateDebut
	 */
	public LocalDateTime getDateDebut() {
		return dateDebut;
	}

	/**
	 * @param dateDebut
	 *            the dateDebut to set
	 */
	public void setDateDebut(LocalDateTime dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * @return the dateFin
	 */
	public LocalDateTime getDateFin() {
		return dateFin;
	}

	/**
	 * @param dateFin
	 *            the dateFin to set
	 */
	public void setDateFin(LocalDateTime dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * @return the avecChauffeur
	 */
	public boolean isAvecChauffeur() {
		return avecChauffeur;
	}

	/**
	 * @param avecChauffeur
	 *            the avecChauffeur to set
	 */
	public void setAvecChauffeur(boolean avecChauffeur) {
		this.avecChauffeur = avecChauffeur;
	}

	/**
	 * @return the collaborateur
	 */
	public Collaborateur getCollaborateur() {
		return collaborateur;
	}

	/**
	 * @param collaborateur
	 *            the collaborateur to set
	 */
	public void setCollaborateur(Collaborateur collaborateur) {
		this.collaborateur = collaborateur;
	}

	/**
	 * @return the vehicule
	 */
	public Vehicule getVehicule() {
		return vehicule;
	}

	/**
	 * @param vehicule
	 *            the vehicule to set
	 */
	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

	/**
	 * @return the chauffeur
	 */
	public Chauffeur getChauffeur() {
		return chauffeur;
	}

	/**
	 * @param chauffeur
	 *            the chauffeur to set
	 */
	public void setChauffeur(Chauffeur chauffeur) {
		this.chauffeur = chauffeur;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReservationVehicule [id=" + id + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin
				+ ", avecChauffeur=" + avecChauffeur + ", collaborateur=" + collaborateur + ", vehicule=" + vehicule
				+ ", chauffeur=" + chauffeur + "]";
	}
}
