package dev.controller.vm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dev.domain.Collaborateur;
import dev.domain.Role;
import dev.domain.RoleCollaborateur;

/**
 * Structure modèlisant un collaborateur servant à communiquer avec l'extérieur
 * (WEB API).
 */
public class CollaborateurVM {
	private Long id;
	private String email;
	private String nom;
	private String prenom;
	private String numeroTel;
	private List<Role> roles = new ArrayList<>();

	public CollaborateurVM() {
	}

	public CollaborateurVM(Collaborateur col) {
		this.id = col.getId();
		this.email = col.getEmail();
		this.nom = col.getNom();
		this.prenom = col.getPrenom();
		this.numeroTel = col.getNumeroTel();
		this.roles = col.getRoles().stream().map(roleCollaborateur -> roleCollaborateur.getRole())
				.collect(Collectors.toList());
	}

	public Collaborateur toCollaborateur() {
		Collaborateur col = new Collaborateur();
		col.setId(this.id);
		col.setEmail(this.email);
		col.setNom(this.nom);
		col.setPrenom(this.prenom);
		col.setNumeroTel(this.numeroTel);
		List<RoleCollaborateur> rolesCollaborateur = this.roles.stream().map(role -> new RoleCollaborateur(col, role))
				.collect(Collectors.toList());
		col.setRoles(rolesCollaborateur);
		return col;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNumeroTel() {
		return numeroTel;
	}

	public void setNumeroTel(String numeroTel) {
		this.numeroTel = numeroTel;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
