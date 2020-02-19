package dev.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Représente le concept d'une réservation en covoiturage
 * 
 * @author Diginamic
 *
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "annonce_id", "collaborateur_id" }))
public class ReservationAnnonce {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/** Identifiant d'une réservation de covoiturage */

	@ManyToOne
	@JoinColumn(name = "annonce_id")
	private Annonce annonce;
	/** Annonce de la réservation */

	@ManyToOne
	@JoinColumn(name = "collaborateur_id")
	private Collaborateur collaborateur;
	/** Passager du covoiturage */

	@Enumerated(EnumType.STRING)
	private Statut statut;

	/** Statut de la réservation: ANNULE, ACTIF */

	/**
	 * Constructeurs
	 */
	public ReservationAnnonce() {
	}

	public ReservationAnnonce(Annonce annonce, Collaborateur collaborateur, Statut statut) {
		this.annonce = annonce;
		this.collaborateur = collaborateur;
		this.statut = statut;
	}

	public ReservationAnnonce(Long id, Annonce annonce, Collaborateur collaborateur, Statut statut) {
		this(annonce, collaborateur, statut);
		this.id = id;
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
	 * @return the annonce
	 */
	public Annonce getAnnonce() {
		return annonce;
	}

	/**
	 * @param annonce
	 *            the annonce to set
	 */
	public void setAnnonce(Annonce annonce) {
		this.annonce = annonce;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Passager [annonce=" + annonce + ", collaborateur=" + collaborateur + ", statut=" + statut + "]";
	}

}
