package notes;
import java.sql.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import metier.Epreuve_avec_coeff;



public class EtudiantImplService extends UnicastRemoteObject implements IEtudiant {
	
	Connection con;
    Statement st;
	ResultSet rs;
	
	private static final long serialVersionUID = 1L;
	List<Epreuve_avec_coeff> listeEpreuve = new ArrayList<Epreuve_avec_coeff>();
	protected EtudiantImplService() throws RemoteException {
		super();
	}
	public String ajouter_une_epreuve(String matieres, double notes,int coef,int matricule,int num_promo) throws RemoteException, ClassNotFoundException, SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		   con = DriverManager.getConnection("jdbc:mysql://localhost/gestions_notes","root","");
		   st = con.createStatement();
		   String sql = "insert into epreuve(matieres,notes,coef,matricule,promotion) values('"+matieres+"','"+notes+"','"+coef+"','"+matricule+"','"+num_promo+"')";
		   st.executeUpdate(sql);
		   return "ajouter avec success";
		} catch (Exception e) {
			return (e.toString());
		}
	}

	@Override
	public ArrayList<String> afficher_liste_des_epreuves() throws RemoteException {
		ArrayList<String> epreuve = new ArrayList<String>();
		try {	
		   Class.forName("com.mysql.cj.jdbc.Driver");
		   con = DriverManager.getConnection("jdbc:mysql://localhost/gestions_notes","root","");
		   st = con.createStatement();
		   String sql = "select * from epreuve";
		   rs = st.executeQuery(sql);
		   while(rs.next()) {
			   epreuve.add("matieres : "+rs.getString("matieres"));
			   epreuve.add("notes : "+rs.getString("notes"));
			   epreuve.add("coef : "+rs.getString("coef"));
			   epreuve.add("matricule : "+rs.getString("matricule"));
		   }
		   }catch (Exception e) {
				e.printStackTrace();
			}
		 return epreuve;
	}
	@Override
	public double calculer_la_moyenne(int matricule) throws RemoteException {
		double taille = 0;
		double somme = 0;
		try {	
		   Class.forName("com.mysql.cj.jdbc.Driver");
		   con = DriverManager.getConnection("jdbc:mysql://localhost/gestions_notes","root","");
		   st = con.createStatement();
		   String sql = "select * from epreuve where matricule='"+matricule+"'";
		   rs = st.executeQuery(sql);
		   while(rs.next()) {
			   double notes= Double.parseDouble(rs.getString("notes")) ;
			   double coef = Double.parseDouble(rs.getString("coef"));
			   somme += notes*coef;
			   taille += coef;
		   }
		   }catch (Exception e) {
				e.printStackTrace();
			}
		return somme/taille;
		 
	}
	@Override
	public String modifier_matiere(String matieres, double notes, int coef, int matricule, int num_promo)
			throws RemoteException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		   con = DriverManager.getConnection("jdbc:mysql://localhost/gestions_notes","root","");
		   st = con.createStatement();
    	   String sql = "update epreuve set matieres='"+matieres+"', notes='"+notes+"', coef='"+coef+"',promotion='"+num_promo+"' where matricule='"+matricule+"'";
		   st.executeUpdate(sql);
		   return "modifier avec success";
		} catch (Exception e) {
			return (e.toString());
		}
	}
	@Override
	public String supprimer_matiere(int matricule) throws RemoteException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		   con = DriverManager.getConnection("jdbc:mysql://localhost/gestions_notes","root","");
		   st = con.createStatement();
    	   String sql = "delete from epreuve where matricule='"+matricule+"'";
		   st.executeUpdate(sql);
		   return "supprimer avec success";
		} catch (Exception e) {
			return (e.toString());
		}
	}
}	
