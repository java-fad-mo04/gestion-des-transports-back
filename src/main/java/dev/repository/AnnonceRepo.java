package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.Annonce;

public interface AnnonceRepo extends JpaRepository<Annonce, Long> {

}
