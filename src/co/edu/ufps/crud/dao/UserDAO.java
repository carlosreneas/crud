package co.edu.ufps.crud.dao;

import java.sql.SQLException;
import java.util.List;

import co.edu.ufps.crud.model.User;

public interface UserDAO {
	public void insertUser(User user) throws SQLException;
	public User selectUser(int id);
	public List < User > selectAllUsers();
	public boolean deleteUser(int id) throws SQLException;
	public boolean updateUser(User user) throws SQLException;
}
