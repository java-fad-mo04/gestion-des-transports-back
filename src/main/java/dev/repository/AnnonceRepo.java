package dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.Annonce;
import dev.domain.Collaborateur;

public interface AnnonceRepo extends JpaRepository<Annonce, Long> {

	/** Récupérer les annonces d'un collaborateur dont l'id est donné */
	public List<Annonce> findAllByCollaborateur(Collaborateur collaborateur);
}
