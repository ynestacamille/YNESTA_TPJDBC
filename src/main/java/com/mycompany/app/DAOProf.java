package com.mycompany.app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class DAOProf implements DAO<Prof> {
	 private static DAOProf instance;
	 private Connection conn;
	@Override
	public Prof insert(Prof prof) {
		final String req = "INSERT INTO PROF "
                + "VALUES (" + prof.getNumProf() + ", "
                + prof.getNomProf()+ ", "
                + prof.getPrenomProf() + ", "
                + prof.getAdrProf() + ", "
                + prof.getCpProf() + ", "
                + prof.getVilleProf() + ")";
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(req);
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return prof;
	}

	@Override
	public boolean delete(Prof prof) {
		final String req = "DELETE INTO PROF "
                + "WHERE NUM_PROF = " + prof.getNumProf();
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
	public boolean update(Prof prof) {
		final String req = "UPDATE PROF "
                + "SET NUM_PROF = " + prof.getNumProf()
                + ", NOM_PROF = " + prof.getNomProf()
                + ", PRENOM_PROF = " + prof.getPrenomProf()
                + ", ADR_PROF = " + prof.getAdrProf()
                + ", CP_PROF = "  + prof.getCpProf()
                + ", VILLE_PROF = " + prof.getVilleProf()
                + " WHERE NUM_PROF = " + prof.getNumProf();
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
	public Prof getById(int numT) {
		final String req = "SELECT * FROM PROF WHERE NUM_PROF = " + numT;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(req);
            Prof prof = new Prof();
            prof.setAdrProf(rset.getString("ADR_PROF"));
            prof.setCpProf(rset.getString("CP_PROF"));
            prof.setNomProf(rset.getString("NOM_PROF"));
            prof.setNumProf(rset.getInt("NUM_PROF"));
            prof.setPrenomProf(rset.getString("PRENOM_PROF"));
            prof.setVilleProf(rset.getString("VILLE_PROF"));
            stmt.close();
            return prof;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}

	@Override
	public List<Prof> findAll() {
		final String req = "SELECT * FROM PROF";
        List<Prof> listeProf = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(req);
            while(rset.next()){
	           Prof prof = new Prof();
	           prof.setNumProf(rset.getInt("NUM_PROF"));
	           prof.setNomProf(rset.getString("NOM_PROF"));
	           prof.setPrenomProf(rset.getString("PRENOM_PROF"));
	           prof.setAdrProf(rset.getString("ADR_PROF"));
	           prof.setCpProf(rset.getString("CP_PROF"));
	           prof.setVilleProf(rset.getString("VILLE_PROF"));
               listeProf.add(prof);
            }
            stmt.close();
            return listeProf;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return listeProf;
	}

	public static DAOProf getInstance() {
		return instance;
	}

	public static void setInstance(DAOProf instance) {
		DAOProf.instance = instance;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

}
