package dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.Chauffeur;
import dev.domain.Collaborateur;
import dev.domain.ReservationVehicule;
import dev.domain.Vehicule;

public interface ReservationVehiculeRepo extends JpaRepository<ReservationVehicule, Long> {

	/** Récupérer les réservations pour un véhicule */
	public List<ReservationVehicule> findAllByVehicule(Vehicule cehicule);

	/** Récupérer les réservations de véhicule d'un collaborateur */
	public List<ReservationVehicule> findAllByCollaborateur(Collaborateur collaborateur);

	/** Récupérer les réservations de véhicule d'un chauffeur */
	public List<ReservationVehicule> findAllByChauffeur(Chauffeur chauffeur);

}
