package cafeManagement.dao;


import cafeManagement.model.security.User;

/**
 * 
 * @author a_mgr
 *
 */
public interface UserDAO extends GenericDAO<User, Integer>{

	User findByUserName(String username);

	

}
