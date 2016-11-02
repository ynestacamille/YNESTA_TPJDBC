package com.mycompany.app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestAsso2 {
	static final String CONNECT_URL = "jdbc:mysql://mysql-margauxpasquali.alwaysdata.net:3306/margauxpasquali_test";
	static final String LOGIN = "100990_dd";
	static final String PASSWORD = "azerty";
	// La requete de test
	static final String req =  "SELECT * FROM ETUDIANT, MODULE, NOTATION " +
            "WHERE NOTATION.NUM_ET = ETUDIANT.NUM_ET " +
            "AND NOTATION.CODE = MODULE.CODE";                                     
	public static void main(String[] args) throws SQLException {
		Connection conn = ConnexionUnique.getInstance().getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(req);
            AssociationNotation associationNotation = new AssociationNotation();
            
            while (rset.next()) {
                Prof prof = new Prof(/*rset.getInt("NUM_PROF"), rset.getString("NOM_PROF"), rset.getString("PRENOM_PROF"), rset.getString("ADR_PROF"), rset.getString("CP_PROF"), rset.getString("VILLE_PROF"), null*/);
                Module mod = new Module();
                		mod.setCode(rset.getString("CODE"));
                		mod.setLibelle(rset.getString("LIBELLE"));
                		mod.sethCoursPrev(rset.getInt("H_COURS_PREV"));
                		mod.sethCoursRea(rset.getInt("H_COURS_REA"));
                		mod.sethTpPrev(rset.getInt("H_TP_PREV"));
                		mod.sethTpRea(rset.getInt("H_TP_REA"));
                		mod.setDiscipline(rset.getString("DISCIPLINE"));
                		mod.setCoefTest(rset.getInt("COEFF_TEST"));
                		mod.setCoefCc(rset.getInt("COEFF_CC"));
                		mod.setResponsable(prof);
                Etudiant et = new Etudiant();
                		et.setNumEt(rset.getInt("NUM_ET"));
                		et.setNomEt(rset.getString("NOM_ET"));
                		et.setPrenomEt(rset.getString("PRENOM_ET"));
                		et.setCpEt(rset.getString("CP_ET"));
                		et.setVilleEt(rset.getString("VILLE_ET"));
                		et.setAnnee(rset.getInt("ANNEE"));
                		et.setGroupe(rset.getInt("GROUPE"));
                Notation note = new Notation();
                		note.setMoyCC(rset.getFloat("MOY_CC"));
                 		note.setMoyTest(rset.getFloat("MOY_TEST"));
                
                associationNotation.creerLien(mod, et, note);
            }
            Module ACSI = new Module();
            ACSI.setCode("ACSI");
            ACSI.setLibelle("CONCEPTION DE SI");
            ACSI.sethCoursPrev(30);
            ACSI.sethTpPrev(40);
            ACSI.setDiscipline("INFORMATIQUE");
            ACSI.setCoefTest(60);
            ACSI.setCoefCc(40);
            Prof p = new Prof();
        		p.setNumProf(1);
            ACSI.setResponsable(p);
            for (Lien lien : associationNotation.getLiens(ACSI)) {
                if (lien.getModule().getCode().equals("ACSI")) {
                    System.out.println(lien.toString());
                }

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
                conn.close();
            }
        }
	}
}
