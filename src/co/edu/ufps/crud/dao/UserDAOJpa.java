package co.edu.ufps.crud.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import co.edu.ufps.crud.model.User;

public class UserDAOJpa implements UserDAO {
	
	
	protected EntityManager getEntityManager() {
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("crud");
		EntityManager em = emf.createEntityManager();
		return em;
	}
	
	@Override
	public void insertUser(User user) throws SQLException {
		
		co.edu.ufps.crud.entities.User userEntity = new co.edu.ufps.crud.entities.User();
		userEntity.setName(user.getName());
		userEntity.setCountry(user.getCountry());
		userEntity.setEmail(user.getEmail());
		
		EntityManager em = this.getEntityManager();
		
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		

	}

	@Override
	public User selectUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> selectAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUser(int id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
