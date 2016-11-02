package com.mycompany.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnexionUnique {
	private Connection connection;
	private static ConnexionUnique instance;
	private String CONNECT_URL = "jdbc:mysql://mysql-camilleynesta.alwaysdata.net:3306/camilleynesta_tutojdbc";;
	
	public ConnexionUnique() throws SQLException{
		connection = DriverManager.getConnection(CONNECT_URL, "128842", "linottexqxqi");
	}
	
	public Connection getConnection(){
		return connection;
	}
	
	public static ConnexionUnique getInstance() throws SQLException{
		if(instance == null)
			instance = new ConnexionUnique();
		return instance; 
	}
	
	public void setConnection (Connection connect){
		connection = connect;
	}

}

