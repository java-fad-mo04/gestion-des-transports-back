package dev.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Représente le concept d'annonce
 * 
 * @author Diginamic
 *
 */
@Entity
public class Annonce {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/** Identifiant de l'annonce */

	private String adresseDepart;
	/** Adresse de départ */

	private String adresseArrivee;
	/** Adresse d'arrivée */

	private String immatriculation;
	/** Immatriculation du véhicule utilisé */

	private String marque;
	/** Marque du véhicule utilisé */

	private String modele;
	/** Modèle du véhicule utilisé */

	private Integer nombrePlacesDispo;
	/** Nombre de places disponibles dans le véhicule utilisé */

	private LocalDateTime dateDepart;
	/** Date et heure de départ */

	@Enumerated(EnumType.STRING)
	private Statut statut;
	/** Statut de l'annonce ACTIF, "ANNULE */

	@ManyToOne
	@JoinColumn(name = "collaborateur_id")
	private Collaborateur collaborateur;

	@OneToMany(mappedBy = "annonce", cascade = CascadeType.PERSIST)
	private List<ReservationAnnonce> reservations;

	/** Liste des réservations */

	/** Collaborateur ayant posté l'annonce */

	/**
	 * Constructeurs
	 */
	public Annonce() {
		this.reservations = new ArrayList<ReservationAnnonce>();
	}

	public Annonce(Long id, String adresseDepart, String adresseArrivee, String immatriculation, String marque,
			String modele, Integer nombrePlacesDispo, LocalDateTime dateDepart, Statut statut,
			Collaborateur collaborateur) {
		this();
		this.id = id;
		this.adresseDepart = adresseDepart;
		this.adresseArrivee = adresseArrivee;
		this.immatriculation = immatriculation;
		this.marque = marque;
		this.modele = modele;
		this.nombrePlacesDispo = nombrePlacesDispo;
		this.dateDepart = dateDepart;
		this.statut = statut;
		this.collaborateur = collaborateur;
	}

	public Annonce(Long id, String adresseDepart, String adresseArrivee, String immatriculation, String marque,
			String modele, Integer nombrePlacesDispo, LocalDateTime dateDepart, Statut statut,
			Collaborateur collaborateur, List<ReservationAnnonce> reservations) {
		this(id, adresseDepart, adresseArrivee, immatriculation, marque, modele, nombrePlacesDispo, dateDepart, statut,
				collaborateur);
		this.reservations = reservations;
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
	 * @return the adresseDepart
	 */
	public String getAdresseDepart() {
		return adresseDepart;
	}

	/**
	 * @param adresseDepart
	 *            the adresseDepart to set
	 */
	public void setAdresseDepart(String adresseDepart) {
		this.adresseDepart = adresseDepart;
	}

	/**
	 * @return the adresseArrivée
	 */
	public String getAdresseArrivee() {
		return adresseArrivee;
	}

	/**
	 * @param adresseArrivée
	 *            the adresseArrivée to set
	 */
	public void setAdresseArrivee(String adresseArrivée) {
		this.adresseArrivee = adresseArrivée;
	}

	/**
	 * @return the immatriculation
	 */
	public String getImmatriculation() {
		return immatriculation;
	}

	/**
	 * @param immatriculation
	 *            the immatriculation to set
	 */
	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
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
	 * @return the nombrePlacesDispo
	 */
	public Integer getNombrePlacesDispo() {
		return nombrePlacesDispo;
	}

	/**
	 * @param nombrePlacesDispo
	 *            the nombrePlacesDispo to set
	 */
	public void setNombrePlacesDispo(Integer nombrePlacesDispo) {
		this.nombrePlacesDispo = nombrePlacesDispo;
	}

	/**
	 * @return the dateDepart
	 */
	public LocalDateTime getDateDepart() {
		return dateDepart;
	}

	/**
	 * @param dateDepart
	 *            the dateDepart to set
	 */
	public void setDateDepart(LocalDateTime dateDepart) {
		this.dateDepart = dateDepart;
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
	 * @return the reservations
	 */
	public List<ReservationAnnonce> getReservations() {
		return reservations;
	}

	/**
	 * @param reservations
	 *            the reservations to set
	 */
	public void setReservations(List<ReservationAnnonce> reservations) {
		this.reservations = reservations;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Annonce [id=" + id + ", adresseDepart=" + adresseDepart + ", adresseArrivée=" + adresseArrivee
				+ ", immatriculation=" + immatriculation + ", marque=" + marque + ", modele=" + modele
				+ ", nombrePlacesDispo=" + nombrePlacesDispo + ", dateDepart=" + dateDepart + ", statut=" + statut
				+ ", collaborateur=" + collaborateur + "]";
	}

}