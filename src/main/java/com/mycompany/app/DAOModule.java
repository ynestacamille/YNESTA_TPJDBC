package com.mycompany.app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DAOModule implements DAO<Module>{
	 private static DAOModule instance;
	 private Connection conn;
	 
	@Override
	public Module insert(Module module) {
		final String req = "INSERT INTO MODULE "
                + "VALUES (" + module.getCode() + ", "
                + module.getLibelle() + ", "
                + module.gethCoursPrev()+ ", "
                + module.gethCoursRea() + ", "
                + module.gethTpPrev() + ", "
                + module.gethTpRea() + ", "
                + module.getDiscipline() + ", "
                + module.getCoefTest() + ", "
                + module.getCoefCc() + ")";
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(req);
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return module;
	}

	@Override
	public boolean delete(Module module) {
		final String req = "DELETE INTO MODULE "
                + "WHERE CODE = " + module.getCode();
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
	public boolean update(Module module) {
		final String req = "UPDATE MODULE "
                + "SET CODE = " + module.getCode()
                + ", LIBELLE = " + module.getLibelle()
                + ", H_COURS_PREV =  "+ module.gethCoursPrev()
                + ", H_COURS_REA = " + module.gethCoursRea()
                + ", H_TP_PREV = "+ module.gethTpPrev()
                + ", H_TP_REA = "+ module.gethTpRea()
                + ", DISCIPLINE = "+ module.getDiscipline()
                + ", COEF_TEST = "+ module.getCoefTest()
                + ", COEF_CC = "+ module.getCoefCc()
                + " WHERE CODE = " +module.getCode();
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
	public Module getById(int numT) {
        return null;
	}
	
	public Module getById(String code){
		final String req = "SELECT * FROM MODULE WHERE CODE = " + code;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(req);
            Module module= new Module();
            module.setCode(rset.getString("CODE"));
            module.setLibelle(rset.getString("LIBELLE"));
            module.sethCoursPrev(rset.getInt("H_COURS_PREV"));
            module.sethCoursRea(rset.getInt("H_COURS_REA"));
            module.sethTpPrev(rset.getInt("H_TP_PREV"));
            module.sethTpRea(rset.getInt("H_TP_REA"));
            module.setDiscipline(rset.getString("DISCIPLINE"));
            module.setCoefTest(rset.getInt("COEF_TEST"));
            module.setCoefCc(rset.getInt("COEF_CC"));

            stmt.close();
            return module;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}

	@Override
	public List<Module> findAll() {
		final String req = "SELECT * FROM MODULE";
        List<Module> listeModule= new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(req);
            while(rset.next()){
                Module module = new Module();
                module.setCode(rset.getString("CODE"));
                module.setLibelle(rset.getString("LIBELLE"));
                module.sethCoursPrev(rset.getInt("H_COURS_PREV"));
                module.sethCoursRea(rset.getInt("H_COURS_REA"));
                module.sethTpPrev(rset.getInt("H_TP_PREV"));
                module.sethTpRea(rset.getInt("H_TP_REA"));
                module.setDiscipline(rset.getString("DISCIPLINE"));
                module.setCoefTest(rset.getInt("COEF_TEST"));
                module.setCoefCc(rset.getInt("COEF_CC"));

                listeModule.add(module);
            }
            stmt.close();
            return listeModule;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return listeModule;
	}

	public static DAOModule getInstance() {
		return instance;
	}

	public static void setInstance(DAOModule instance) {
		DAOModule.instance = instance;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

}
