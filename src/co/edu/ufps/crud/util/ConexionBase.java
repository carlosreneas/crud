package co.edu.ufps.crud.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBase {
	
	protected Connection con=null;
	protected PreparedStatement preparedStatement;
	
	public ConexionBase() {
		
	}
	
	public ConexionBase(String driver, String url, String dbName, String userName, String password) {
		try {
			Class.forName(driver).newInstance();
			con = (Connection)DriverManager.getConnection(url+dbName,userName,password);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getCon() {
		return this.con;
	}
	
	public void cerrarConexion(){
		try {
			this.con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet query() throws SQLException{
        ResultSet res = this.preparedStatement.executeQuery();
        return res;
    }
	
	public int execute() throws SQLException {
        int result = this.preparedStatement.executeUpdate();
        return result;
 
    }
		

}
