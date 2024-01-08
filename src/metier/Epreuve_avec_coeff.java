package metier;

import java.io.Serializable;

public class Epreuve_avec_coeff implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public String matieres;
	public double notes;
	public int coef;
	public int matricule;
	
	
	public Epreuve_avec_coeff() {
		super();
	}

	public Epreuve_avec_coeff(String matieres, double notes, int coef, int matricule) {
		super();
		this.matieres = matieres;
		this.notes = notes;
		this.coef = coef;
		this.matricule = matricule;
	}

	
	public String getMatieres() {
		return matieres;
	}

	public void setMatieres(String matiere) {
		this.matieres = matiere;
	}

	public double getNotes() {
		return notes;
	}

	public void setNotes(double notes) {
		this.notes = notes;
	}

	public int getCoef() {
		return coef;
	}

	public void setCoef(int coef) {
		this.coef = coef;
	}

	public int getMatricule() {
		return matricule;
	}

	public void setMatricule(int matricule) {
		this.matricule = matricule;
	}

	
}
