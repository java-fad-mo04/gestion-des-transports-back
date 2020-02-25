package dev.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Représente le concept de chauffeur
 * 
 * @author Diginamic
 *
 */
@Entity
public class Chauffeur extends Collaborateur {

	@NotNull
	private String matricule;
	/** Matricule du chauffeur */

	@NotNull
	private String numeroPermis;
	/** Numéro de permis de conduire du chauffeur */

	private String photoUrl;

	/** Url de la photo du chauffeur */

	/**
	 * Constructeur
	 */
	public Chauffeur() {
	}

	public Chauffeur(String matricule, String numeroPermis, String photoUrl) {
		this.matricule = matricule;
		this.numeroPermis = numeroPermis;
		this.photoUrl = photoUrl;
	}

	public Chauffeur(Long id, String nom, String prenom, String email, String motDePasse, String numeroTel,
			List<RoleCollaborateur> roles, String matricule, String numeroPermis, String photoUrl) {
		super(id, nom, prenom, email, motDePasse, numeroTel, roles);
		this.matricule = matricule;
		this.numeroPermis = numeroPermis;
		this.photoUrl = photoUrl;
	}

	public Chauffeur(Collaborateur collaborateur, String matricule, String numeroPermis, String photoUrl) {
		this(collaborateur.getId(), collaborateur.getNom(), collaborateur.getPrenom(), collaborateur.getEmail(),
				collaborateur.getMotDePasse(), collaborateur.getNumeroTel(), collaborateur.getRoles(), matricule,
				numeroPermis, photoUrl);
	}

	/**
	 * @return the matricule
	 */
	public String getMatricule() {
		return matricule;
	}

	/**
	 * @param matricule
	 *            the matricule to set
	 */
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	/**
	 * @return the numeroPermis
	 */
	public String getNumeroPermis() {
		return numeroPermis;
	}

	/**
	 * @param numeroPermis
	 *            the numeroPermis to set
	 */
	public void setNumeroPermis(String numeroPermis) {
		this.numeroPermis = numeroPermis;
	}

	/**
	 * @return the photoUrl
	 */
	public String getPhotoUrl() {
		return photoUrl;
	}

	/**
	 * @param photoUrl
	 *            the photoUrl to set
	 */
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Chauffeur [id=" + this.getId() + ", nom=" + this.getNom() + ", prenom=" + this.getPrenom() + ", email="
				+ this.getEmail() + ", motDePasse=" + this.getMotDePasse() + ", numeroTel=" + this.getNumeroTel()
				+ ", matricule=" + matricule + ", numeroPermis=" + numeroPermis + "]";
	}
}
