package cafeManagement.service.serviceinterface;

import org.springframework.validation.annotation.Validated;

import cafeManagement.model.security.User;

@Validated
public interface UserManagement {

	void createUser(User user);
}
