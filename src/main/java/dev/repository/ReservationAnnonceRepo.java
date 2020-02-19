package dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.Annonce;
import dev.domain.Collaborateur;
import dev.domain.ReservationAnnonce;

public interface ReservationAnnonceRepo extends JpaRepository<ReservationAnnonce, Integer> {

	/** Récupérer les réservations sur une annonce */
	public List<ReservationAnnonce> findAllByAnnonce(Annonce annonce);

	/** Récupérer les réservations d'un collaborateur */
	public List<ReservationAnnonce> findAllByCollaborateur(Collaborateur collaborateur);

}
