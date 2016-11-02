package com.mycompany.app;

import java.sql.*;
import java.util.ArrayList;
 

public class TestAsso1 {
	static final String CONNECT_URL = "jdbc:mysql://mysql-margauxpasquali.alwaysdata.net:3306/margauxpasquali_test";
	static final String LOGIN = "100990_dd";
	static final String PASSWORD = "azerty";
	// La requete de test
	static final String req = "SELECT NUM_PROF, NOM_PROF, PRENOM_PROF, MAT_SPEC "
							 + "FROM PROF ";
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
				Prof prof = new Prof();
				
				prof.setNumProf(rset.getInt("NUM_PROF"));
				prof.setNomProf(rset.getString("NOM_PROF"));
				prof.setPrenomProf(rset.getString("PRENOM_PROF"));
				
				Module mod = new Module();
				mod.setCode (rset.getString("MAT_SPEC"));
				prof.setSpecialite(mod);
				
				listeProfesseur.add(prof);
			}
			
			for(Prof prof : listeProfesseur){
				System.out.println(prof.toString());
				System.out.println("Mat_Spec :" + prof.getSpecialite().getCode());}
			
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
