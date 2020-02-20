package dev.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.domain.Chauffeur;
import dev.domain.Collaborateur;
import dev.domain.ReservationVehicule;
import dev.domain.Vehicule;

public interface ReservationVehiculeRepo extends JpaRepository<ReservationVehicule, Long> {

	/** Récupérer les réservations pour un véhicule */
	public List<ReservationVehicule> findAllByVehicule(Vehicule vehicule);

	/**
	 * Récupérer les réservations d'un véhicule qui chevauchent une période
	 * donnée
	 */
	@Query(value = "from ReservationVehicule r where r.vehicule = :vehicule AND NOT(r.dateDebut >= :dateFin OR r.dateFin <= :dateDebut)")
	public List<ReservationVehicule> getAllByVehiculeAndBetweenDates(@Param("vehicule") Vehicule vehicule,
			@Param("dateDebut") LocalDateTime dateDebut, @Param("dateFin") LocalDateTime dateFin);

	/** Récupérer les réservations de véhicule d'un collaborateur */
	public List<ReservationVehicule> findAllByCollaborateur(Collaborateur collaborateur);

	/**
	 * Récupérer les réservations d'un collaborateur qui chevauchent une période
	 * donnée
	 */
	@Query(value = "from ReservationVehicule r where r.collaborateur = :collaborateur AND NOT(r.dateDebut >= :dateFin OR r.dateFin <= :dateDebut)")
	public List<ReservationVehicule> getAllByCollaborateurAndBetweenDates(
			@Param("collaborateur") Collaborateur collaborateur, @Param("dateDebut") LocalDateTime dateDebut,
			@Param("dateFin") LocalDateTime dateFin);

	/** Récupérer les réservations de véhicule d'un chauffeur */
	public List<ReservationVehicule> findAllByChauffeur(Chauffeur chauffeur);

	/**
	 * Récupérer les réservations d'un chauffeur qui chevauchent une période
	 * donnée
	 */
	@Query(value = "from ReservationVehicule r where r.chauffeur = :chauffeur AND NOT(r.dateDebut >= :dateFin OR r.dateFin <= :dateDebut)")
	public List<ReservationVehicule> getAllByChauffeurAndBetweenDates(@Param("chauffeur") Chauffeur chauffeur,
			@Param("dateDebut") LocalDateTime dateDebut, @Param("dateFin") LocalDateTime dateFin);

}
