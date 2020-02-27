package dev.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.Chauffeur;

public interface ChauffeurRepo extends JpaRepository<Chauffeur, Long> {

	List<Chauffeur> findByMatriculeStartingWith( String matricule);



	List<Chauffeur> findByMatriculeStartingWithAndNomStartingWithAndPrenomStartingWith(String matricule, String nom,
			String prenom);



	Optional<Chauffeur> findByMatricule(String matricule);


}
