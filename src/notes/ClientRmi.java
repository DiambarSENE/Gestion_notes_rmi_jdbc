package notes;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import metier.Etudiant;
import net.proteanit.sql.DbUtils;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ClientRmi {

	private JFrame frame;
	private JTextField input_nom;
	private JTextField input_prenom;
	private JTextField input_matricule; 
	private JTextField input_recherche;
	private JTable table;
	
	Connection con;
	Statement st;
	ResultSet rs;
	private JTextField input_matiere;
	private JTextField input_notes;
	private JTextField input_coef;
	private JTextField input_matr;
	private JTable table_1;
	private JTextField input_moyenne;
	private JTextField input_moyenne_promo;
	private JTextField input_promo;
	
	public void Load_table() {
		  try { 
			  con = DriverManager.getConnection("jdbc:mysql://localhost/gestions_notes","root","");
			  st = con.prepareStatement("select * from promotion"); 
		      rs = st.executeQuery("select * from promotion");
		      table.setModel(DbUtils.resultSetToTableModel(rs));
		 } catch (SQLException e) { 
		  e.printStackTrace(); }
		 
	}
	
	public void Load_table_etudiant() {
		  try { 
			  con = DriverManager.getConnection("jdbc:mysql://localhost/gestions_notes","root","");
			  st = con.prepareStatement("select * from epreuve"); 
		      rs = st.executeQuery("select * from epreuve");
		      table_1.setModel(DbUtils.resultSetToTableModel(rs));
		 } catch (SQLException e) { 
		  e.printStackTrace(); }
		 
	}
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientRmi window = new ClientRmi();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientRmi() {
		initialize();
		Load_table();
		Load_table_etudiant();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1186, 667);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Gestion des notes ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(479, 11, 284, 53);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Etudiant", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 71, 366, 197);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Nom");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(43, 26, 100, 34);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Prenom");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(43, 66, 100, 34);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Matricule ");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(43, 104, 100, 34);
		panel.add(lblNewLabel_1_2);
		
		input_nom = new JTextField();
		input_nom.setBounds(139, 35, 169, 20);
		panel.add(input_nom);
		input_nom.setColumns(10);
		
		input_prenom = new JTextField();
		input_prenom.setColumns(10);
		input_prenom.setBounds(139, 71, 169, 20);
		panel.add(input_prenom);
		
		input_matricule = new JTextField();
		input_matricule.setColumns(10);
		input_matricule.setBounds(139, 113, 169, 20);
		panel.add(input_matricule);
		
		JButton btnAjouter = new JButton("Add");
		btnAjouter.setBounds(10, 149, 75, 37);
		panel.add(btnAjouter);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			String nom = input_nom.getText();
			String prenom = input_prenom.getText();
			int matricule = Integer.parseInt(input_matricule.getText());
			
			try {
				IPromotion stub = (IPromotion)Naming.lookup("rmi://127.0.0.1:1099/Pro");
				String result = stub.ajouter_un_etudiant(nom,prenom,matricule);
				Load_table();
				JOptionPane.showConfirmDialog(null, result, "success",JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				JOptionPane.showConfirmDialog(null, ex, "Error",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		});
		btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnEffacer = new JButton("Delete");
		btnEffacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input_nom.setText("");
				input_prenom.setText("");
				input_matricule.setText("");
			}
		});
		btnEffacer.setBounds(177, 149, 83, 37);
		panel.add(btnEffacer);
		btnEffacer.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnModifier = new JButton("Update");
		btnModifier.setBounds(87, 149, 88, 37);
		panel.add(btnModifier);
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nom = input_nom.getText();
				String prenom = input_prenom.getText();
				int matricule = Integer.parseInt(input_matricule.getText());
				try {
					IPromotion stub = (IPromotion)Naming.lookup("rmi://127.0.0.1:1099/Pro");
					String result = stub.modifier(nom,prenom,matricule);
					Load_table();
					JOptionPane.showConfirmDialog(null, result, "success",JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					JOptionPane.showConfirmDialog(null, ex, "Error",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnModifier.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnSupprimer = new JButton("Remove");
		btnSupprimer.setBounds(261, 149, 95, 37);
		panel.add(btnSupprimer);
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int matricule = Integer.parseInt(input_matricule.getText());
				try {
					IPromotion stub = (IPromotion)Naming.lookup("rmi://127.0.0.1:1099/Pro");
					String result = stub.supprimer(matricule);
					Load_table();
					JOptionPane.showConfirmDialog(null, result, "success",JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					JOptionPane.showConfirmDialog(null, ex, "Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Recherche", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(10, 279, 350, 62);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		input_recherche = new JTextField();
		input_recherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		input_recherche.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		input_recherche.setBounds(177, 19, 152, 22);
		panel_2.add(input_recherche);
		input_recherche.setColumns(10);
		
		JButton btnNewCherche = new JButton("Etudiant_matricule");
		btnNewCherche.setBounds(26, 18, 141, 23);
		panel_2.add(btnNewCherche);
		btnNewCherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						try {
							int matricule = Integer.parseInt(input_recherche.getText());
							
							IPromotion stub = (IPromotion)Naming.lookup("rmi://127.0.0.1:1099/Pro");
							ArrayList<String> result = stub.rechercher_un_etudiant(matricule);
						    JOptionPane.showConfirmDialog(null, result, "Information de l'etudiant ",JOptionPane.ERROR_MESSAGE);
						
						}catch(Exception ex){
							JOptionPane.showConfirmDialog(null, ex, "Error",JOptionPane.ERROR_MESSAGE);
						}
					}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(386, 78, 435, 263);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Matieres", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 352, 366, 265);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_3 = new JLabel("Matiere");
		lblNewLabel_1_3.setBounds(23, 32, 79, 29);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Notes");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_3_1.setBounds(23, 66, 79, 29);
		panel_1.add(lblNewLabel_1_3_1);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("Coefficient");
		lblNewLabel_1_3_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_3_2.setBounds(23, 102, 89, 29);
		panel_1.add(lblNewLabel_1_3_2);
		
		JLabel lblNewLabel_1_3_3 = new JLabel("Matricule_etu");
		lblNewLabel_1_3_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_3_3.setBounds(23, 131, 100, 29);
		panel_1.add(lblNewLabel_1_3_3);
		
		input_matiere = new JTextField();
		input_matiere.setColumns(10);
		input_matiere.setBounds(150, 32, 169, 20);
		panel_1.add(input_matiere);
		
		input_notes = new JTextField();
		input_notes.setColumns(10);
		input_notes.setBounds(150, 72, 169, 20);
		panel_1.add(input_notes);
		
		input_coef = new JTextField();
		input_coef.setColumns(10);
		input_coef.setBounds(150, 108, 169, 20);
		panel_1.add(input_coef);
		
		input_matr = new JTextField();
		input_matr.setColumns(10);
		input_matr.setBounds(150, 137, 169, 20);
		panel_1.add(input_matr);
		
		JButton btnAjouter_mat = new JButton("Add");
		btnAjouter_mat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String matieres = input_matiere.getText();
				double notes = Double.parseDouble(input_notes.getText());
				int coef  = Integer.parseInt(input_coef.getText());
				//Scanner sc = new Scanner(System.in);
				//int matricule = sc.nextInt();
				int matricule = Integer.parseInt(input_matr.getText());
				int num_promo = Integer.parseInt(input_promo.getText());
				
				
				
				try {
					IEtudiant stub = (IEtudiant)Naming.lookup("rmi://127.0.0.1:1099/Etu");
					String result = stub.ajouter_une_epreuve(matieres,notes,coef,matricule,num_promo);
					Load_table_etudiant();
					JOptionPane.showConfirmDialog(null, result, "ajouter avec success",JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showConfirmDialog(null, ex, "Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAjouter_mat.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAjouter_mat.setBounds(10, 217, 79, 37);
		panel_1.add(btnAjouter_mat);
		
		JButton btnEffacer_1 = new JButton("Delete");
		btnEffacer_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input_matiere.setText("");
				input_notes.setText("");
				input_coef.setText("");
				input_matr.setText("");
				input_promo.setText("");
			}
		});
		btnEffacer_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEffacer_1.setBounds(182, 217, 79, 37);
		panel_1.add(btnEffacer_1);
		
		JLabel lblNewLabel_1_3_3_1 = new JLabel("Numero_promo");
		lblNewLabel_1_3_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_3_3_1.setBounds(23, 171, 117, 29);
		panel_1.add(lblNewLabel_1_3_3_1);
		
		input_promo = new JTextField();
		input_promo.setColumns(10);
		input_promo.setBounds(150, 177, 169, 20);
		panel_1.add(input_promo);
		
		JButton btnModifier_mat = new JButton("Update");
		btnModifier_mat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String matieres = input_matiere.getText();
				double notes = Double.parseDouble(input_notes.getText());
				int coef  = Integer.parseInt(input_coef.getText());
				int matricule = Integer.parseInt(input_matr.getText());
				int num_promo = Integer.parseInt(input_promo.getText());
				
				try {
					IEtudiant stub = (IEtudiant)Naming.lookup("rmi://127.0.0.1:1099/Etu");
					String result = stub.modifier_matiere(matieres,notes,coef,matricule,num_promo);
					Load_table_etudiant();
					JOptionPane.showConfirmDialog(null, result, "modifier avec success",JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					JOptionPane.showConfirmDialog(null, ex, "Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnModifier_mat.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnModifier_mat.setBounds(93, 217, 89, 37);
		panel_1.add(btnModifier_mat);
		
		JButton btnSupprimer_mat = new JButton("Remove");
		btnSupprimer_mat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int matricule = Integer.parseInt(input_matr.getText());
				try {
					IEtudiant stub = (IEtudiant)Naming.lookup("rmi://127.0.0.1:1099/Etu");
					String result = stub.supprimer_matiere(matricule);
					Load_table_etudiant();
					JOptionPane.showConfirmDialog(null, result, "supprimer avec success",JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					JOptionPane.showConfirmDialog(null, ex, "Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSupprimer_mat.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSupprimer_mat.setBounds(261, 217, 95, 37);
		panel_1.add(btnSupprimer_mat);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(386, 359, 435, 258);
		frame.getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Calcul_moyenne", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(831, 187, 367, 281);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Moyenne_promotion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(20, 148, 326, 101);
		panel_3.add(panel_4);
		panel_4.setLayout(null);
		
		input_moyenne_promo = new JTextField();
		input_moyenne_promo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
		});
		input_moyenne_promo.setColumns(10);
		input_moyenne_promo.setBounds(215, 44, 101, 22);
		panel_4.add(input_moyenne_promo);
		
		JButton btnNewButton_1 = new JButton("Entrer_numero_promo");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int promo = Integer.parseInt(input_moyenne_promo.getText());
					
					IPromotion stub = (IPromotion)Naming.lookup("rmi://127.0.0.1:1099/Pro");
					double result = stub.calculer_moyenne_de_la_promotion(promo);
					JOptionPane.showConfirmDialog(null, result, "La moyenne de la promotion est : ",JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showConfirmDialog(null, ex, "Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(10, 43, 195, 23);
		panel_4.add(btnNewButton_1);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Moyenne_etudiant", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(20, 52, 326, 90);
		panel_3.add(panel_5);
		panel_5.setLayout(null);
		
		input_moyenne = new JTextField();
		input_moyenne.setBounds(189, 40, 127, 22);
		panel_5.add(input_moyenne);
		input_moyenne.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
		});
		input_moyenne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		input_moyenne.setColumns(10);
		
		JButton btnNewButton = new JButton("Entrer_matr_etu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int matricule = Integer.parseInt(input_moyenne.getText());
				
				try {
					IEtudiant stub = (IEtudiant)Naming.lookup("rmi://127.0.0.1:1099/Etu");
					double result = stub.calculer_la_moyenne(matricule);
					JOptionPane.showConfirmDialog(null, result, "La moyenne de l'etudiant est : ",JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showConfirmDialog(null, ex, "Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(10, 39, 169, 23);
		panel_5.add(btnNewButton);
		
		JButton btnQuiter = new JButton("Quiter");
		btnQuiter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuiter.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnQuiter.setBounds(975, 523, 98, 37);
		frame.getContentPane().add(btnQuiter);
	}
}
