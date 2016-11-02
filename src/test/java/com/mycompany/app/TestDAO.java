package com.mycompany.app;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;




public class TestDAO {
	static final String CONNECT_URL = "jdbc:mysql://mysql-margauxpasquali.alwaysdata.net:3306/margauxpasquali_test";
	static final String LOGIN = "100990_dd";
	static final String PASSWORD = "azerty";
	
	
    public static void main (String[] args) throws SQLException {
    	

    	
        DAOEtudiant DAOEt = DAOEtudiant.getInstance();
        
    	System.out.println( "Connexion a " + CONNECT_URL) ;
    	Connection conn = ConnexionUnique.getInstance().getConnection();
    	System.out.println ( "Connecte \n" );

        DAOEt.setConnection(conn);
        

        List<Etudiant> etudiantAnnee2;

        etudiantAnnee2 = DAOEt.findByAnnee(2);

        for( Etudiant e : etudiantAnnee2){
        	System.out.println(e);
        }
            	
          
    }

}
