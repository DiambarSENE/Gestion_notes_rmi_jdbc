package metier;

import java.io.Serializable;
public class Etudiant implements Serializable{
	private static final long serialVersionUID = 1L;
	private String nom; 
	private String prenom; 
	private int matricule; 
	public Etudiant() {
		super();
		
	}
	public Etudiant(String nom, String prenom, int matricule) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.matricule = matricule;
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

	public int getMatricule() {
		return matricule;
	}

	public void setMatricule(int matricule) {
		this.matricule = matricule;
	}

}
