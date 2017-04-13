package cafeManagement.service.coreservice;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import cafeManagement.dao.UserDAO;
import cafeManagement.model.security.User;
import cafeManagement.service.serviceinterface.UserManagement;

@Named
public class CoreUserManagement implements UserManagement {

	@Autowired
	UserDAO userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void createUser(User user) {
		// here we encode the password for insert into db
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDao.add(user);
	}

}
