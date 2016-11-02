package com.mycompany.app;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnexion {
	static final String CONNECT_URL = "jdbc:mysql://mysql-camilleynesta.alwaysdata.net:3306/camilleynesta_tutojdbc";
	static final String LOGIN = "128842";
	static final String PASSWORD = "linottexqxqi";
	// La requete de test
	static final String req = "SELECT NUM_ET, NOM_ET, PRENOM_ET " +
	                          "FROM ETUDIANT " +
	                          "WHERE VILLE_ET = 'AIX-EN-PROVENCE'";  
	
	public static void main(String[] args) throws SQLException {
		// Objet materialisant la connexion a la base de donnees
		ConnexionUnique conn = new ConnexionUnique();
		Connection test = null;
		try {
			// Connexion a la base
			System.out.println("Connexion a " + CONNECT_URL);
			test = DriverManager.getConnection(CONNECT_URL,LOGIN,PASSWORD);
			conn.setConnection(test);
			System.out.println("Connecte\n");
			// Creation d'une instruction SQL
			Statement stmt = conn.getConnection().createStatement();
			// Execution de la requete
			System.out.println("Execution de la requete : " + req );
			ResultSet rset = stmt.executeQuery(req);
			// Affichage du resultat
			while (rset.next()){	
				System.out.print(rset.getInt("NUM_ET") + " ");
				System.out.print(rset.getString("NOM_ET") + " ");
				System.out.println(rset.getString("PRENOM_ET"));
			}
			// Fermeture de l'instruction (liberation des ressources)
			stmt.close();
			System.out.println("\nOk.\n");
		} catch (SQLException e) {
			e.printStackTrace();// Arggg!!!
			System.out.println(e.getMessage() + "\n");
		} finally {
			if (conn != null) {
				// Deconnexion de la base de donnees
				conn.getConnection().close();
			}
		}
	}
}


