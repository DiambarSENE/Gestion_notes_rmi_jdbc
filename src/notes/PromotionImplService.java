package notes;
import java.rmi.RemoteException;

import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PromotionImplService extends UnicastRemoteObject implements IPromotion{
	
	Connection con;
    Statement st;
	ResultSet rs;
	
	private static final long serialVersionUID = 1L;
	protected PromotionImplService() throws RemoteException {
		super();
	}
	public String ajouter_un_etudiant(String nom, String prenom, int matricule) throws RemoteException, ClassNotFoundException, SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		   con = DriverManager.getConnection("jdbc:mysql://localhost/gestions_notes","root","");
		   st = con.createStatement();
		   String sql = "insert into promotion(nom,prenom,matricule) values('"+nom+"','"+prenom+"','"+matricule+"')";
		   st.executeUpdate(sql);
		   return "ajouter avec success";
		} catch (Exception e) {
			return (e.toString());
		}
	}
	@Override
	public ArrayList<String> rechercher_un_etudiant(int matricule) throws RemoteException {
		ArrayList<String> etudiant = new ArrayList<String>();
		try {	
		   Class.forName("com.mysql.cj.jdbc.Driver");
		   con = DriverManager.getConnection("jdbc:mysql://localhost/gestions_notes","root","");
		   st = con.createStatement();
		   String sql = "select * from promotion where matricule='"+matricule+"'";
		   rs = st.executeQuery(sql);
		   while(rs.next()) {
			   etudiant.add(rs.getString(1));
			   etudiant.add(rs.getString(2));
			   etudiant.add(rs.getString(3));
			   etudiant.add(rs.getString(4));
		   }
		   }catch (Exception e) {
				e.printStackTrace();
			}
		return etudiant;
		
		 
	}
	@Override
	public double calculer_moyenne_de_la_promotion(int numero_promo) throws RemoteException {
		double taille = 0;
		double somme = 0;
		try {	
		   Class.forName("com.mysql.cj.jdbc.Driver");
		   con = DriverManager.getConnection("jdbc:mysql://localhost/gestions_notes","root","");
		   st = con.createStatement();
		   String sql = "select * from epreuve where promotion='"+numero_promo+"'";
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
	public String modifier(String nom, String prenom, int matricule) throws RemoteException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		   con = DriverManager.getConnection("jdbc:mysql://localhost/gestions_notes","root","");
		   st = con.createStatement();
    	   String sql = "update promotion set nom='"+nom+"', prenom='"+prenom+"' where matricule='"+matricule+"'";
		   st.executeUpdate(sql);
		   return "modifier avec success";
		} catch (Exception e) {
			return (e.toString());
		}
		
	}
	@Override
	public String supprimer(int matricule) throws RemoteException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		   con = DriverManager.getConnection("jdbc:mysql://localhost/gestions_notes","root","");
		   st = con.createStatement();
    	   String sql = "delete from promotion where matricule='"+matricule+"'";
		   st.executeUpdate(sql);
		   return "supprimer avec success";
		} catch (Exception e) {
			return (e.toString());
		}
	}
	 

	
	
}
