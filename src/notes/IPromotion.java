package notes;

import java.rmi.Remote;


import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.Epreuve_avec_coeff;
import metier.Etudiant;


public interface IPromotion extends Remote{
	public String ajouter_un_etudiant(String nom,String prenom,
			int matricule) throws RemoteException, ClassNotFoundException, SQLException;
	//public ArrayList<String> liste_etudiant() throws RemoteException;
	public ArrayList<String> rechercher_un_etudiant(int matricule) throws RemoteException;
	public double calculer_moyenne_de_la_promotion(int numero_promo) throws RemoteException;
	public String modifier(String nom, String prenom,int matricule) throws RemoteException;
	public String supprimer(int matricule) throws RemoteException;
}
