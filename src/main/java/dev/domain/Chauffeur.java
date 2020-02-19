package dev.domain;

import java.util.List;

import javax.persistence.Entity;

/**
 * Représente le concept de chauffeur
 * 
 * @author Diginamic
 *
 */
@Entity
public class Chauffeur extends Collaborateur {

	private String matricule;
	/** Matricule du chauffeur */

	private String numeroPermis;

	/** Numéro de permis de conduire du chauffeur */

	/**
	 * Constructeur
	 */
	public Chauffeur() {
	}

	public Chauffeur(String matricule, String numeroPermis) {
		this.matricule = matricule;
		this.numeroPermis = numeroPermis;
	}

	public Chauffeur(Long id, String nom, String prenom, String email, String motDePasse, String numeroTel,
			List<RoleCollaborateur> roles, String matricule, String numeroPermis) {
		super(id, nom, prenom, email, motDePasse, numeroTel, roles);
		this.matricule = matricule;
		this.numeroPermis = numeroPermis;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Chauffeur [matricule=" + matricule + ", numeroPermis=" + numeroPermis + "]";
	}
}
