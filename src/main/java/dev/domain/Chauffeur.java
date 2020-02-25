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
	private String numeroPermis;

	/** Numéro de permis de conduire du chauffeur */

	/**
	 * Constructeur
	 */
	public Chauffeur() {
	}

	public Chauffeur( String numeroPermis) {
		this.numeroPermis = numeroPermis;
	}

	public Chauffeur(Long id, String nom, String prenom, String email, String motDePasse, String numeroTel,
			String urlPhoto,
			List<RoleCollaborateur> roles, String matricule, String numeroPermis) {
		super(id, nom, prenom, email, motDePasse, numeroTel, matricule, urlPhoto, roles);
		this.numeroPermis = numeroPermis;
	}

	public Chauffeur(Collaborateur collaborateur, String numeroPermis) {
		this(	collaborateur.getId(), collaborateur.getNom(), collaborateur.getPrenom(), collaborateur.getEmail(),
				collaborateur.getMotDePasse(), collaborateur.getNumeroTel(), collaborateur.getUrlPhoto(), collaborateur.getRoles(), 
				collaborateur.getMatricule(), numeroPermis);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Chauffeur [id=" + this.getId() + ", nom=" + this.getNom() + ", prenom=" + this.getPrenom() + ", email="
				+ this.getEmail() + ", motDePasse=" + this.getMotDePasse() + ", numeroTel=" + this.getNumeroTel()
				+ ", matricule=" + this.getMatricule() + ", numeroPermis=" + numeroPermis + "]";
	}
}
