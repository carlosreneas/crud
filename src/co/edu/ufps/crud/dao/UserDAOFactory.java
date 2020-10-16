package co.edu.ufps.crud.dao;

public class UserDAOFactory {
    public static UserDAO getUserDAO(String type) { 
    	switch(type) {
    		case "singleton":
    			return new UserDAOSingleton();
    		case "jpa":
    			return new UserDAOJpa();
    		default:
    			return new UserDAOJdbc();
    	}
    }
    
    public static UserDAO getUserDAO(String type, String motor) { 
    	switch(type) {
    		case "singleton":
    			return new UserDAOSingleton(motor);
    		case "jpa":
    			return new UserDAOJpa();
    		default:
    			return new UserDAOJdbc();
    	}
    }
}
