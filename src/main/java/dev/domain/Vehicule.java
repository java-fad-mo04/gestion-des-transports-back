package dev.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Représente le concept de véhicule
 * 
 * @author Diginamic
 *
 */
@Entity
public class Vehicule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/** Identifiant du véhicule */

	private String marque;
	/** Marque du véhicule */

	private String modele;
	/** Modèle du véhicule */

	@Enumerated(EnumType.STRING)
	private CategorieVehicule categorie;
	/** Catégorie du véhicule */

	private String urlPhoto;
	/** Adresse Url de la photo */

	@Enumerated(EnumType.STRING)
	private StatutVehicule statut;
	/** Statut du véhicule: EN_SERVICE, EN_REPARATION, HORS_SERVICE */

	private Integer nombrePlaces;

	/** Nombre de places disponibles dans le véhicule */

	/**
	 * Constructeurs
	 */
	public Vehicule() {
	}

	public Vehicule(Long id, String marque, String modele, CategorieVehicule categorie, String urlPhoto,
			StatutVehicule statut, Integer nombrePlaces) {
		this.id = id;
		this.marque = marque;
		this.modele = modele;
		this.categorie = categorie;
		this.urlPhoto = urlPhoto;
		this.statut = statut;
		this.nombrePlaces = nombrePlaces;
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
	 * @return the marque
	 */
	public String getMarque() {
		return marque;
	}

	/**
	 * @param marque
	 *            the marque to set
	 */
	public void setMarque(String marque) {
		this.marque = marque;
	}

	/**
	 * @return the modele
	 */
	public String getModele() {
		return modele;
	}

	/**
	 * @param modele
	 *            the modele to set
	 */
	public void setModele(String modele) {
		this.modele = modele;
	}

	/**
	 * @return the categorie
	 */
	public CategorieVehicule getCategorie() {
		return categorie;
	}

	/**
	 * @param categorie
	 *            the categorie to set
	 */
	public void setCategorie(CategorieVehicule categorie) {
		this.categorie = categorie;
	}

	/**
	 * @return the urlPhoto
	 */
	public String getUrlPhoto() {
		return urlPhoto;
	}

	/**
	 * @param urlPhoto
	 *            the urlPhoto to set
	 */
	public void setUrlPhoto(String urlPhoto) {
		this.urlPhoto = urlPhoto;
	}

	/**
	 * @return the statut
	 */
	public StatutVehicule getStatut() {
		return statut;
	}

	/**
	 * @param statut
	 *            the statut to set
	 */
	public void setStatut(StatutVehicule statut) {
		this.statut = statut;
	}

	/**
	 * @return the nombrePlaces
	 */
	public Integer getNombrePlaces() {
		return nombrePlaces;
	}

	/**
	 * @param nombrePlaces
	 *            the nombrePlaces to set
	 */
	public void setNombrePlaces(Integer nombrePlaces) {
		this.nombrePlaces = nombrePlaces;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Vehicule [id=" + id + ", marque=" + marque + ", modele=" + modele + ", categorie=" + categorie
				+ ", urlPhoto=" + urlPhoto + ", statut=" + statut + ", nombrePlaces=" + nombrePlaces + "]";
	}

}
