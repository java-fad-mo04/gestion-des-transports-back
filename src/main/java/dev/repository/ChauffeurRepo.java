package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.Chauffeur;

public interface ChauffeurRepo extends JpaRepository<Chauffeur, Long> {

}
