package co.edu.ufps.crud.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionMySQL extends ConexionBase implements Conexion {

	//private Connection con=null;
	private static ConexionMySQL db;
	//private PreparedStatement preparedStatement;
	
	private static final String url= "jdbc:mysql://localhost:3306/";
	private static final String dbName = "demo";
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String userName = "root";
    private static final String password = "";

	public ConexionMySQL() {
		super(driver, url, dbName, userName, password);
	}
	
	public static ConexionMySQL getConexion(){
		if ( db == null ) {
            db = new ConexionMySQL();
        }
		return db;
	}
	
	
	public PreparedStatement setPreparedStatement(String sql) throws SQLException {
		if ( db == null ) {
            db = new ConexionMySQL();
        }
		
		this.preparedStatement = con.prepareStatement(sql);
		return this.preparedStatement;
	}
 

	



}