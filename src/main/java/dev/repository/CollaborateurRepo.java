package dev.repository;

import dev.domain.Collaborateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CollaborateurRepo extends JpaRepository<Collaborateur, Long> {

    Optional<Collaborateur> findByEmail(String email);
}
