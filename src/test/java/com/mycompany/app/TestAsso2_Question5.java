package com.mycompany.app;

import java.sql.*;
import java.util.ArrayList;
public class TestAsso2_Question5 {

	static final String CONNECT_URL = "jdbc:mysql://mysql-margauxpasquali.alwaysdata.net:3306/margauxpasquali_test";
	static final String LOGIN = "100990_dd";
	static final String PASSWORD = "azerty";
	// La requete de test
	static final String req =   "SELECT *"
			 + "FROM ENSEIGNT, ETUDIANT "
			 + "WHERE ENSEIGNT.NUM_ET = ETUDIANT.NUM_ET";            
	
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
			ArrayList<Enseignement> listeEnseignement = new ArrayList<>();
			while (rset.next())
			{
				Enseignement enseignement = new Enseignement();
                Etudiant etudiant = new Etudiant();
                Module module = new Module();
                Prof prof = new Prof();
                module.setCode(rset.getString("CODE"));
                prof.setNumProf(rset.getInt("NUM_PROF"));
                etudiant.setNumEt(rset.getInt("NUM_ET"));
                etudiant.setGroupe(rset.getInt("GROUPE"));
                enseignement.setEtudiant(etudiant);
                enseignement.setModule(module);
                enseignement.setProf(prof);
				listeEnseignement.add(enseignement);
			}
			
			for(Enseignement ens : listeEnseignement)
			{
				if(ens.getEtudiant().getGroupe() == 1)
				{
					System.out.println(ens.getModule().getCode() + " etudié par l'étudiant " +ens.getEtudiant().getNumEt() + " appartenant au groupe " + ens.getEtudiant().getGroupe() +  " avec le prof numéro " + ens.getProf().getNumProf());
				}
			}
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
