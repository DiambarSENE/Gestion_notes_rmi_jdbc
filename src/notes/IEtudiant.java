package notes;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Diambar SENE
 *
 */

public interface IEtudiant extends Remote{
	
	public String ajouter_une_epreuve(String matieres,
			double notes,int coef,int matricule,int num_promo) throws RemoteException, ClassNotFoundException, SQLException;
	public ArrayList<String> afficher_liste_des_epreuves() throws RemoteException;
	public double calculer_la_moyenne(int matricule) throws RemoteException;
	public String modifier_matiere(String matieres,
			double notes,int coef,int matricule,int num_promo) throws RemoteException;
	public String supprimer_matiere(int matricule) throws RemoteException;
}
