package cafeManagement.service.servicefacade;

import javax.inject.Inject;
import javax.inject.Named;

import cafeManagement.model.security.User;
import cafeManagement.service.serviceinterface.UserManagement;

@Named
public class UserManagementServices {

	@Inject
	private UserManagement userManagement;

	public void createUser(User user) {
		userManagement.createUser(user);
	}

}
