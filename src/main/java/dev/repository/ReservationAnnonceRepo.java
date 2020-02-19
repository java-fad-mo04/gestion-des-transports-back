package dev.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.Annonce;
import dev.domain.Collaborateur;
import dev.domain.ReservationAnnonce;
import dev.domain.Statut;

public interface ReservationAnnonceRepo extends JpaRepository<ReservationAnnonce, Long> {

	/** Récupérer les réservations sur une annonce */
	public List<ReservationAnnonce> findAllByAnnonce(Annonce annonce);

	/** Récupérer les réservations sur une annonce suivant le statut */
	public List<ReservationAnnonce> findAllByAnnonceAndStatut(Annonce annonce, Statut statut);

	/** Récupérer les réservations d'un collaborateur */
	public List<ReservationAnnonce> findAllByCollaborateur(Collaborateur collaborateur);

	/** Récupérer la réservation pour une annonce d'un collaborateur */
	public Optional<ReservationAnnonce> findByAnnonceAndCollaborateur(Annonce annonce, Collaborateur collaborateur);

}
