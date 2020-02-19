package dev.controller.vm;

import dev.domain.ReservationAnnonce;
import dev.domain.Statut;

/**
 * Structure modèlisant une réservation de covoiturage servant à communiquer
 * avec l'extérieur (WEB API).
 */
public class ReservationAnnonceVM {

	private Long id;
	private AnnonceVM annonce;
	private CollaborateurVM collaborateur;
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
		this.annonce = new AnnonceVM(resAnnonce.getAnnonce());
		this.collaborateur = new CollaborateurVM(resAnnonce.getCollaborateur());
		this.statut = resAnnonce.getStatut();
	}

	public ReservationAnnonce toReservationAnnonce() {
		return new ReservationAnnonce(this.id, this.annonce.toAnnonce(), this.collaborateur.toCollaborateur(),
				this.statut);
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
	public AnnonceVM getAnnonce() {
		return annonce;
	}

	/**
	 * @param annonce
	 *            the annonce to set
	 */
	public void setAnnonce(AnnonceVM annonce) {
		this.annonce = annonce;
	}

	/**
	 * @return the collaborateur
	 */
	public CollaborateurVM getCollaborateur() {
		return collaborateur;
	}

	/**
	 * @param collaborateur
	 *            the collaborateur to set
	 */
	public void setCollaborateur(CollaborateurVM collaborateur) {
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

}
