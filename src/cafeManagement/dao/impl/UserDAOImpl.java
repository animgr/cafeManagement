package cafeManagement.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cafeManagement.dao.UserDAO;
import cafeManagement.model.security.User;

/**
 * Manage user dao
 * 
 * @author a_mgr
 *
 */
@Repository
public class UserDAOImpl extends GenericDAOImpl<User, Integer> implements UserDAO {

    @PersistenceContext
    private EntityManager em;
    
	@SuppressWarnings("unchecked")
	public User findByUserName(String username) {

		List<User> users = new ArrayList<>();

		users = em.createQuery("SELECT u FROM User u WHERE u.username=:username")
				.setParameter("username", username).getResultList();
		
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}
	
}
