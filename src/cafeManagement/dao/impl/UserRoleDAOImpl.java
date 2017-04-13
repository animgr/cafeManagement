package cafeManagement.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cafeManagement.dao.UserRoleDAO;
import cafeManagement.model.security.UserRole;

/**
 * Manage user Role dao
 * 
 * @author a_mgr
 *
 */
@Repository
public class UserRoleDAOImpl extends GenericDAOImpl<UserRole, Integer> implements UserRoleDAO {

    @PersistenceContext
    private EntityManager em;

}
