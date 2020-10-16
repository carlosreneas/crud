package co.edu.ufps.crud.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.ufps.crud.model.User;
import co.edu.ufps.crud.util.Conexion;
import co.edu.ufps.crud.util.ConexionFactory;
import co.edu.ufps.crud.util.ConexionMySQL;

public class UserDAOSingleton implements UserDAO {
    
    private Conexion conexion;
    
    private static final String INSERT_USERS_SQL = "INSERT INTO users (name, email, country) VALUES (?, ?, ?);";
    private static final String SELECT_USER_BY_ID = "SELECT id,name,email,country FROM users WHERE id = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String DELETE_USERS_SQL = "DELETE FROM users WHERE id = ?;";
    private static final String UPDATE_USERS_SQL = "UPDATE users SET name = ?,email= ?, country =? WHERE id = ?;";
    
    public UserDAOSingleton() {
    	this.conexion = ConexionFactory.getConexion("mysql");
    }
    
    public UserDAOSingleton(String motor) {
    	this.conexion = ConexionFactory.getConexion(motor);
    }

    public void insertUser(User user) throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        // try-with-resource statement will auto close the connection.
        try {
        	
        	PreparedStatement preparedStatement = conexion.setPreparedStatement(INSERT_USERS_SQL);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            System.out.println(preparedStatement);
            conexion.execute();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    
    public User selectUser(int id) {
        User user = null;
        // Step 1: Establishing a Connection
        try {
        	PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = conexion.query();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user = new User(id, name, email, country);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }

    public List < User > selectAllUsers() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < User > users = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try {
        	
        	PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_ALL_USERS);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = conexion.query();
            
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                users.add(new User(id, name, email, country));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted = false;
        try {
	    	PreparedStatement preparedStatement = conexion.setPreparedStatement(DELETE_USERS_SQL);
	    	preparedStatement.setInt(1, id);
	        rowDeleted = conexion.execute() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
	    return rowDeleted;
    }

    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated = false;
        try {
	    	PreparedStatement preparedStatement = conexion.setPreparedStatement(UPDATE_USERS_SQL);
	    	preparedStatement.setString(1, user.getName());
	    	preparedStatement.setString(2, user.getEmail());
	    	preparedStatement.setString(3, user.getCountry());
	    	preparedStatement.setInt(4, user.getId());
	
	        rowUpdated = conexion.execute() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        
        return rowUpdated;
    }
    
    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
