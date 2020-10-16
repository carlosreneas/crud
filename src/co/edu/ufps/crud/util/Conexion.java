package co.edu.ufps.crud.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Conexion {
	public Connection getCon();
	public void cerrarConexion();
	public PreparedStatement setPreparedStatement(String sql) throws SQLException;
	public ResultSet query() throws SQLException;
	public int execute() throws SQLException;
}
