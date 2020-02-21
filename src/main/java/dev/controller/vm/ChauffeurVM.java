package dev.controller.vm;

import java.util.List;
import java.util.stream.Collectors;

import dev.domain.Chauffeur;
import dev.domain.RoleCollaborateur;

/**
 * Structure modèlisant un chauffeur servant à communiquer avec l'extérieur (WEB
 * API).
 */
public class ChauffeurVM extends CollaborateurVM {

	private String matricule;
	private String numeroPermis;

	public ChauffeurVM() {

	}

	public ChauffeurVM(Chauffeur chauffeur) {
		super(chauffeur);
		this.matricule = chauffeur.getMatricule();
		this.numeroPermis = chauffeur.getNumeroPermis();
	}

	public Chauffeur toChauffeur() {
		Chauffeur ch = new Chauffeur();
		ch.setId(this.getId());
		ch.setEmail(this.getEmail());
		ch.setNom(this.getNom());
		ch.setPrenom(this.getPrenom());
		ch.setNumeroTel(this.getNumeroTel());
		ch.setMatricule(this.matricule);
		ch.setNumeroPermis(this.numeroPermis);
		List<RoleCollaborateur> rolesCollegue = this.getRoles().stream().map(role -> new RoleCollaborateur(ch, role))
				.collect(Collectors.toList());
		ch.setRoles(rolesCollegue);
		return ch;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getNumeroPermis() {
		return numeroPermis;
	}

	public void setNumeroPermis(String numeroPermis) {
		this.numeroPermis = numeroPermis;
	}

}
