package notes;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ServerRmi {
	
	public static void main(String[] args) {
		
		try {
			LocateRegistry.createRegistry(1099);
			EtudiantImplService Etu = new EtudiantImplService();
			System.out.println(Etu.toString());
			String chemin="rmi://127.0.0.1:1099/Etu";
			Naming.rebind(chemin, Etu);
			
			
			PromotionImplService Pro = new PromotionImplService();
			System.out.println(Pro.toString());
			String chemin2="rmi://127.0.0.1:1099/Pro";
			Naming.rebind(chemin2, Pro);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
