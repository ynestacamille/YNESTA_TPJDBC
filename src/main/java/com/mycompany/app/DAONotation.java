package com.mycompany.app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DAONotation implements DAO<Notation> {
	 private static DAONotation instance;
	    private Connection conn;
	@Override
	public Notation insert(Notation note) {
		 final String req = "INSERT INTO NOTATION "
	                + "VALUES (" + note.getEtudiant().getNumEt() + ", "
	                + note.getModule().getCode() + ", "
	                + note.getMoyCC() + ", "
	                + note.getMoyTest() + ")";
	        try {
	            Statement stmt = conn.createStatement();
	            stmt.executeUpdate(req);
	            stmt.close();
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return note;
	}

	@Override
	public boolean delete(Notation note) {
		final String req = "DELETE INTO NOTATION "
                + "WHERE NUM_ET = " + note.getEtudiant().getNumEt()
                + " AND CODE = " + note.getModule().getCode();
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
	public boolean update(Notation note) {
		final String req = "UPDATE Notation "
                + "SET NUM_ET = " + note.getEtudiant().getNumEt()
                + ", CODE = " + note.getModule().getCode()
                + ", MOY_CC = " + note.getMoyCC()
                + ", MOY_TEST = " + note.getMoyTest()
                + " WHERE NUM_ET = " + note.getEtudiant().getNumEt()
                + " AND CODE = " + note.getModule().getCode();

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
	public Notation getById(int numT) {
        final String req = "SELECT * FROM NOTATION WHERE  NUM_ET = " + numT;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(req);
            Notation note = new Notation();
            DAOEtudiant DAOEt = DAOEtudiant.getInstance();
            DAOModule DAOMod = DAOModule.getInstance();
            note.setEtudiant(DAOEt.getById(rset.getInt("NUM_ET")));
            note.setModule(DAOMod.getById(rset.getString("CODE")));
            note.setMoyCC(rset.getFloat("MOY_CC"));
            note.setMoyTest(rset.getFloat("MOY_TEST"));
            stmt.close();
            return note;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


	@Override
	public List<Notation> findAll() {
		final String req = "SELECT * FROM NOTATION";
        List<Notation> listeNotation = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(req);
            DAOEtudiant DAOEt = DAOEtudiant.getInstance();
            DAOModule DAOMod = DAOModule.getInstance();
            while(rset.next()){
                Notation note = new Notation();
                note.setEtudiant(DAOEt.getById(rset.getInt("NUM_ET")));
                note.setModule(DAOMod.getById(rset.getString("CODE")));
                note.setMoyCC(rset.getFloat("MOY_CC"));
                note.setMoyTest(rset.getFloat("MOY_TEST"));
                listeNotation.add(note);
            }
            stmt.close();
            return listeNotation;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return listeNotation;

	}

	public static DAONotation getInstance() {
		return instance;
	}

	public static void setInstance(DAONotation instance) {
		DAONotation.instance = instance;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

}
