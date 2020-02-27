package dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.Collaborateur;

public interface CollaborateurRepo extends JpaRepository<Collaborateur, Long> {

    Optional<Collaborateur> findByEmail(String email);

	Optional<Collaborateur> findByMatricule(String matricule);


}
