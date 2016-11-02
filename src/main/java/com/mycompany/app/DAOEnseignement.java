package com.mycompany.app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DAOEnseignement implements DAO<Enseignement>{
	private static DAOEnseignement instance;
    private Connection conn;
	@Override
	public Enseignement insert(Enseignement enseignement) {
		 final String req = "INSERT INTO ENSEIGNEMENT "
	                + "VALUES (" + enseignement.getEtudiant().getNumEt() + ", "
	                + enseignement.getModule().getCode() + ", "
	                + enseignement.getProf().getNumProf() + ")";
	        try {
	            Statement stmt = conn.createStatement();
	            stmt.executeUpdate(req);
	            stmt.close();
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return enseignement;
	}

	@Override
	public boolean delete(Enseignement enseignement) {
		 final String req = "DELETE INTO Enseignement "
	                + "WHERE NUM_ET = " + enseignement.getEtudiant().getNumEt()
	                + " AND CODE = " + enseignement.getModule().getCode()
	                + " AND NUM_PROF = " + enseignement.getProf().getNumProf();
	        try {
	            Statement stmt = conn.createStatement();
	            stmt.executeUpdate(req);
	            stmt.close();
	            return true;
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	}

	@Override
	public boolean update(Enseignement enseignement) {
		 final String req = "UPDATE Enseignement "
	                + "SET NUM_ET = " + enseignement.getEtudiant().getNumEt()
	                + ", CODE = " + enseignement.getModule().getCode()
	                + ", NUM_PROF = " + enseignement.getProf().getNumProf()
	                + " WHERE NUM_ET = " + enseignement.getEtudiant().getNumEt()
	                + " AND CODE = " + enseignement.getModule().getCode()
	                + " AND NUM_PROF = " + enseignement.getProf().getNumProf();
	        try {
	            Statement stmt = conn.createStatement();
	            stmt.executeUpdate(req);
	            stmt.close();
	            return true;
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;

	}

	@Override
	public Enseignement getById(int numT) {
		final String req = "SELECT * FROM Enseignement WHERE  NUM_ET = " + numT;

		try {
		   Statement stmt = conn.createStatement();
		   ResultSet rset = stmt.executeQuery(req);
		   Enseignement ens = new Enseignement();
		   DAOEtudiant DAOEt = DAOEtudiant.getInstance();
		   DAOModule DAOMod = DAOModule.getInstance();
		   DAOProf DAOPr = DAOProf.getInstance();
		   ens.setEtudiant(DAOEt.getById(rset.getInt("NUM_ET")));
		   ens.setModule(DAOMod.getById(rset.getString("CODE")));
		   ens.setProf(DAOPr.getById(rset.getInt("NUM_PROF")));
		   stmt.close();
		   return ens;
		}
		catch (SQLException e) {
		   e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Enseignement> findAll() {
		 final String req = "SELECT * FROM ETUDIANT";
	        List<Enseignement> listeEtudiant = new ArrayList<>();
	        try {
	            Statement stmt = conn.createStatement();
	            ResultSet rset = stmt.executeQuery(req);
	            DAOEtudiant DAOEt = DAOEtudiant.getInstance();
	            DAOModule DAOMod = DAOModule.getInstance();
	            DAOProf DAOPr = DAOProf.getInstance();
	            while(rset.next()){
	                Enseignement ens = new Enseignement();
	                ens.setEtudiant(DAOEt.getById(rset.getInt("NUM_ET")));
	                ens.setModule(DAOMod.getById(rset.getString("CODE")));
	                ens.setProf(DAOPr.getById(rset.getInt("NUM_PROF")));
	                listeEtudiant.add(ens);
	            }
	            stmt.close();
	            return listeEtudiant;
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return listeEtudiant;
	    }

	public static DAOEnseignement getInstance() {
		return instance;
	}

	public static void setInstance(DAOEnseignement instance) {
		DAOEnseignement.instance = instance;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

}
