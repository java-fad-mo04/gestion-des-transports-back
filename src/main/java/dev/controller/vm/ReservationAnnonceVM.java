package dev.controller.vm;

import dev.domain.ReservationAnnonce;
import dev.domain.Statut;

/**
 * Structure modèlisant une réservation de covoiturage servant à communiquer
 * avec l'extérieur (WEB API).
 */
public class ReservationAnnonceVM {

	private Long id;
	private Long annonceId;
	private Long collaborateurId;
	private Statut statut;

	/**
	 * Constructeur
	 * 
	 * @param resAnnonce
	 */
	public ReservationAnnonceVM() {

	}

	public ReservationAnnonceVM(ReservationAnnonce resAnnonce) {
		this.id = resAnnonce.getId();
		this.annonceId = resAnnonce.getAnnonce().getId();
		this.collaborateurId = resAnnonce.getCollaborateur().getId();
		this.statut = resAnnonce.getStatut();
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
	 * @return the collaborateurId
	 */
	public Long getCollaborateurId() {
		return collaborateurId;
	}

	/**
	 * @return the annonceId
	 */
	public Long getAnnonceId() {
		return annonceId;
	}

	/**
	 * @param annonceId
	 *            the annonceId to set
	 */
	public void setAnnonceId(Long annonceId) {
		this.annonceId = annonceId;
	}

	/**
	 * @param collaborateurId
	 *            the collaborateurId to set
	 */
	public void setCollaborateurId(Long collaborateurId) {
		this.collaborateurId = collaborateurId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReservationAnnonceVM [id=" + id + ", annonceId=" + annonceId + ", collaborateurId=" + collaborateurId
				+ ", statut=" + statut + "]";
	}

}
