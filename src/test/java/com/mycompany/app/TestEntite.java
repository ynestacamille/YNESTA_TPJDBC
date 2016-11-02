package com.mycompany.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class TestEntite {
	static final String CONNECT_URL = "jdbc:mysql://mysql-camilleynesta.alwaysdata.net:3306/camilleynesta_tutojdbc";
	static final String LOGIN = "128842";
	static final String PASSWORD = "linottexqxqi";
	// La requete de test
	static final String req = "SELECT NUM_ET, NOM_ET, PRENOM_ET " +
	                          "FROM ETUDIANT " +
	                          "WHERE VILLE_ET = 'AIX-EN-PROVENCE'";  
	static ArrayList<Etudiant> listeEtudiant = new ArrayList<Etudiant>();
	static ArrayList<Prof> listeProfesseur = new ArrayList<Prof>();
	static ArrayList<Module> listeModule = new ArrayList<Module>();
	
	public static void main(String[] args) throws SQLException {
		// Objet materialisant la connexion a la base de donnees
		
		Connection conn = ConnexionUnique.getInstance().getConnection();
		try {
			Statement stmt = conn.createStatement();
			
			//Exécution de la requête
			ResultSet rset = stmt.executeQuery(req);
			
			while (rset.next())
			{
				Etudiant etudiant = new Etudiant();
				
				etudiant.setNumEt(rset.getInt("NUM_ET"));
				etudiant.setNomEt(rset.getString("NOM_ET"));
				etudiant.setPrenomEt(rset.getString("PRENOM_ET"));
				listeEtudiant.add(etudiant);
			}
			
			for(Etudiant e : listeEtudiant)
			{
				System.out.println(e.toString());
			}
			
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();// Arggg!!!
			System.out.println(e.getMessage() + "\n");
		} finally {
			if (conn != null) {
				// Deconnexion de la base de donnees
				conn.close();
			}
		}
		
	}
}
